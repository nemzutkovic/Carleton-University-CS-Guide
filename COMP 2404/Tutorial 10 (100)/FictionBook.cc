#include "FictionBook.h"

FictionBook::FictionBook(int i, string c, string t, string a, int y) : Book(i, c, t, a, y)
{
}

bool FictionBook::operator<(Book* book)
{
	return this->author < book->getAuthor(book);
}