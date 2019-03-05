#ifndef DATE_H
#define DATE_H

#include "Time.h"

//  Class:               Date
//  Purpose:             Creates and stores the day, month and year of the event in the Event Management Program.
//  Role in the Program: Construction/destruction of Date objects. Sets the day, month and year for a Date object.
//                       Appends the day, month and year of a Date object to the output string. Returns the true/false value between the dates of two Date objects. 
//                       Stores a Time object. Tracks whether or not a month is a leap year. Returns the month in string format.

class Date
{
  public:
    Date(int=0, int=0, int=2000, int=0, int=0, int=0);
    void set(int, int, int, int, int, int);
    void format(string&) const;
    bool operator < (Date&);

  private:
    int    day;
    int    month;
    int    year;
    Time   time;
    int    lastDayInMonth();
    bool   leapYear();
    string getMonthStr() const;
};

#endif