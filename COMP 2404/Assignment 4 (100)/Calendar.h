#ifndef CALENDAR_H
#define CALENDAR_H

#include "List.h"

//  Class:	  					 Calendar
//  Purpose:  					 Creates and stores a calendar in the Event Management Program. 
//  Role in the Program: Construction/destruction of Calendar objects. Delegates adding, copying and printing to the List object.
// 											 Stores the calendar name and appends it to the output string. Stores a List object of templated pointers.							

class Calendar
{
  public:
  	Calendar(string="DEFAULT CALENDAR");
    void addEvent(Event*);
    void copyEvents(Array&);
    void setName(string&);
    void format(string&);

  private:
  	string 				calendarname;
    List<Event*>  linkedlist;
};

#endif