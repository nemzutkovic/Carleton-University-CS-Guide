#include <iostream>
#include <iomanip>
#include <string>
#include "Library.h"
using namespace std;

Library::Library()
{
    numberofbooks = 0;
}

void Library::addBook(Book& newbook)
{ 
    bookarray[numberofbooks] = newbook;
    ++numberofbooks;
}

void Library::printLibrary()
{
  if(numberofbooks > 0){
    for(int i = 0; i < numberofbooks; ++i){
      bookarray[i].print();
    }
  }
}
