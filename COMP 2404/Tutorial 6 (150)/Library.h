#ifndef LIBRARY_H
#define LIBRARY_H
#include "List.h"

class Library
{    
  public:
    Library();
    void addBook(Book*);
    void format(string& outStr);

  private:
    List linkedlist;
};
#endif