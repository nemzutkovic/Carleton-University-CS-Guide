#include "Event.h"

Event::Event(string n)
{
  name = n;
}

void Event::setDate(int y, int m, int d, int h, int min, int sec)
{
  date.set(y, m, d, h, min, sec);
}

Date& Event::getDate()
{
  return date;
}

void Event::format(string& outStr)
{
	stringstream ss;
	ss <<setw(20)<<name<<": ";
	outStr += ss.str();
	date.format(outStr);
}