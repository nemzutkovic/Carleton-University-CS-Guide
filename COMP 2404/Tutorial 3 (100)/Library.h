#ifndef LIBRARY_H
#define LIBRARY_H
#include "Array.h"
using namespace std;

class Library
{    
  public:
    Library();
    void addBook(Book&);
    void printLibrary();

  private:
    Array bookarray;
};
#endif
