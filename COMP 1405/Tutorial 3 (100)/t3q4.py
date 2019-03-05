previous = 0
templongest = 0
longest = 0

while True:
	userin = input("Enter a positive integer or 'q' to quit: ")
	if userin == "q":
		break
	else:
		if int(userin) > previous:
			templongest += 1
			if templongest > longest:
				longest = templongest
		else:
			templongest = 1
		previous = int(userin)

print("Length of longest increasing sequence is " + str(longest))