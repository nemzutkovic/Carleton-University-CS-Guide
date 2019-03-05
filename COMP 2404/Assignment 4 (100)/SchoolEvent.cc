#include "SchoolEvent.h"

SchoolEvent::SchoolEvent(string name, int priority) : Event(name, priority)
{
}

bool SchoolEvent::operator < (Event* e)
{
	return this->getDate() < e->getDate();
}