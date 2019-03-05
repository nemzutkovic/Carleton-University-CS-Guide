#include "Event.h"

//  Class:	  					 SchoolEvent
//  Purpose:  					 Creates and stores a school event in the Event Management Program.
//  Role in the Program: Construction/destruction of SchoolEvent objects. Compares two SchoolEvent objects based on which occurs sooner (date/time).

class SchoolEvent : public Event
{
  public:
    SchoolEvent(string="Default", int=0);
    virtual bool operator < (Event*);
};