#ifndef LIBRARY_H
#define LIBRARY_H
#include "Array.h"

class Library
{    
    public:
        Library();
        void addBook(Book*);
        void format(string& outStr);

    private:
        Array bookarray;
};
#endif
