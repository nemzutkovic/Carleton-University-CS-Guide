#include <iostream>
#include <iomanip>
#include <string>
#include "Library.h"
using namespace std;

Library::Library()
{
}

void Library::addBook(Book* newbook)
{ 
  bookarray.add(newbook);    
}

void Library::printLibrary()
{
  bookarray.printBooks();
}
