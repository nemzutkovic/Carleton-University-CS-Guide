#include "Book.h"

Book::Book(int i, string c, string t, string a, int y)
{ 
	id         = i;
	callnumber = c;
	title      = t;
	author     = a;
	year   	   = y;
}

Book::~Book()
{
}

string Book::getAuthor(Book* book)
{
	return book->author;
}

string Book::getCallNumber(Book* book)
{
	return book->callnumber;
}

void Book::format(string& outStr)
{
	stringstream ss;
	ss<<setw(3)<<id<<"  Title: "<< setw(40)<<title<<";  Author: "<< setw(20)<<author<<";  Year: "<<year<<";  Call Number: "<<callnumber<<endl;
  outStr += ss.str();
}

bool Book::lessThan(Book* book)
{
    return year <= book->year;
}