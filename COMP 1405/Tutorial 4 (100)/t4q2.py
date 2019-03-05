x = int(input("How long do you want the memory to be? "))
measurements = []

def measure(newvar):
	measurements.append(newvar)
	if len(measurements) > x:
		measurements.pop(0)

def average():
	sumoflist = 0
	for i in measurements:
		sumoflist += i
	average = sumoflist / len(measurements)
	return average

def min():
	minimum = measurements[0]
	for i in measurements:
		if i < minimum:
			minimum = i
	return minimum

def max():
	maximum = 0
	for i in measurements:
		if i > maximum:
			maximum = i
	return maximum

def isdanger():
	if max() - min() > 10:
		return True
	else:
		return False