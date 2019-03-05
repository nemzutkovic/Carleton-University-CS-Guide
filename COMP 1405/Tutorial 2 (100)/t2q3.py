print("(A)ddition")
print("(S)ubtraction")
print("(M)ultiplication")
print("(D)ivision (Long)")
selection = input("Please select an operation from the list above: ")
if selection != "A" and selection != "S" and selection != "M" and selection != "D":
	print("This program does not support the operation: " + '"' + selection + '"' + ".")
else:
	x = int(input("Please provide the 1st integer: "))
	y = int(input("Please provide the 2nd integer: "))
	if selection == "A":
		print(str(x) + " + " + str(y) + " = " + str(x+y))
	elif selection == "S":
		print(str(x) + " - " + str(y) + " = " + str(x-y))
	elif selection == "M":
		print(str(x) + " * " + str(y) + " = " + str(x*y))
	else:
		print(str(x) + " / " + str(y) + " = " + str(round(x/y)) + " with remainder " + str(x%y))