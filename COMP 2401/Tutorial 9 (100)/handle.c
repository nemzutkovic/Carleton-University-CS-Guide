#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>

void handleSig1(int);
void handleSig2(int);

volatile int running;

int main(int argc, char* argv[])
{
  //int i;
  
  running = 1;
  
  signal(SIGUSR1, handleSig1);
  signal(SIGUSR2, handleSig2);

  while (running == 1)
    sleep(1);

  printf("we made it\n");

}


void handleSig1(int i)
{
  printf("Received SIGUSR1\n");
}


void handleSig2(int i)
{
   printf("Received SIGUSR2\n");
}
