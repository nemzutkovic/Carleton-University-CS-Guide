#ifndef EVENT_H
#define EVENT_H

#include "Date.h"

//  Class:	  					 Event
//  Purpose:  					 Creates and stores an event in the Event Management Program.
//  Role in the Program:         Construction/destruction of Event objects. Sets name of the event. Delegates setting the Date/Time of a Date object. 
//                               Gets the Date object inside of the Event object. Appends basic punctuation and string formatting to the output string. 
//                               Stores the event name, event priority and a Date object.					     

class Event
{
  public:
    Event(string="Default", int=0);
    virtual ~Event();
    void  setDate(int=0, int=0, int=0, int=0, int=0);
    Date& getDate();
    int   getPriority();
    virtual bool operator < (Event*) = 0;
    void  format(string&) const;

  protected:
    string eventname;
    Date   date;
    int	   eventpriority;
};

#endif