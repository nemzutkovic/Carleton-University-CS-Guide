/* 3000pc.c */
/* producer-consumer example using signals, processes and mmap */
/* v1 Oct. 15, 2017 */
/* Licenced under the GPLv3, copyright Anil Somayaji */
/* You really shouldn't be incorporating parts of this in any other code,
   it is meant for teaching, not production */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/mman.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <semaphore.h>
#include <string.h>
#include <time.h>

#define QUEUESIZE 32
#define WORDSIZE 16

const int wordlist_size = 27;
const char *wordlist[] = {
        "Alpha",
        "Bravo",
        "Charlie",
        "Delta",
        "Echo",
        "Foxtrot",
        "Golf",
        "Hotel",
        "India",
        "Juliet",
        "Kilo",
        "Lima",
        "Mike",
        "November",
        "Oscar",
        "Papa",
        "Quebec",
        "Romeo",
        "Sierra",
        "Tango",
        "Uniform",
        "Victor",
        "Whiskey",
        "X-ray",
        "Yankee",
        "Zulu",
        "Dash"
};

typedef struct entry {
        char word[WORDSIZE];
        sem_t lock;
} entry;

typedef struct shared {
        int prod_waiting;
        int con_waiting;
        entry queue[QUEUESIZE];
        int last_produced;
        int last_consumed;
        pid_t prod_pid;
        pid_t con_pid;
        int prod_count;
        int con_count;
} shared;


void report_error(char *error)
{
        fprintf(stderr, "Error: %s\n", error);
}

void usage_exit(char *progname)
{
        fprintf(stderr,
                "Usage: %s <event count> <prod delay int> <con delay int>\n",
                progname);
        exit(-1);
}

void producer_handler(int the_signal)
{
        if (the_signal == SIGUSR1) {
                fprintf(stderr, "Producer received SIGUSR1.\n");
                return;

        } else {
                fprintf(stderr, "Producer: No handler for for signal %d?!\n",
                        the_signal);
                return;
        }
}

void consumer_handler(int the_signal)
{
        if (the_signal == SIGUSR1) {
                fprintf(stderr, "Consumer received SIGUSR1.\n");
                return;
        } else {
                fprintf(stderr, "Consumer: No handler for for signal %d?!\n",
                        the_signal);
                return;
        }
}

void pick_word(char *word)
{
        int pick;

        pick = random() % wordlist_size;

        strcpy(word, wordlist[pick]);
}

void wait_for_producer(shared *s)
{
        struct timespec delay;
        
        delay.tv_sec = 100;
        delay.tv_nsec = 0;

        s->con_waiting = 1;
        
        while (s->con_waiting) {
                nanosleep(&delay, NULL);
        }
}

void wait_for_consumer(shared *s)
{
        struct timespec delay;
        
        delay.tv_sec = 100;
        delay.tv_nsec = 0;

        s->prod_waiting = 1;
        
        while (s->prod_waiting) {
                nanosleep(&delay, NULL);
        }
}

void wakeup_consumer(shared *s)
{
        if (s->con_waiting) {
                s->con_waiting = 0;
                kill(s->con_pid, SIGUSR1);
        }
}

void wakeup_producer(shared *s)
{
        if (s->prod_waiting) {
                s->prod_waiting = 0;
                kill(s->prod_pid, SIGUSR1);
        }
}

void output_word(int c, char *w, FILE *file)
{
        fprintf(file, "Word %d: %s\n", c, w);
	printf("Word %d: %s\n", c, w);
}

int queue_word(char *word, shared *s)
{
        entry *e;
        int current, retval;
        
        current = (s->last_produced + 1) % QUEUESIZE;

        e = &s->queue[current];

        sem_wait(&e->lock);

        if (e->word[0] != '\0') {
                /* consumer hasn't consumed this entry yet */
                sem_post(&e->lock);
                wait_for_consumer(s);
                sem_wait(&e->lock);
        }

        if (e->word[0] != '\0') {
                fprintf(stderr, "ERROR: No room for producer after waiting!\n");
                retval = -1;
                goto done;
        } else {
                strncpy(e->word, word, WORDSIZE);
                s->last_produced = current;
                s->prod_count++;
                wakeup_consumer(s);
                retval = 0;
                goto done;
        }

 done:
        sem_post(&e->lock);
        return retval;
}

