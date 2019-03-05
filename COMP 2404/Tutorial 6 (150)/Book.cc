#include "Book.h"

Book::Book(int i, string t, string a, int y) 
{ 
	id     = i;
	title  = t;
	author = a;
	year   = y;
}

void Book::format(string& outStr)
{
	stringstream ss;
	ss << setw(3)  << id
       <<"  Title: "   << setw(40) << title
       <<";  Author: "  << setw(20) << author
       <<";  Year: "    << year << endl;
    outStr += ss.str();
}

bool Book::lessThan(Book* book)
{
    return year <= book->year;
}