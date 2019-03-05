#include "Book.h"
#include "FictionBook.h"
#include "NonFictionBook.h"
#include "Array.h"

template <class T>
class List
{

  class Node
  {
    friend class List;
    private:
      T     data;
      Node* next;
      Node* prev;
  };

  public:
    List();
    ~List();
    void add(Book*);
    void copy(Array&);
    void format(string& outStr);
  private:
    Node* head;
    Node* tail;
};

template <class T>
List<T>::List()
{
  head = NULL;
  tail = NULL;
}

template <class T>
List<T>::~List()
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

template <class T>
void List<T>::add(Book* book)
{
  Node *tmpNode;
  Node *currNode;

  tmpNode = new Node;
  tmpNode->data = book;
  tmpNode->next = NULL;
  tmpNode->prev = NULL;

  currNode = head;

  while (currNode != NULL) {
    if (*book < (currNode->data))
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

template <class T>
void List<T>::copy(Array& array)
{
  Node *currNode = head;
  while(currNode != NULL){
    array.add(currNode->data);
    currNode = currNode->next;
  }

}

template <class T>
void List<T>::format(string& outStr)
{
  // Prints out doubly linked list from head to tail.
  Node *currNode = head;
  while(currNode != NULL){
    currNode->data->format(outStr);
    currNode = currNode->next;
  }
  
  Node *currNode2 = tail;
  while(currNode2 != NULL){
    currNode2->data->format(outStr);
    currNode2 = currNode2->prev;
  }

}
