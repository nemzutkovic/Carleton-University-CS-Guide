x = int(input("Enter a number (q, Q, or quit to exist): "))
sum = x
min = x
max = x
average = 1
while x == int(x):
	y = input("Enter a number (q, Q, or quit to exist): ")
	if (y == "q") or (y == "Q") or (y == "quit"):
		break
	else:
		y = int(y)
		sum = sum + y
		average = average + 1
		if y < min:
			min = y
		elif y > max:
			max = y
print("Min =", min)
print("Max =", max)
print("Total =", sum)
print("Average =", sum / average)