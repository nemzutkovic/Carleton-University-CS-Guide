#ifndef BOOK_H
#define BOOK_H

#include <string>
using namespace std;

class Book
{
  public:
    Book(int=0, string="Unknown", string="Unknown", int=0); 
    void print();
  private:
    int    id;
    string title;
    string author;
    int    year;
};

#endif

