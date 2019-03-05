#ifndef CONTROL_H
#define CONTROL_H

#include "View.h"

//  Class:	  					 Control
//  Purpose:  					 Launches the entire program.
//  Role in the Program: Launches the program and stores the calendar and view object. 				

class Control
{
	public:
		void launch();
	private:
   	Calendar calobject;
   	View 		 viewobject;
};

#endif