#ifndef BOOK_H
#define BOOK_H
#include <iomanip>
#include <iostream>
#include <string>
using namespace std;

class Book
{
	public:
		Book(int=0, string="Unknown", string="Unknown", int=0, string="Unknown"); 
		virtual ~Book();
		void format(string& outStr);
		virtual bool lessThan(Book*) = 0;
		string getAuthor(Book*);
		string getCallNumber(Book*);
	protected:
		int    id;
		string title;
		string author;
		int    year;
		string callnumber;
};
#endif