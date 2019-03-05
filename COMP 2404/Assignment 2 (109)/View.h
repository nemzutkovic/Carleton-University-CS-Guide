#ifndef VIEW_H
#define VIEW_H

#include "Calendar.h"

//  Class:	  					 View
//  Purpose:  					 Controls user i/o in the Simple Event Management Program.
//  Role in the Program: Construction/destruction of view objects. Displays options and takes in user input for the main menu.
// 											 Takes in all the user input for the event information. Prints out the final string to the user/console.				

class View
{
  public:
   	int  mainMenu();
   	void readInfo(string*, int*, int*, int*, int*, int*, int*);
   	void print(string*);
};

#endif