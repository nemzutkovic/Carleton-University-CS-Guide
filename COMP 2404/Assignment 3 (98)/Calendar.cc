#include "Calendar.h"

Calendar::Calendar(string calname)
{
	calendarname = calname;
}

void Calendar::add(Event* tempevent)
{
	linkedlist.add(tempevent);
}

void Calendar::setName(string& calname)
{
	calendarname = calname;
}

void Calendar::format(string& outStr)
{
	outStr="";
	stringstream ss;
	ss <<endl<<"Calendar: "<<calendarname<<endl;
	outStr += ss.str();
	linkedlist.format(outStr);
}