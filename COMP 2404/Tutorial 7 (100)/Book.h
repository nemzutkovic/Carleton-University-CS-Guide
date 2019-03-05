#ifndef BOOK_H
#define BOOK_H
#include <iomanip>
#include <iostream>
#include <string>
using namespace std;

class Book
{
	public:
		Book(int=0, string="Unknown", string="Unknown", int=0); 
		void format(string& outStr);
		bool lessThan(Book*);
	private:
		int    id;
		string title;
		string author;
		int    year;
};
#endif