num = int(input("Enter an integer: "))
divisor = 1
divisorsum = 0
count = 0

while (divisor <= num):
	if num % divisor == 0:
		print(divisor)
		divisorsum += divisor
		count += 1
	divisor += 1
print("The sum of the divisors is: " + str(divisorsum))
if count == 2:
	print("The number is prime!")
else:
	print("The number is not prime.")