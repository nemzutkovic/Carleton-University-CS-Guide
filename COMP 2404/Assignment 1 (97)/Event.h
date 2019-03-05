#ifndef EVENT_H
#define EVENT_H
#include "Date.h"

//  Class:	  				Event
//  Purpose:  				Creates and stores an event in the Simple Event Management Program. Also stores a date object inside it.
//  Role in the Program:    Construction of event objects. Sets name of the event. Prints out the name of the event object.

class Event
{
  public:
    Event(string="", int=0, int=0, int=0, int=0, int=0, int=0);
    void set(string);
    Date get();
    void print();

  private:
    string eventname;
    Date dateobject;
};

#endif