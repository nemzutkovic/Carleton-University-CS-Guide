#ifndef DATE_H
#define DATE_H
#include "Time.h"

//  Class:	  				Date
//  Purpose:  				Creates and stores the day, month and year of the event in the Simple Event Management Program. Also stores a time object inside it.
//  Role in the Program:    Construction of date objects. Sets the days, month and year for a date object.
//							Prints out the days, month and year of a date object. Returns the lesser date from two date objects, through comparison. 
//							Tracks whether or not a month is a leap year. Returns the month in string format.

class Date
{
  public:
    Date(int=0, int=0, int=2000, int=0, int=0, int=0);
    ~Date();
    void set(int, int, int, int, int, int);
    void print();
    bool lessThan(Date&);

  private:
    Time timeobject;
    int day;
    int month;
    int year;
    int  lastDayInMonth();
    bool leapYear();
    string getMonthStr();
};

#endif