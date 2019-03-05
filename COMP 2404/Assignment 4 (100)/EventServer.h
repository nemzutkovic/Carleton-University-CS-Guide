#ifndef EVENT_SERVER_H
#define EVENT_SERVER_H

#include <string>

#include "Array.h"

/*
  Class:    EventServer
  Purpose:  simulates cloud-based storage of events

  Member function:  constructor
     Side effects:  creates a set of dynamically allocated events; these
                    events must be deallocated by the client classes,
                    as the destructor does no deallocation

  Member function:  destructor
     Side effects:  prints out the master list of all school and work events

  Member function:  retrieve
          Purpose:  copies events from simulated cloud storage into given arrays
              out:  array of school events
              out:  array of work events

  Member function:  update
          Purpose:  copies events from given arrays to simulated cloud storage
               in:  array of school events
               in:  array of work events
*/

class EventServer
{
  public:
    EventServer();
    ~EventServer();
    void retrieve(Array&, Array&);
    void update(Array&, Array&);

  private:
    Array serverWork;
    Array serverSchool;
};

#endif
