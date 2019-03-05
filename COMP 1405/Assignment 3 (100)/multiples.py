def ismultiple(a,b):
	return b % a == 0
def commonmultiple(a,b,c):
	return ismultiple(a,c) and ismultiple(b,c)
a = int(input("Enter an integer for a: "))
b = int(input("Enter an integer for b: "))
for c in range(1,101):
	if commonmultiple(a,b,c) == True:
		print(c)