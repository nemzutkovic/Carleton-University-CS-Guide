#ifndef LIST_H
#define LIST_H

#include "Event.h"

//  Class:	  					 List
//  Purpose:  					 Creates and stores a linked list in the Simple Event Management Program.
//  Role in the Program: Construction/destruction of linked list objects. Adds an event object to the linked list. Delegates the printing of each event object.

class List
{
  class Node
  {
    friend class List;
    private:
      Event*  data;
      Node*   next;
  };

  public:
    List();
    ~List();
    void add(Event*);
    void format(string&);

  private:
    Node* head;
};

#endif