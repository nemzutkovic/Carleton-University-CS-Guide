#include <iostream>
#include <iomanip>
using namespace std;

#include "Calendar.h"

Calendar::Calendar(string newname)
{
	calname = newname;
    numberofevents = 0;
}

Calendar::~Calendar()
{
	for(int i = 0; i < numberofevents; ++i)
	{
		delete events[i];
	}
}

void Calendar::add(Event* tempevent){
	if(numberofevents == 128){
		cout << endl << "The event could not be added. The Calendar: " << calname << " is full!" << endl;
	}
	else{
		if(numberofevents == 0){
			events[numberofevents] = tempevent;
			++numberofevents;
		}
		else{
			Date tempobj;
			for(int i = numberofevents-1; i>=0; i--)
			{
				tempobj = tempevent->get();
				if(!events[i]->get().lessThan(tempobj))
				{
					events[i+1] = events[i];
					events[i] = tempevent;
				}
				else
				{
					events[i+1] = tempevent;
					break;
				}
			}
			++numberofevents;
		}		
	}
}

void Calendar::printEvents()
{
	cout << endl << "Calendar: " << calname << endl;
	if(numberofevents>0)
	{
		for(int i = 0; i < numberofevents; ++i)
		{
			events[i]->print();
		}
	}
}