/* 3000random.c */
/* prints out random numbers obtained from system /dev/urandom */
/* (This is much, much better than using rand() or random())! */
/* v1 Oct. 15, 2017 */
/* Licenced under the GPLv3, copyright Anil Somayaji */
/* You really shouldn't be incorporating parts of this in any other code,
   it is meant for teaching, not production */

#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>

#define BUFSIZE 1024

typedef struct rand_state {
        unsigned long buffer[BUFSIZE];
        int current;
        int fd;
} rand_state;

void fill_rand_buffer(rand_state *r)
{
        ssize_t count;

        count = read(r->fd, (void *) &r->buffer,
                     BUFSIZE * sizeof(unsigned long));

        if (count > sizeof(unsigned long)) {
                r->current = (count / sizeof(unsigned long)) - 1;
                /* was %, that was wrong! */
                /* left out -1, that was wrong! */
        } else {
                fprintf(stderr, "Couldn't fill random buffer.\n");
        }
}

void system_rand_init(rand_state *r)
{
        r->fd = open("/dev/urandom", O_RDONLY);

        fill_rand_buffer(r);
}

long system_rand(long max, rand_state *r)
{        
        unsigned long val;

        if (r->current < 0) {
                fill_rand_buffer(r);                
                if (r->current < 0) {
                        /* fill_rand_buffer should have already
                           reported an error */
                        return 0;
                }
        }

        val = r->buffer[r->current];
        r->current--;
        return val % max;
}

int main(int argc, char *argv[])
{
        int count, i;
        long max, x;
        rand_state r;

        if (argc != 3) {
                fprintf(stderr, "Usage: %s <count> <max>\n", argv[0]);
                exit(-1);
        }

        count = atoi(argv[1]);
        max = atol(argv[2]);

        printf("count = %d, max = %ld\n", count, max);
        
        system_rand_init(&r);
        
        for (i = 0; i < count; i++) {
                x = system_rand(max, &r);
                printf("%ld\n", x);
        }
        
        return 0;
}
