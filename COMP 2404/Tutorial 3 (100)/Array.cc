#include <iostream>
#include "Array.h"

Array::Array()
{
  size = 0;
}

void Array::add(Book& tempbook)
{
  elements[size] = tempbook;
  ++size;
}

void Array::printBooks()
{
  if(size > 0){
    for(int i = 0; i < size; ++i){
      elements[i].print();
    }
  }
}