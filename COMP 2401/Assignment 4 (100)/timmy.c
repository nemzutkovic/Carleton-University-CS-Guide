#include "defs.h"

/*
  Function:  clientChat
  Purpose:   Timmy joins the chatline (client) to communicate with Harold
  Parameters:
    in:      SERVER_IP
    return:  none
*/
void clientChat(char* SERVER_IP){
  /* Check IP */
  checkIP(SERVER_IP);

  /* Declare Local Variables */
  struct sockaddr_in  addr;
  char inStr[80], buffer[80];
  int mySocket, bytesRcv;

  /* Connect To Server */
  createSocket(&mySocket);
  setupClient(&addr, SERVER_IP);
  serverConnect(&mySocket, &addr);

  /* Two-Way Communication */
  while (1) {
    sendMessage(&mySocket, inStr, buffer);
    if(strcmp(inStr, "quit") == 0)
      break;
    receiveMessage(&bytesRcv, &mySocket, buffer);
    if(strcmp(buffer, "quit") == 0)
      break;
  } 
  /* Close Sockets */
  close(mySocket);
}

/*
  Function:  checkIP
  Purpose:   checks if correct IP address is used to chat with the server (Harold)
  Parameters:
    in:      SERVER_IP
    return:  none
*/
void checkIP(char* SERVER_IP){
  if(strcmp(SERVER_IP, "127.0.0.1") != 0){
    printf("Could not connect to chat...\n");
    exit(-1);
  }
}

/*
  Function:  setupClient
  Purpose:   sets up the address for the client
  Parameters:
    out:     addr
    in:      SERVER_IP
    return:  none
*/
void setupClient(struct sockaddr_in* addr, char* SERVER_IP){
  memset(addr, 0, sizeof(*addr));
  addr->sin_family = AF_INET;
  addr->sin_addr.s_addr = inet_addr(SERVER_IP);
  addr->sin_port = htons((unsigned short) PORT);
}

/*
  Function:  serverConnect
  Purpose:   connects client to server
  Parameters:
    in:      thissocket
    in:      myAddr
    return:  none
*/
void serverConnect(int* thissocket, struct sockaddr_in* myAddr){
  printf("\nConnecting to server...\n");
  int i = connect(*thissocket, (struct sockaddr *) myAddr, sizeof(*myAddr));
  if (i<0) {
    printf("Could not connect to server!\n");
    exit(-1);
  }
  printf("... connected\n");
}