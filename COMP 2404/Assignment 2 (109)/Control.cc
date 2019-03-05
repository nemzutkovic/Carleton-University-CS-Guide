#include "Control.h"

void Control::launch()
{
	int menuSelection, day, month, year, hours, minutes, seconds;
	string event, output;

	while(true){
		menuSelection = viewobject.mainMenu();
		if(menuSelection == 0){
			break;
		}
		viewobject.readInfo(&event, &day, &month, &year, &hours, &minutes, &seconds);
    Event* eventobject = new Event(event);
    eventobject->setDate(day, month, year, hours, minutes, seconds);
    calobject.add(eventobject);
	}

	calobject.format(output);
	viewobject.print(&output);
}