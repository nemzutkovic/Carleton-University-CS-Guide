#ifndef CALENDAR_H
#define CALENDAR_H
#define MAX_ARR_SIZE  128
#include "Event.h"

//  Class:	  				Calendar
//  Purpose:  				Creates and stores a calendar in the Simple Event Management Program. Also stores an array of event pointers inside it.
//  Role in the Program:    Construction of calendar objects. Destruction of calendar objects. Adds event object to the array of event pointers. 
// 							Prints out all the events of the array. Holds the name of a calendar. Tracks of the number of events in each calendar.							

class Calendar
{
  public:
    Calendar(string);
    ~Calendar();
    void add(Event*);
    void printEvents();

  private:
    string calname;
    Event* events[MAX_ARR_SIZE];
    int numberofevents;
};

#endif