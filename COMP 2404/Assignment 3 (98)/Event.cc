#include "Event.h"

Event::Event(string n, int pri)
{
  name = n;
  priority = pri;
}

Event::~Event()
{
}

void Event::setDate(int y, int m, int d, int h, int min, int sec)
{
  date.set(y, m, d, h, min, sec);
}

Date& Event::getDate()
{
  return date;
}

int Event::getPriority()
{
	return priority;
}

void Event::format(string& outStr)
{
	stringstream ss;
	ss <<setw(20)<<name<<": ";
	outStr += ss.str();
	date.format(outStr);
	stringstream st;
	st <<setw(4)<<priority<<endl;
	outStr += st.str();
}