#include <stdlib.h>
#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>

int main() {

	printf("Process started...\n");
	printf("Loading module...\n");
	system("sudo insmod remember.ko");
	printf("Module loaded successfully.\n");

	printf("First ps output:\n");	
	system("ps");

	printf("Opening remember module for reading/writing...\n");
    	int fd = open("/dev/remember", O_RDWR);
	printf("Module opened successfully.\n");

	printf("Removing module...\n");	
    	system("sudo rmmod remember.ko");

	printf("Module removed successfully.\n");
	
	printf("Second ps output:\n");
	system("ps");

	printf("Process ended.\n");
    	
	return 0;
}
