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

  tmpNode = new Node;
  tmpNode->data = book;
  tmpNode->next = NULL;
  tmpNode->prev = NULL;

  currNode = head;

  while (currNode != NULL) {
    if (book->lessThan(currNode->data))
      break;
    currNode = currNode->next;
  }

  if(currNode == NULL){
    if(head == NULL){
      head = tmpNode;
      tail = tmpNode;
    }
    else{
      tail->next = tmpNode;
      tmpNode->prev = tail;
      tail = tmpNode;
    }
  }
  else{
    if(currNode == head){
      tmpNode->next = head;
      head->prev = tmpNode;
      head = tmpNode;
    }
    else{
      tmpNode->prev = currNode->prev;
      tmpNode->prev->next = tmpNode;
      tmpNode->next = currNode;
      currNode->prev = tmpNode;
    }
}

}

void List::format(string& outStr)
{
    // Prints out doubly linked list from head to tail.
    Node* currNode = head;
    while(currNode != NULL){
      currNode->data->format(outStr);
      currNode = currNode->next;
    }
    
    //Prints out doubly linked list from tail to head.
    Node* currNode2 = tail;
    while(currNode2 != NULL){
      currNode2->data->format(outStr);
      currNode2 = currNode2->prev;
    }
    
}