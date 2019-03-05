#This code works like a basic queue data structure.
#The addend function adds a value to the end of the list, the removefront function takes a value out from the front of the list.

def addend(value):
	list.append(value)
	
def removefront():
	return list.pop(0)
	
#this function has to perform linear search to determine if an item is in the list or not, which is O(n).
#You will implement a solution that allows you to search using O(1) operations
def containslinear(value):
	return value in list
	
list = []