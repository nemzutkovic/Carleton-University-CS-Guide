#include "Book.h"
#include "FictionBook.h"
#include "NonFictionBook.h"
#include "Array.h"

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
    void copy(Array&);
    void format(string& outStr);
  private:
    Node* head;
    Node* tail;
};