#include "Library.h"

Library::Library()
{
}

void Library::addBook(Book* newbook)
{ 
  linkedlist.add(newbook);    
}

void Library::copyBooks(Array& array)
{
	linkedlist.copy(array);
}

void Library::format(string& outStr)
{
  linkedlist.format(outStr);
}