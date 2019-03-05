import math

old_city_x = 0
old_city_y = 0
total = 0

while True:
	new_city_x = input("X Coordinate of the New City: ")
	new_city_y = input("Y Coordinate of the New City: ")
	if new_city_x == "q" or new_city_y == "q":
		print("The distance from the current city to the last city is: " + str(distance))
		print("The total distance travelled between all cities is: " + str(total))
		break
	distance = math.sqrt(((int(new_city_x) - int(old_city_x))**2) + ((int(new_city_y) - int(old_city_y))**2))
	total = total + distance
	old_city_x = int(new_city_x)
	old_city_y = int(new_city_y)
	print()