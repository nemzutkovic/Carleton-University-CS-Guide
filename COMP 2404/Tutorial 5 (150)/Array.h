#ifndef ARRAY_H
#define ARRAY_H
#define MAX_ARR_SIZE 128
#include "Book.h"

class Array
{
    public:
        Array();
        ~Array();
        void add(Book*);
        void format(string& outStr);
    private:
        Book* elements[MAX_ARR_SIZE];
        int size;
};
#endif