#include "FictionBook.h"

FictionBook::FictionBook(int i, string t, string a, int y, string c) : Book(i, t, a, y, c)
{
}

bool FictionBook::lessThan(Book* book)
{
	return author < book->getAuthor(book);
}