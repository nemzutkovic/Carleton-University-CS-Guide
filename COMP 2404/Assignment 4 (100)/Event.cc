#include "Event.h"

Event::Event(string name, int priority)
{
  eventname = name;
  eventpriority = priority;
}

Event::~Event()
{
}

void Event::setDate(int y, int m, int d, int h, int min)
{
  date.set(y, m, d, h, min, 0);
}

Date& Event::getDate()
{
  return date;
}

int Event::getPriority()
{
	return eventpriority;
}

void Event::format(string& outStr) const
{
	stringstream ss;
	ss <<setw(20)<<eventname<<": ";
	outStr += ss.str();
	date.format(outStr);
	stringstream st;
	st <<setw(4)<<eventpriority<<endl;
	outStr += st.str();
}