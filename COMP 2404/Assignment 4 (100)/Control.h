#ifndef CONTROL_H
#define CONTROL_H

#include "View.h"
#include "EventServer.h"

//  Class:	  					 Control
//  Purpose:  					 Launches the Event Management Program.
//  Role in the Program: Launches the program, stores two Calendar objects, one View object and one EventServer object. Integrates the BookServer and Array classes into the program.				

class Control
{
	public:
    Control();
    ~Control();
		void launch();
		
	private:
   	Calendar 		workcalendar;
   	Calendar 		schoolcalendar;
   	View 		 		viewobject;
   	EventServer server;
};

#endif