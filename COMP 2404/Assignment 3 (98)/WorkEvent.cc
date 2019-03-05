#include "WorkEvent.h"

WorkEvent::WorkEvent(string n, int pri) : Event(n, pri)
{
}

bool WorkEvent::lessThan(Event* e)
{
	return priority < e->getPriority();
}