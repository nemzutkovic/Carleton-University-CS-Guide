#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

#define PORT 60002
#define MAX_STR 80

void serverChat();
void clientChat(char*);
void createSocket(int*);
void setupServer(struct sockaddr_in*);
void setupClient(struct sockaddr_in*, char*);
void bindSocket(int*, struct sockaddr_in*);
void listenTo(int*);
void checkIP(char*);
void serverConnect(int*, struct sockaddr_in* myAddr);
void acceptConnection(int*, int*, int*, struct sockaddr_in*);
void sendMessage(int*, char*, char*);
void receiveMessage(int*, int*, char*);
void cryptoProcess(char*);
unsigned char encrypt(unsigned char, unsigned char);