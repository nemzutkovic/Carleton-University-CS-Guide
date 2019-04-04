import sequencelength

information = [
[[49, 15, 41, 6, 25, 21, 36, 9, 45, 41, 31, 48, 33, 20, 36], 2], 
[[22, 14, 48, 50, 41, 46, 11, 34, 47, 5, 29, 33, 30, 19, 25], 3], 
[[9, 46, 25, 44, 49, 26, 24, 8, 24, 35, 38, 6, 32, 21, 39], 4], 
[[25, 45, 39, 40, 7, 24, 38, 29, 7, 32, 6, 20, 8, 24, 12], 3], 
[[46, 3, 28, 46, 5, 11, 49, 1, 36, 43, 6, 27, 22, 48, 5], 3], 
[[13, 29, 20, 15, 13, 26, 4, 1, 32, 32, 17, 19, 29, 46, 11], 4], 
[[38, 10, 27, 45, 25, 18, 28, 37, 41, 30, 36, 44, 18, 16, 29], 4], 
[[24, 34, 17, 46, 6, 17, 26, 0, 40, 41, 5, 38, 16, 29, 21], 3], 
[[41, 23, 22, 9, 24, 40, 12, 14, 39, 48, 50, 33, 40, 32, 5], 5], 
[[12, 7, 29, 9, 47, 22, 34, 40, 49, 12, 33, 8, 36, 21, 42], 4], 
[[40, 8, 17, 35, 36, 35, 4, 2, 43, 4, 19, 20, 0, 13, 11], 4], 
[[1, 16, 10, 12, 19, 48, 5, 21, 13, 35, 10, 33, 8, 19, 38], 4], 
[[44, 18, 29, 48, 27, 17, 35, 37, 50, 30, 21, 23, 26, 44, 45], 5], 
[[19, 38, 47, 19, 14, 32, 3, 13, 29, 12, 4, 15, 32, 40, 42], 5], 
[[5, 33, 40, 5, 25, 42, 36, 24, 39, 50, 20, 19, 33, 15, 42], 3], 
[[28, 50, 44, 15, 21, 2, 18, 41, 18, 15, 21, 1, 4, 28, 12], 3], 
[[25, 5, 14, 47, 29, 50, 8, 32, 48, 30, 48, 14, 10, 13, 29], 3], 
[[1, 2, 3, 4, 5, 6, 7, 8, 9, 10], 10], 
[[], 0], 
[[5], 1]
]


correct = 0
incorrect = []
print("Checking sequencelength function with test lists...")
print()
for info in information:
	l1 = info[0][:]
	goal = info[1]
	result = sequencelength.sequencelength(l1)
	print("List:", l1)
	print("Goal:", goal)
	print("Your result:", result)
	if result == goal:
		correct += 1
		print("Good!")
	else:
		incorrect.append([l1, goal, result])
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
		print("Goal:", info[1])
		print("Your Result:", info[2])
		print()
	