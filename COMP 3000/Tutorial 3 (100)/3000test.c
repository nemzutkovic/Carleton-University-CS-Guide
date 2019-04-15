/* 3000test.c */
/* v1 Oct. 1, 2017 */
/* Licenced under the GPLv3, copyright Anil Somayaji */
/* You really shouldn't be incorporating parts of this in any other code,
   it is meant for teaching, not production */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <fcntl.h>
#include <errno.h>
#include <string.h>
#include <limits.h>

void report_error(char *error)
{
        fprintf(stderr, "Error: %s\n", error);

        exit(-1);
}

int main(int argc, char *argv[])
{
        struct stat statbuf;
        char *fn, *data, *buffer;
        int fd;
        size_t len, i, count, bytes;
        
        if (argc < 2) {
                if (argc < 1) {
                        report_error("no command line");
                        fprintf(stderr, "Usage: %s <file>\n", argv[0]); 
                } else {
                        report_error("Not enough arguments");
                        fprintf(stderr, "Usage: %s <file>\n", argv[0]); 
                }
        }

        fn = argv[1];

        if (lstat(fn, &statbuf)) {
                report_error(strerror(errno));
        }

        len = statbuf.st_size;
	
	if (statbuf.st_size == 0)
	       	len = PATH_MAX;

	buffer = malloc(len);
	if (buffer == NULL){
		perror("malloc");
		exit(EXIT_FAILURE);
	}

	bytes = readlink(fn, buffer, len);
	if (bytes != -1){
		printf("'%s' points to '%.*s'\n", fn, (int) bytes, buffer);
	}

      	printf(" File %s:\n", fn);
	printf("   inode: %ld\n", statbuf.st_ino);
       	printf("  length: %ld\n", len);        

        if (S_ISREG(statbuf.st_mode)) {
                fd = open(fn, O_RDONLY);
                if (fd == -1) {
                        report_error(strerror(errno));
                }
                data = (char *) mmap(NULL, len,
                                     PROT_READ, MAP_SHARED, fd, 0);
                if (data == MAP_FAILED) {
                        report_error(strerror(errno));
                }

                count = 0;
                for (i=0; i<len; i++) {
                        if (data[i] == 'a') {
                                count++;
                        }
			//Uncomment line below to test memory usage with top
			//if (i % 200000000 == 0) sleep(3); 
                }

                printf(" a count %ld\n", count);

                if (munmap(data, len) == -1) {
                        report_error(strerror(errno));                        
                }
                close(fd);
        }

        return 0;
}
