#ifndef LIST_H
#define LIST_H

#include "SchoolEvent.h"
#include "WorkEvent.h"
#include "Array.h"

//  Class:	  					 List
//  Purpose:  					 Creates and stores a templated linked list in the Event Management Program.
//  Role in the Program: Construction/destruction of List objects (linked lists). Adds and copies Event objects to and from the linked list. Delegates the printing of each Event object.

template <class T>
class List
{
  template <class V>
  class Node
  {
    friend class List;
    private:
      T     data;
      Node* next;
  };

  public:
    List();
    ~List();
    void add(Event*);
    void copy(Array&);
    void format(string&);

  private:
    Node<T>* head;
};

template <class T>
List<T>::List()
{
  head = NULL;
}

template <class T>
List<T>::~List()
{
  Node<T> *currNode, *nextNode;

  currNode = head;

  while (currNode != NULL) {
    nextNode = currNode->next;
    delete currNode->data;
    delete currNode;
    currNode = nextNode;
  }
}

template <class T>
void List<T>::add(Event* event)
{
  Node<T>* tmpNode;
  Node<T>* currNode;
  Node<T>* prevNode;

  tmpNode = new Node<T>;
  tmpNode->data = event;
  tmpNode->next = NULL;

  currNode = head;
  prevNode = NULL;

  while (currNode != NULL) {
    if (*event < (currNode->data))
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

template <class T>
void List<T>::copy(Array& array)
{
  Node<T>* currNode = head;
  while(currNode != NULL){
    array.add(currNode->data);
    currNode = currNode->next;
  }

}

template <class T>
void List<T>::format(string& outStr)
{
  Node<T>* currNode = head;
  while(currNode != NULL){
    currNode->data->format(outStr);
    currNode = currNode->next;
  } 
}

#endif