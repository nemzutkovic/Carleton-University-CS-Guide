#include "Library.h"

Library::Library()
{
}

void Library::addBook(Book* newbook)
{ 
    linkedlist.add(newbook);    
}

void Library::format(string& outStr)
{
    linkedlist.format(outStr);
}
