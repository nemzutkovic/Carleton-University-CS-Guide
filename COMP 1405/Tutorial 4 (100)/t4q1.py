def sortlist(list1, list2):
	output = []
	marker1 = 0
	marker2 = 0

	while list1 and list2:
		if list1[0] < list2[0]:
			output.append(list1[0])
			list1 = list1[1:]
		else:
			output.append(list2[0])
			list2 = list2[1:]

	if list1:
		output = output + list1
	else:
		output = output + list2

	return output

print(sortlist([1,3,5], [2,4,6]))