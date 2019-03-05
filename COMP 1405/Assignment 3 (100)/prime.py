def isprime(x):
	if x == 1: return False
	for i in range(2, x):
		if x % i == 0: return False
	return True
for p in range(1,101):
	if isprime(p) == True:
		print(p)