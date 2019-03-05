#include "View.h"
#include "BookServer.h"

class Control
{
    public:
    	Control();
    	~Control();
    	void launch();
    private:
    	Library scslibraryobject;
    	Library loungelibraryobject;
    	View viewobject;
    	BookServer server;
};