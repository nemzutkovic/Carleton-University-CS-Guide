maxq = 10
listq = []

def enqueue(i):
	if len(listq) < maxq:
		listq.append(i)
		return True
	elif len(listq) >= maxq: return False

def dequeue():
	if len(listq) > 0:	return listq.pop(0)
	else: return None

def peek():
	if len(listq) > 0: return listq[0]
	else: return None

def isempty():
	if len(listq) <= 0: return True
	else: return False

def getlist():
	return listq
	
def multienqueue(i):
	for x in range(0, len(i)):
		listq.append(i[x])
		if len(listq) == maxq:
			break
	return x + 1
	
def multidequeue(i):
	listq2 = []
	for i in range(i, 0, -1):
		if len(listq) > 0:
			listq2.append(listq.pop(0))
	return listq2