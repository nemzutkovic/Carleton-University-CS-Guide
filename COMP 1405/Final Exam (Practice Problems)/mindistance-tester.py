
import mindistance
information = [
[[[0, 6], [0, 0], [1, 7], [6, 2], [6, 5], [1, 10]], [0, 0], 0.0], 
[[[6, 6], [2, 1], [4, 4], [6, 7], [4, 7], [3, 3], [0, 10], [0, 7]], [2, 1], 2.23606797749979], 
[[[0, 5], [10, 7], [3, 5], [5, 8], [9, 8], [9, 4], [5, 4]], [0, 5], 5.0],
[[[1, 0], [7, 1], [3, 3], [0, 5], [10, 3], [9, 10], [8, 1], [4, 6]], [1, 0], 1.0], 
[[[1, 7], [0, 4], [0, 2], [4, 3]], [0, 2], 2.0], 
[[[4, 6], [3, 4], [7, 2]], [3, 4], 5.0], 
[[[0, 7], [10, 9], [9, 2], [0, 10], [2, 7]], [0, 7], 7.0], 
[[[4, 10], [9, 3], [9, 2], [6, 3], [10, 10], [9, 0]], [6, 3], 6.708203932499369], 
[[[2, 9], [6, 1], [10, 1], [1, 5], [10, 3], [10, 0], [2, 3]], [2, 3], 3.605551275463989], 
[[[2, 5], [3, 8], [8, 9], [6, 0], [8, 0], [0, 10]], [2, 5], 5.385164807134504]
]

correct = 0
incorrect = []
print("Checking mindistance function with test lists...")
print()
for info in information:
	points = info[0][:]
	goal = info[1]
	mindist = info[2]
	print("Points:", points)
	print("Min Dist:", mindist)
	print("Min Item:", goal)
	result = mindistance.mindistance(info[0])
	print("Your Result:", result)
	if result == goal:
		correct += 1
		print("Good!")
	else:
		incorrect.append([points,goal,mindist,result])
		print("Incorrect!")
	print()
		
print()
print("Your code produced",correct,"out of", len(information),"correct results.")
print()
if len(incorrect) > 0:
	print("The cases for which your program failed were:")
	print()
	for info in incorrect:
		print("Points:", info[0])
		print("Min Dist:", info[2])		
		print("Goal:", info[1])
		print("Your Result:", info[3])
		print()