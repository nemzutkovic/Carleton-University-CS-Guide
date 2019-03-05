#include "Event.h"

//  Class:	  					 SchoolEvent
//  Purpose:  					 Creates and stores a school event in the Event Management Program.
//  Role in the Program: Construction/destruction of school event objects. Compares two school event objects based on which is occurs soonest (date/time).

class SchoolEvent : public Event
{
  public:
    SchoolEvent(string="Default", int=0);
    virtual bool lessThan(Event*);
};