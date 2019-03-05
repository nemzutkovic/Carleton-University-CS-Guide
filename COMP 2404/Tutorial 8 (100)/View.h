#include "Library.h"

class View
{
  public:
   	View();
   	int mainMenu();
   	void readInfo(int*, string*, string*, int*, string*);
   	void readBookType(string*);
   	void print(string);
};