#include "Control.h"

void Control::launch()
{
	int menuSelection, day, month, year, hours, minutes, seconds, priority;
	string eventname, output, eventtype, workcalname, schoolcalname;
	
	workcalname = "WORK CALENDAR";
	schoolcalname = "SCHOOL CALENDAR";
	workcalendar.setName(workcalname);
	schoolcalendar.setName(schoolcalname);

	while(true){
		menuSelection = viewobject.mainMenu();
		if(menuSelection == 0){
			break;
		}
		viewobject.readInfo(&eventname, &day, &month, &year, &hours, &minutes, &seconds, &priority);
		viewobject.readEventType(&eventtype);
    if(eventtype == "Work Event"){
				WorkEvent* work = new WorkEvent(eventname, priority);
				work->setDate(day, month, year, hours, minutes, seconds);
				workcalendar.add(work);
			}
		if(eventtype == "School Event"){
				SchoolEvent* school = new SchoolEvent(eventname, priority);
				school->setDate(day, month, year, hours, minutes, seconds);
				schoolcalendar.add(school);
			}
	}

	workcalendar.format(output);
	viewobject.print(&output);

	schoolcalendar.format(output);
	viewobject.print(&output);
}