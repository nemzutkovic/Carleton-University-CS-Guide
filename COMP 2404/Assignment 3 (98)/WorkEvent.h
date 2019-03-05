#include "Event.h"

//  Class:	  					 WorkEvent
//  Purpose:  					 Creates and stores a work event in the Event Management Program.
//  Role in the Program: Construction/destruction of work event objects. Compares two work event objects based on which is more important (priority).

class WorkEvent : public Event
{
  public:
    WorkEvent(string="Default", int=0);
    virtual bool lessThan(Event*);
};