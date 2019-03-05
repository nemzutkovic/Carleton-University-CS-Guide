#ifndef CALENDAR_H
#define CALENDAR_H

#include "List.h"

//  Class:	  					 Calendar
//  Purpose:  					 Creates and stores a calendar in the Simple Event Management Program. 
//  Role in the Program: Construction/destruction of calendar objects. Delegates adding to the List object. 
// 											 Delegates printing to the List object. Stores the calendar name and appends it to the output string. Stores a List object .							

class Calendar
{
  public:
  	Calendar(string="Life Events");
    void add(Event*);
    void format(string&);

  private:
  	string calendarname;
    List   linkedlist;
};

#endif