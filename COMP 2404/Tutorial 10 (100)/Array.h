#ifndef ARRAY_H
#define ARRAY_H

#include "Book.h"
using namespace std;

#define MAX_SIZE  128


class Array
{
  public:
    Array();
    ~Array();
    void  add(Book*);
    int   getSize();
    Book* get(int);
    void  print();
  private:
    Book* elements[MAX_SIZE];
    int   size;
};

#endif
