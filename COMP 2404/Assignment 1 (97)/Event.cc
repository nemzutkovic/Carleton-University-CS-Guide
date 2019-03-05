#include <iostream>
#include <iomanip>
using namespace std;

#include "Event.h"

Event::Event(string ename, int day, int month, int year, int hours, int minutes, int seconds)
{
  set(ename);
  dateobject.set(day, month, year, hours, minutes, seconds);
}

void Event::set(string ename)
{
	eventname = ename;
}

Date Event::get()
{
	return dateobject;
}

void Event::print()
{
	cout << setw(20) << eventname << ": ";
	dateobject.print();
}