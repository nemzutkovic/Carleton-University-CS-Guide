#ifndef VIEW_H
#define VIEW_H
#include <iostream>
#include "Library.h"

class View
{
  public:
   	View();
   	int mainMenu();
   	void readInfo(int*, string*, string*, int*);
   	void print(string);
};
#endif