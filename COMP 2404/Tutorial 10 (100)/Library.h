#include "List.h"

class Library
{    
  public:
    Library();
    void addBook(Book*);
    void copyBooks(Array&);
    void format(string& outStr);
  private:
    List<Book*> linkedlist;
};