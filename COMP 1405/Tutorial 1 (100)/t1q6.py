# 1 - The numbers are 21 and 23.

# 2 - Starting with the number in question, you must divide it by two, and then either subtract or add one.

# 3
magicnum = 44
num1 = magicnum/2 - 1
num2 = magicnum/2 + 1

if num1 % 2 == 1 and num2 % 2 == 1:
	print("The two numbers are: " + str(num1) + " and " + str(num2))
else:
	print("No two consecutive numbers for " + str(magicnum) + " exists.")

# 4 / 5
# Go through entire list one number at a time.
# Keep track of the max number (n). 
# If the current element is greater than n, overwrite n with the current element, otherwise proceed to the next number.

# 6
# Draw the base of the house with one horizontal line.
# Draw the sides of the house with two vertical lines. Each vertical line will connect to an end point of the bottom line.
# Draw the ceiling of the house by connnecting the top points of the vertical lines. You have now formed a square.
# Draw a 45 degree line from the top left corner of the square, so that it is extending above the top vertical line. Ensure that the line only goes halfway.
# Draw another 45 degree line from the top right corner of the square, so that it connects with the other 45 degree line.
# You have just drawn the frame of your house!