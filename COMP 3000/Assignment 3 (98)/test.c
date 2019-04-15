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


void read_test(void)
{        
        int fd = open_remember();       
	ssize_t c = read(fd, -1, 1024);

        if (c >= 0) {
                printf("read with -1 buffer succeeded\n");
        } else {
                perror("Error reading remember in -1  buffer test");
        }

	c = read(fd, NULL, 1024);
        if (c >= 0) {
                printf("read with NULL buffer succeeded\n");
        } else {
                perror("Error reading remember in NULL  buffer test");
        }
        close(fd);
}

void write_test(void)
{
	int fd = open_remember();      
	ssize_t c = write(fd, -1, 1024);

        if (c >= 0) {
                printf("write with -1 buffer succeeded\n");
        } else {
                perror("Error writing remember in -1  buffer test");
        }
	

        c = read(fd, NULL, 1024);
        if (c >= 0) {
                printf("write with NULL buffer succeeded\n");
        } else {
                perror("Error writing remember in NULL  buffer test");
        }
        close(fd);
}

void llseek_test(void)
{
       char buff1[50];
       char buff2[50];

       system("echo abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ > /dev/remember");

       int fd = open_remember();
      
       lseek(fd, 10, SEEK_SET);
       int c = read(fd, buff1, 1024);
       printf("%s", buff1);
      
       lseek(fd, 0, SEEK_SET);
       lseek(fd, 4, SEEK_CUR);
       lseek(fd, 1, SEEK_CUR);
       
       c = read(fd, buff2, 1024);
       
       printf("%s", buff2);
       
       close(fd);
}

int main(int argc, char *argv)
{
        //read_test();
	//write_test();
        llseek_test();
	return 0;
}
