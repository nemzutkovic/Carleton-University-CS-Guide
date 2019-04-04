import maxk

information = [
[[18, 14, 1, 2, 14, 11, 7, 5, 15, 17, 12, 10], 3, [18, 17, 15]], 
[[11, 19, 6, 12, 2, 1, 15, 16], 2, [19, 16]], 
[[3, 12, 16, 4, 12, 19, 17, 14], 3, [19, 17, 16]], 
[[18, 0, 7, 6, 19, 6, 15, 11], 2, [19, 18]], 
[[3, 0, 20, 16,18, 16, 8, 15], 3, [20, 18, 16]], 
[[3, 7, 17, 6, 0, 11, 1, 11, 20, 19, 19, 7], 3, [20, 19, 19]], 
[[16, 9, 13, 0, 15, 11, 6, 1], 2, [16, 15]], 
[[12, 1, 1, 20, 7, 10, 10, 5, 12, 20, 6, 14], 5, [20, 20, 14, 12, 12]], 
[[4, 0, 1, 2, 3, 8, 1, 8, 10, 0, 2, 19], 3, [19, 10, 8]], 
[[2, 10, 14, 6, 0, 11, 15, 15, 9, 12, 4], 3, [15, 15, 14]], 
[[2, 16, 16, 14, 2, 16, 5, 3], 3, [16, 16, 16]], 
[[15, 20, 5, 2, 17, 7, 3, 2, 18, 8], 2, [20, 18]], 
[[4, 19, 6, 9, 6, 20, 13, 4, 9], 5, [20, 19, 13, 9, 9]], 
[[11, 0, 12, 10, 7, 4, 11, 6, 16, 6, 7], 5, [16, 12, 11, 11, 10]], 
[[17, 8, 14, 14, 19, 14, 8, 12, 17, 8, 16], 4, [19, 17, 17, 16]], 
[[16, 0, 13, 19, 8, 14, 2, 10, 1], 3, [19, 16, 14]], 
[[10, 2, 1, 7, 20, 13, 11, 9, 1, 10, 17, 15], 3, [20, 17, 15]], 
[[7, 4, 10, 1, 1, 3, 10, 2, 17], 3, [17, 10, 10]], 
[[3, 0, 15, 6, 16, 1, 3, 9, 15, 20, 6], 4, [20, 16, 15, 15]], 
[[18, 16, 18, 17, 19, 8, 11, 17, 7], 4, [19,18, 18, 17]]
]
 
 
correct = 0
incorrect = []
print("Checking maxk function with test lists...")
print()
for info in information:
	l1 = info[0][:]
	k = info[1]
	goal = info[2]
	result = maxk.maxk(l1,k)
	print("List:", l1)
	print("K:", k)
	print("Goal:", goal)
	print("Your result:", result)
	if result == goal:
		correct += 1
		print("Good!")
	else:
		incorrect.append([l1, k, goal, result])
		print("Incorrect!")
	print()
		
print()
print("Your code produced",correct,"out of", len(information),"correct results.")
print()
if len(incorrect) > 0:
	print("The cases for which your program failed were:")
	print()
	for info in incorrect:
		print("List:", info[0])
		print("K:", info[1])
		print("Goal:", info[2])
		print("Your Result:", info[3])
		print()
	