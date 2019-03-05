#include "Control.h"

Control::Control()
{
	Array schoolarray;
	Array workarray;

	server.retrieve(schoolarray, workarray);

	for(int i = 0; i < schoolarray.getSize(); ++i){
		schoolcalendar.addEvent(schoolarray.get(i));
	}

	for(int i = 0; i < workarray.getSize(); ++i){
		workcalendar.addEvent(workarray.get(i));
	}

}

Control::~Control()
{
	Array schoolarray;
	Array workarray;
	schoolcalendar.copyEvents(schoolarray);
	workcalendar.copyEvents(workarray);
	server.update(schoolarray, workarray);
}

void Control::launch()
{
	int menuSelection, day, month, year, hours, minutes, priority;
	string eventname, output, eventtype;
	string workcalname = "WORK CALENDAR";
	string schoolcalname = "SCHOOL CALENDAR";

	workcalendar.setName(workcalname);
	schoolcalendar.setName(schoolcalname);

	while(true){
		menuSelection = viewobject.mainMenu();
		if(menuSelection == 0){
			break;
		}
		viewobject.readInfo(&eventname, &day, &month, &year, &hours, &minutes, &priority);
		viewobject.readEventType(&eventtype);
    if(eventtype == "Work Event"){
				WorkEvent* work = new WorkEvent(eventname, priority);
				work->setDate(day, month, year, hours, minutes);
				workcalendar.addEvent(work);
			}
		if(eventtype == "School Event"){
				SchoolEvent* school = new SchoolEvent(eventname, priority);
				school->setDate(day, month, year, hours, minutes);
				schoolcalendar.addEvent(school);
			}
	}

	schoolcalendar.format(output);
	viewobject.print(&output);

	workcalendar.format(output);
	viewobject.print(&output);

}