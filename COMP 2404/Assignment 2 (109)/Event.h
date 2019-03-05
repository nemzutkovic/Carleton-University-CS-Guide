#ifndef EVENT_H
#define EVENT_H

#include "Date.h"

//  Class:	  					 Event
//  Purpose:  					 Creates and stores an event in the Simple Event Management Program.
//  Role in the Program: Construction/destruction of event objects. Sets name of the event. Delegates setting the date/time of the date object. Gets the date object inside of the Event object. 
//											 Appends basic punctuation and string formatting to the output string. Stores the event name and\ a date object.

class Event
{
  public:
    Event(string="Default");
    void  setDate(int=0, int=0, int=0, int=0, int=0, int=0);
    Date& getDate();
    void  format(string&);

  private:
    string name;
    Date   date;
};

#endif