#include <iostream>
using namespace std;

#include "List.h"

List::List()
{
  head = NULL;
  tail = NULL;
}

List::~List()
{
  Node *currNode, *nextNode;

  currNode = head;

  while (currNode != NULL) {
    nextNode = currNode->next;
    delete currNode->data;
    delete currNode;
    currNode = nextNode;
  }
}

void List::add(Book* book)
{
  Node* tmpNode;
  Node* currNode;
  Node* prevNode;

  tmpNode = new Node;
  tmpNode->data = book;
  tmpNode->next = NULL;
  tmpNode->prev = NULL;

  currNode = head;
  prevNode = NULL;

  while (currNode != NULL) {
    if (book->lessThan(currNode->data))
      break;
    prevNode = currNode;
    currNode = currNode->next;
  }

  if (prevNode == NULL) {
    head = tmpNode;
    tail = tmpNode;
  }
  else {
    prevNode->next = tmpNode;
    tmpNode->prev = prevNode;
    tmpNode->next = currNode;
  }
  
  if(tmpNode->next == NULL){
    tail = tmpNode;
  }
  else{
    currNode->prev = tmpNode;
  }

  tmpNode->next = currNode;

}

void List::format(string& outStr)
{
    // Prints out doubly linked list from head to tail.
    Node* currNode = head;
    while(currNode != NULL){
      currNode->data->format(outStr);
      currNode = currNode->next;
    }
    
    // Prints out doubly linked list from tail to head.
    
    Node* currNode2 = tail;
    while(currNode2 != NULL){
      currNode2->data->format(outStr);
      currNode2 = currNode2->prev;
    }
    
}