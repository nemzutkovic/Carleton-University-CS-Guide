#include "defs.h"

/* Global counters used for program */
unsigned char key     = 101;
unsigned char counter = 87;

/*
  Function:  main
  Purpose:   determine flow of program based on command line arguments
  Parameters:
  	in:			 argc
  	in:			 argv[]
    return:  success or failure
*/
int main(int argc, char* argv[]){
	if(argc == 1){ serverChat(); }
	else if(argc == 2){	clientChat(argv[1]); }

	return 0;
}

/*
  Function:  createSocket
  Purpose:   creates a socket
  Parameters:
  	out:		 thissocket
    return:  none
*/
void createSocket(int* thissocket){
	*thissocket = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP);
  if (*thissocket < 0) {
    printf("eek! couldn't open socket\n");
    exit(-1);
  }
}

/*
  Function:  cryptoProcess
  Purpose:   encrypts and decrypts character array
  Parameters:
  	inout:	 buffer
    return:  none
*/

void cryptoProcess(char* buffer){
  for(int i = 0; buffer[i] != '\0'; ++i){
    buffer[i] = buffer[i]^encrypt(counter, key);
    counter += 1;
  }
}

/*
  Function:  sendMessage
  Purpose:   sends message to a client in the local network
  Parameters:
  	in:			 clientSocket
  	out:		 inStr
  	out:	 	 buffer
    return:  none
*/
void sendMessage(int* clientSocket, char* inStr, char* buffer){
  printf("\nYour msg-> ");
  fgets(inStr, MAX_STR, stdin);
  inStr[strlen(inStr)-1] = 0;
  strcpy(buffer, inStr);
  cryptoProcess(buffer);
  send(*clientSocket, buffer, strlen(buffer), 0);
}

/*
  Function:  receiveMessage
  Purpose:   receives a message from a client in the local network
  Parameters:
  	out:		 bytesRcv
  	in:			 clientSocket
  	inout:	 buffer
    return:  none
*/
void receiveMessage(int* bytesRcv, int* clientSocket, char* buffer){
	printf("\n... waiting to receive ...\n");
  *bytesRcv = recv(*clientSocket, buffer, MAX_STR, 0);
  buffer[*bytesRcv] = 0;
  printf("Received-> ");
  for(int i = 0; i < *bytesRcv; ++i){
    printf("%c", buffer[i]);
    printf(" ");
  }
  cryptoProcess(buffer);
  printf("\nReceived-> %s\n", buffer);
}