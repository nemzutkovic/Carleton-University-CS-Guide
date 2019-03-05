#include "NonFictionBook.h"

NonFictionBook::NonFictionBook(int i, string t, string a, int y, string c) : Book(i, t, a, y, c)
{
}

bool NonFictionBook::lessThan(Book* book)
{
	return callnumber < book->getCallNumber(book);
}