int get_next_word(char *word, shared *s)
{
        entry *e;
        int current, retval;

        current = (s->last_consumed + 1) % QUEUESIZE;

        e = &s->queue[current];
        
        sem_wait(&e->lock);

        if (e->word[0] == '\0') {
                /* producer hasn't filled in this entry yet */
                sem_post(&e->lock);
                wait_for_producer(s);
                sem_wait(&e->lock);
        }

        if (e->word[0] == '\0') {
                fprintf(stderr, "ERROR: Nothing for consumer after waiting!\n");
                retval = -1;
                goto done;
        } else {
                strncpy(word, e->word, WORDSIZE);
                e->word[0] = '\0';
                s->last_consumed = current;
                s->con_count++;
                wakeup_producer(s);
                retval = 0;
                goto done;
        }
        
 done:
        sem_post(&e->lock);
        return retval;
}

void producer(shared *s, int event_count, int producer_delay_interval)
{
        char word[WORDSIZE];
        int i;
	struct sigaction signal_handler_struct;
	struct timespec time, time2;
	time.tv_sec = 0;
	time.tv_nsec = 1000;

        memset (&signal_handler_struct, 0, sizeof(signal_handler_struct));
        signal_handler_struct.sa_handler = producer_handler;

        if (sigaction(SIGUSR1, &signal_handler_struct, NULL)) {
            fprintf(stderr, "Producer couldn't register SIGUSR1 handler.\n");
        }

	
        
        for (i=0; i < event_count; i++) {        
                pick_word(word);
                queue_word(word, s);
                if (producer_delay_interval > 0) {
                        if (i % producer_delay_interval == 0) {
				sleep(1);
				//nanosleep(&time,&time2);
                        }
                }
        }

        printf("Producer finished.\n");
        exit(0);
}

void consumer(shared *s, int event_count, int consumer_delay_interval)
{
        char word[WORDSIZE];
        int i;
        struct sigaction signal_handler_struct;
	struct timespec time, time2;
	time.tv_sec = 0;
	time.tv_nsec = 1000;
	
	FILE *file;
	file = fopen("words.txt","a");
        
	memset (&signal_handler_struct, 0, sizeof(signal_handler_struct));
        signal_handler_struct.sa_handler = consumer_handler;

        if (sigaction(SIGUSR1, &signal_handler_struct, NULL)) {
            fprintf(stderr, "Consumer couldn't register SIGUSR1 handler.\n");
        }
        
        for (i=0; i < event_count; i++) {        
                get_next_word(word, s);
                output_word(s->con_count, word, file);
                if (consumer_delay_interval > 0) {
                        if (i % consumer_delay_interval == 0) {
                                sleep(1);
				//nanosleep(&time,&time2);
                        }
                }
        }

        printf("Consumer finished.\n");
        exit(0);
}

void init_shared(shared *s)
{
        int i;
        
        s->con_waiting = 0;
        s->last_consumed = -1;

        s->prod_waiting = 0;
        s->last_produced = -1;
        
        s->prod_pid = -1;
        s->con_pid = -1;

        s->prod_count = 0;
        s->con_count = 0;
                
        for (i=0; i<QUEUESIZE; i++) {
                s->queue[i].word[0] = '\0';
                /* semaphore is shared between processes,
                   and initial value is 1 (unlocked) */
                sem_init(&s->queue[i].lock, 1, 1); 
        }
}

int main(int argc, char *argv[])
{
        int pid, count, prod_interval, con_interval;
        
        shared *s;

        srandom(42);
        
        if (argc < 4) {
                if (argc < 1) {
                        report_error("no command line");
                        usage_exit(argv[0]);
                } else {
                        report_error("Not enough arguments");
                        usage_exit(argv[0]);
                }
        }

        count = atoi(argv[1]);
        prod_interval = atoi(argv[2]);
        con_interval = atoi(argv[3]);

        s = (shared *) mmap(NULL, sizeof(shared),
                             PROT_READ|PROT_WRITE,
                             MAP_SHARED|MAP_ANONYMOUS, -1, 0);
        
        if (s == MAP_FAILED) {
                report_error(strerror(errno));
        }
        
        init_shared(s);
        
        pid = fork();

        if (pid) {
                /* producer */
                s->prod_pid = getpid();
                producer(s, count, prod_interval);
        } else {
                /* consumer */
                s->con_pid = getpid();
                consumer(s, count, con_interval);
        }
        
        /* This line should never be reached */
        return -1;
}
