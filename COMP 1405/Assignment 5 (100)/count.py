def count(input):
	frequency = {}
	sortedlist = []
	newlist = []
	for i in input:
		count = frequency.get(i,0)
		frequency[i] = count + 1
	sortedlist = sorted(frequency)
	for num in sortedlist:
		for i in range(frequency[num]):
			newlist.append(num)
	return newlist