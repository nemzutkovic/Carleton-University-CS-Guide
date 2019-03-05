#include "Time.h"

Time::Time(int h, int m, int s)
{
  set(h, m, s);
}

void Time::set(int h,int m,int s)
{
  hours   = ( h >= 0 && h < 24) ? h : 0;
  minutes = ( m >= 0 && m < 60) ? m : 0;
  seconds = ( s >= 0 && s < 60) ? s : 0;
}

bool Time::operator < (Time& t)
{
  return this->convertToSecs() < t.convertToSecs();
}

int Time::convertToSecs()
{
  return (hours*3600 + minutes*60 + seconds);
}

void Time::format(string& outStr) const
{
  stringstream ss;
  ss <<setfill('0')<<setw(2)<<hours<<":"
     <<setfill('0')<<setw(2)<<minutes<<":"
     <<setfill('0')<<setw(2)<<seconds;
  outStr += ss.str();
}