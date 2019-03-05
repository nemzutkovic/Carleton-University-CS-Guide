#include "Event.h"

//  Class:	  					 WorkEvent
//  Purpose:  					 Creates and stores a WorkEvent in the Event Management Program.
//  Role in the Program: Construction/destruction of WorkEvent objects. Compares two WorkEvent objects based on which is more important (priority).

class WorkEvent : public Event
{
  public:
    WorkEvent(string="Default", int=0);
    virtual bool operator < (Event*);
};