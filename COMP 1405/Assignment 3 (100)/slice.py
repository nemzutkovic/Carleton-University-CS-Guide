def slice(source, start, end):
	if (start and end) not in range(0, len(source)): return []
	list = []
	for i in range(start, end + 1): list.append(source[i])
	return list
mylist = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"]
print(slice(mylist, 0, 9))