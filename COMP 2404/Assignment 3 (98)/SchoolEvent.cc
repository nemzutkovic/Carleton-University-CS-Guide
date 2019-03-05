#include "SchoolEvent.h"

SchoolEvent::SchoolEvent(string n, int pri) : Event(n, pri)
{
}

bool SchoolEvent::lessThan(Event* e)
{
	return getDate().lessThan(e->getDate());
}