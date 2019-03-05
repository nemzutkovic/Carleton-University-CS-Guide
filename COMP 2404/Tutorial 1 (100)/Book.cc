#include <iostream>
#include <iomanip>
using namespace std;

#include "Book.h"

Book::Book(int i, string t, string a, int y) 
{ 
  id     = i;
  title  = t;
  author = a;
  year   = y;
}

void Book::setBook(int i, string t, string a, int y) 
{
  id     = i;
  title  = t;
  author = a;
  year   = y;
}

void Book::print()
{
  cout << setw(3)  << id
       <<"  Title: "   << setw(40) << title
       <<";  Author: "  << setw(20) << author
       <<";  Year: "    << year << endl;
}

