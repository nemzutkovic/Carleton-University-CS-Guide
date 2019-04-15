#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>

int open_remember(void) {

        int fd = open("/dev/remember", O_RDWR);
       
        if (fd < 0) {
                fprintf(stderr, "Could not open the remember device.\n");
                exit(-1);
        }

        return fd;
}


void read_null_test(void) {

        ssize_t c;        
        int fd = open_remember();      

        c = read(fd, -1, 1024);

        if (c >= 0) {
                printf("read with NULL buffer succeeded\n");
        } else {
                perror("Error reading remember in NULL buffer test");
        }

        close(fd);
}


int main(int argc, char *argv)

{
        read_null_test();      

        return 0;
}
