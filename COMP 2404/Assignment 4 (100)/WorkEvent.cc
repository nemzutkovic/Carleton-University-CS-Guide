#include "WorkEvent.h"

WorkEvent::WorkEvent(string name, int priority) : Event(name, priority)
{
}

bool WorkEvent::operator < (Event* e)
{
	return this->eventpriority < e->getPriority();
}