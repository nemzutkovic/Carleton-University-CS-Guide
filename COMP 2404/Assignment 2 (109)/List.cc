#include "List.h"

List::List()
{
  head = NULL;
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

void List::add(Event* event)
{
  Node* tmpNode;
  Node* currNode;
  Node* prevNode;

  tmpNode = new Node;
  tmpNode->data = event;
  tmpNode->next = NULL;

  currNode = head;
  prevNode = NULL;

  while (currNode != NULL) {
    if (event->getDate().lessThan(currNode->data->getDate()))
      break;
    prevNode = currNode;
    currNode = currNode->next;
  }

  if (prevNode == NULL) {
    head = tmpNode;
  }
  else {
    prevNode->next = tmpNode;
    tmpNode->next = currNode;
  }

  tmpNode->next = currNode;

}

void List::format(string& outStr)
{
  Node* currNode = head;
  while(currNode != NULL){
    currNode->data->format(outStr);
    currNode = currNode->next;
  } 
}