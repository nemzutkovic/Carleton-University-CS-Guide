#include "NonFictionBook.h"

NonFictionBook::NonFictionBook(int i, string c, string t, string a, int y) : Book(i, c, t, a, y)
{
}

bool NonFictionBook::lessThan(Book* book)
{
	return callnumber < book->getCallNumber(book);
}