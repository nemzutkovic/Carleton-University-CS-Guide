#include "Date.h"

Date::Date(int d, int m, int y, int h, int mi, int s)
{
  set(d, m, y, h, mi, s);
}

void Date::set(int d, int m, int y, int h, int mi, int s)
{
  day     = ( ( d > 0 && d <= lastDayInMonth() ) ? d : 0 );
  month   = ( ( m > 0 && m <= 12) ? m : 0 );
  year    = ( ( y > 0) ? y : 0 );
  time.set(h, mi, s);
}

void Date::format(string& outStr)
{
  stringstream ss;
  ss <<right<<setw(9)<<getMonthStr()<<" " 
     <<setw(2)<<setfill('0')<<day<< ", "
     <<setw(4)<<setfill(' ')<<year<<"  ";
  outStr += ss.str();
  time.format(outStr);
}

bool Date::lessThan(Date& d)
{
  if (year < d.year)
    return true;
  else if (year > d.year)
    return false;
  else if (month < d.month)
    return true;
  else if (month > d.month)
    return false;
  else if (day < d.day)
    return true;
  else if (day > d.day)
    return false;
  else 
    return time.lessThan(d.time);
}

int Date::lastDayInMonth()
{
  switch(month)
  {
    case 2:
      if (leapYear())
        return 29;
      else
        return 28;
    case 1:
    case 3:
    case 5:
    case 7:
    case 8:
    case 10:
    case 12:
      return 31;
    default:
      return 30;
  }
}

bool Date::leapYear()
{
  if ( year%400 == 0 ||
       (year%4 == 0 && year%100 != 0) )
    return true;
  else
    return false;
}

string Date::getMonthStr() 
{
  string monthStrings[] = { 
    "January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December" };

  if ( month >= 1 && month <= 12 )
    return monthStrings[month-1];
  else
    return "Unknown";
}