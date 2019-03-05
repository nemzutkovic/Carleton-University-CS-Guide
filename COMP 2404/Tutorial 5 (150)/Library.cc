#include "Library.h"

Library::Library()
{
}

void Library::addBook(Book* newbook)
{ 
    bookarray.add(newbook);    
}

void Library::format(string& outStr)
{
    bookarray.format(outStr);
}
