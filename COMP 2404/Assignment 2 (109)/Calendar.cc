#include "Calendar.h"

Calendar::Calendar(string calname)
{
	calendarname = calname;
}

void Calendar::add(Event* tempevent)
{
	linkedlist.add(tempevent);
}

void Calendar::format(string& outStr)
{
	stringstream ss;
	ss <<"Calendar Name: "<<calendarname<<endl;
	outStr += ss.str();
	linkedlist.format(outStr);
}