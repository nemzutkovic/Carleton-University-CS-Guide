#ifndef CONTROL_H
#define CONTROL_H
#include "Library.h"
#include "View.h"

class Control
{
    public:
    	Control();
    	void launch();
    private:
    	Library libraryobject;
    	View viewobject;
};
#endif