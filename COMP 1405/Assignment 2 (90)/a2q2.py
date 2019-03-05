x = input("Enter a number you want the factorial of: ")
while x == "0" or x.isdigit() == False:
	print("Invalid input.")
	x = input("Enter a number you want the factorial of: ")
x = int(x)
startvalue = x
factorial = x
while x > 1:
	x = x - 1
	factorial = factorial * x
print("The factorial of", startvalue, "is", factorial)