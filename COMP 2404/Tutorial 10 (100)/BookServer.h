#ifndef BOOK_SERVER_H
#define BOOK_SERVER_H

#include <string>

#include "Array.h"

/*
  Class:    BookServer
  Purpose:  simulates cloud-based storage of books

  Member function:  constructor
     Side effects:  creates a set of dynamically allocated books; these
                    books must be deallocated by the client classes,
                    as the destructor does no deallocation

  Member function:  destructor
     Side effects:  prints out the master list of all fiction and
                    non-fiction books

  Member function:  retrieve
          Purpose:  copies books from simulated cloud storage into given arrays
              out:  array of fiction books
              out:  array of non-fiction books

  Member function:  update
          Purpose:  copies books from given arrays to simulated cloud storage
               in:  array of fiction books
               in:  array of non-fiction books
*/

class BookServer
{
  public:
    BookServer();
    ~BookServer();
    void retrieve(Array&, Array&);
    void update(Array&, Array&);

  private:
    Array serverFiction;
    Array serverNonFiction;
};

#endif
