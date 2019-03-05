#ifndef CONTROL_H
#define CONTROL_H

#include "View.h"

//  Class:	  					 Control
//  Purpose:  					 Launches the entire Event Management Program.
//  Role in the Program: Launches the program and stores two calendar objects and one view object. 				

class Control
{
	public:
		void launch();
		
	private:
   	Calendar workcalendar;
   	Calendar schoolcalendar;
   	View 		 viewobject;
};

#endif