#ifndef TIME_H
#define TIME_H

#include <iomanip>
#include <iostream>
using namespace std;

//  Class:               Time
//  Purpose:             Creates and stores the hours, minutes, seconds of the event in the Simple Event Management Program.
//  Role in the Program: Construction/destruction of time objects. Sets the hours, minutes and seconds for a time object.
//                       Appends the hours, minutes and seconds to the output string. Returns the lesser time from two time objects, through comparison done in seconds. 

class Time
{
  public:
    Time(int=0, int=0, int=0);
    void set(int, int, int);
    void format(string&);
    bool lessThan(Time&);

  private:
    int hours;
    int minutes;
    int seconds;
    int convertToSecs();
};

#endif