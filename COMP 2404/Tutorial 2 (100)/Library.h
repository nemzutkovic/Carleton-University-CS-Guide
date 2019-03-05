#ifndef LIBRARY_H
#define LIBRARY_H
#define MAX_ARR_SIZE 128
#include "Book.h"
using namespace std;

class Library
{    
  public:
    Library();
    void addBook(Book&);
    void printLibrary();

  private:
    Book bookarray[MAX_ARR_SIZE];
    int numberofbooks;
};
#endif
