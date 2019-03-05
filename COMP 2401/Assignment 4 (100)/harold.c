#include "defs.h"

/*
  Function:   serverChat
  Purpose:    Harold opens up the chat line (server) to communicate with Timmy
  Parameters: none
    return:   none
*/
void serverChat(){
  /* Declare Local Variables */
  struct sockaddr_in  myAddr, clientAddr;
  char inStr[MAX_STR], buffer[MAX_STR];
  int bytesRcv, myListenSocket, clientSocket, addrSize;

  /* Setup Server / Connect To Client */
  createSocket(&myListenSocket);
  setupServer(&myAddr);
  bindSocket(&myListenSocket, &myAddr);
  listenTo(&myListenSocket);
  acceptConnection(&addrSize, &clientSocket, &myListenSocket, &clientAddr);

  /* Two-Way Communication */
  while(1){
    receiveMessage(&bytesRcv, &clientSocket, buffer);
    if(strcmp(buffer,"quit") == 0)
      break;
    sendMessage(&clientSocket, inStr, buffer);
    if(strcmp(inStr,"quit") == 0){
      sleep(0.5);
      break;
    }
  }
  /* Close Sockets */
  close(myListenSocket);
  close(clientSocket);
}

/*
  Function:  setupServer
  Purpose:   sets up the address for the server
  Parameters:
    out:     myAddr
    return:  none
*/
void setupServer(struct sockaddr_in* myAddr){
  memset(myAddr, 0, sizeof(*myAddr));
  myAddr->sin_family = AF_INET;
  myAddr->sin_addr.s_addr = htonl(INADDR_ANY);
  myAddr->sin_port = htons((unsigned short) PORT);
}

/*
  Function:  bindSocket
  Purpose:   binds a socket for incoming connections
  Parameters:
    in:      thissocket
    in:      myAddr
    return:  none
*/
void bindSocket(int* thissocket, struct sockaddr_in* myAddr){
  int i = bind(*thissocket, (struct sockaddr *) myAddr, sizeof(*myAddr));
  if (i < 0) {
    printf("eek! couldn't bind socket\n");
    exit(-1);
  }
}

/*
  Function:  listenTo
  Purpose:   opens up socket to listen to incoming connections
  Parameters:
    in:      thissocket
    return:  none
*/
void listenTo(int* thissocket){
  int i = listen(*thissocket, 5);
  if (i < 0) {
    printf("eek! couldn't listen\n");
    exit(-1);
  }
  printf("\nWaiting for connection request...\n");
}

/*
  Function:  acceptConnection
  Purpose:   accepts connection from a client
  Parameters:
    out:     addrSize
    out:     clientSocket
    in:      thissocket
    in:      clientAddr
    return:  none
*/
void acceptConnection(int* addrSize, int* clientSocket, int* thissocket, struct sockaddr_in* clientAddr){
  *addrSize = sizeof(*clientAddr);
  *clientSocket = accept(*thissocket, (struct sockaddr *) clientAddr, addrSize);
  if (clientSocket < 0) {
    printf("eek! couldn't accept the connection\n");
    exit(-1);
  }
  printf("... connection accepted\n");
}