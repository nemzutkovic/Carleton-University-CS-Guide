#ifndef TIME_H
#define TIME_H

//  Class:	  				Time
//  Purpose:  				Creates and stores the hours, minutes, seconds of the event in the Simple Event Management Program.
//  Role in the Program:    Construction of time objects. Sets the hours, minutes and seconds for a time object.
//							Prints out the hours, minutes and seconds of a time object. Returns the lesser time from two time objects, through comparison. 

class Time
{
  public:
    Time(int=0, int=0, int=0);
    void set(int, int, int);
    void print();
    bool lessThan(Time&);

  private:
    int hours;
    int minutes;
    int seconds;
    int convertToSecs();
};

#endif