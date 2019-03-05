#ifndef LIST_H
#define LIST_H
#include "Book.h"

class List
{
  class Node
  {
    friend class List;
    private:
      Book*    data;
      Node*    next;
      Node*    prev;
  };

  public:
    List();
    ~List();
    void add(Book*);
    void format(string& outStr);

  private:
    Node* head;
    Node* tail;
};
#endif