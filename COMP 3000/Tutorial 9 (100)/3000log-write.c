/* 3000log-write.c

   A program to demonstrate TOCTTOU issues

   Usage: 3000log-write <file> <message>

   When run, writes message to the file.  It then also records a
   record of this action to /var/log/comp3000-write.log

   Note the program must be setuid root in order to add entries to
   the log file (which is owned by root).  But, it should only add entries
   to files that the current user can write to.
*/

#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <time.h>

#define LOGFILE "/var/log/3000write.log"

void usage(char *message)
{
        fprintf(stderr, "ERROR: %s\n", message);
        fprintf(stderr, "Usage:  log-append <file> <message>\n");
        exit(-1);
}

void test_root_privileges(void)
{
        if (geteuid() != 0) {
                fprintf(stderr, "Not running with root privileges, exiting.\n");
                exit(-1);
        }
}

char *get_username(void)
{
        char *username;
        char *default_username = "UNKNOWN";

        username = getenv("USER");

        if (username == NULL) {
                username = default_username;
        }

        return username;
}

void log_append(char *username, char *filename)
{
        FILE *f;
	
	f = fopen(LOGFILE, "a");

        if (!f) {
                fprintf(stderr, "Could not open log for writing.\n");
                exit(-1);
        }
        
        fprintf(f, "%s (%d) appended to %s\n", username, getuid(), filename);
        fclose(f);
}

void safe_write(char *filename, char *message)
{
        int fd;
        int allowed;

        if (access(filename, W_OK)) {
                fprintf(stderr, "You aren't allowed to write to %s\n",
                        filename);
                exit(-1);
        }
        
        fd = open(filename, O_WRONLY || O_APPEND);
        if (fd == -1) {
                fprintf(stderr, "Could not open %s for writing.\n", filename);
                exit(-1);
        }
        
        write(fd, message, strlen(message));

	time_t current_time;
	struct tm * timeinfo;
	time (&current_time);
	timeinfo = localtime (&current_time);
	printf("Current local time and date is is %s", asctime(timeinfo));
        
	close(fd);
}


int main(int argc, char *argv[])
{
        char *username, *filename, *message;

        test_root_privileges();

        if (argc < 3) {
                usage("Not enough arguments");
        }

        filename = argv[1];
        message = argv[2];
        
        username = get_username();
        
        safe_write(filename, message);
        log_append(username, filename);
        
        printf("Message written to %s\n", filename);
        
        return 0;
}
