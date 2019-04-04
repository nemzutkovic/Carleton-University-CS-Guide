import binarysearch

information = [
[[2, 7, 8, 28, 32, 33, 46, 48, 49, 50], 2, 0],
[[1, 6, 7, 11, 13, 19, 26, 28, 31, 33, 41, 43, 49], 52, -1],
[[0, 1, 4, 11, 13, 14, 15, 18, 32, 33, 43, 46, 47, 49], 1, 1],
[[9, 14, 22, 23, 29, 34, 43, 44, 45, 47], 1, -1],
[[4, 10, 22, 24, 25, 27, 33, 35, 47, 49, 50], 10, 1],
[[7, 9, 19, 28, 31, 38, 41, 47, 50], -10, -1],
[[8, 10, 13, 22, 24, 36, 44, 48], -8, -1],
[[0, 1, 25, 28, 33, 39, 44, 46, 50], 33, 4],
[[7, 10, 11, 12, 17, 18, 23, 33, 39, 40, 44, 45, 49, 50], 10, 1],
[[2, 3, 4, 6, 7, 12, 13, 20, 22, 23, 25, 31, 33, 36, 41, 49], 41, 14],
[[0, 2, 6, 11, 12, 15, 24, 25, 32, 33, 35, 38, 43, 46], 0, 0],
[[11, 15, 24, 31, 32, 33, 36, 41, 42], 36, 6],
[[3, 4, 7, 13, 28, 34, 39, 41, 43, 44, 48], 44, 9],
[[0, 19, 21, 25, 29, 33, 34, 36, 42], 33, 5],
[[2, 3, 5, 7, 11, 12, 13, 19, 25, 26, 35, 48], 3, 1],
[[0, 2, 5, 7, 9, 12, 17, 28, 31, 33, 37, 40, 44], 5, 2],
[[1, 2, 4, 20, 27, 37, 42, 43, 45, 49], 45, 8],
[[8, 9, 14, 18, 28, 30, 35, 36, 40, 43], 28, 4],
[[7, 12, 13, 14, 16, 23, 24, 29, 32, 33, 41, 46, 49], 39, -1],
[[6, 7, 13, 16, 19, 22, 24, 32, 37, 39, 41, 44, 46, 49], 39, 9]
]

correct = 0
incorrect = []
print("Checking iterative binary search function with test lists...")
print()
for info in information:
	l1 = info[0][:]
	searchnum = info[1]
	goal = info[2]
	result = binarysearch.iterativesearch(l1, searchnum)
	print("List:", l1)
	print("Item:", searchnum)
	print("Goal:", goal)
	print("Your result:", result)
	if result == goal:
		correct += 1
		print("Good!")
	else:
		incorrect.append([l1, searchnum, goal, result])
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
		print("Item:", info[1])
		print("Goal:", info[2])
		print("Your Result:", info[3])
		print()
	
correct = 0
incorrect = []
print("Checking recursive binary search function with test lists...")
print()
for info in information:
	l1 = info[0][:]
	searchnum = info[1]
	goal = info[2]
	result = binarysearch.recursivesearch(l1, searchnum)
	print("List:", l1)
	print("Item:", searchnum)
	print("Goal:", goal)
	print("Your result:", result)
	if result == goal:
		correct += 1
		print("Good!")
	else:
		incorrect.append([l1, searchnum, goal, result])
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
		print("Item:", info[1])
		print("Goal:", info[2])
		print("Your Result:", info[3])
		print()