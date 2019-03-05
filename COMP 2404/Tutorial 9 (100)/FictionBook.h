#include "Book.h"

class FictionBook : public Book
{
  public:
    FictionBook(int=0, string="Unknown", string="Unknown", string="Unknown", int=0);
    virtual bool lessThan(Book*);
};