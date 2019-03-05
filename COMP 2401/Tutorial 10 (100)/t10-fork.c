#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <unistd.h>

void handleSig1(int);
void handleSig2(int);

int main(int argc, char* argv[])
{
	int choice;
    int pid = fork();

	if(pid == 0){
	  printf("Process is a child.\n");
	  signal(SIGUSR1, handleSig1);
      signal(SIGUSR2, handleSig2);

	  while(1){
        sleep(1);
	    printf("Child is sleeping...\n");
	  }
	  
	}
	else{
	  printf("Process is a parent.\n");
      choice = -1;
	  while(1){
        printf("Enter 0 for SIGINT, 1 for SIGUSR1, 2 for SIGUSR2: \n");
        scanf("%d", &choice);
       if(choice == 0){
          kill(pid, SIGINT); 
          break;
       }
       else if(choice == 1){
          kill(pid, SIGUSR1);
       }
       else if(choice == 2){
          kill(pid, SIGUSR2);
       }
     }
    }
	
  
  return 0;
}


void handleSig1(int i) 
{	
	printf("Received SIGUSR1\n"); 
}


void handleSig2(int i)
{
	printf("Received SIGUSR2\n");
}
