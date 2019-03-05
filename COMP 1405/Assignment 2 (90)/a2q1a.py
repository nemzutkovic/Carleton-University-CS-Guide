light = str(input("What color is the traffic light? "))
speed = float(input("How fast is the car going? "))
distance = float(input("How far to the intersection? "))
if light == "green":
	print("The car should go.")
if light == "yellow":
	if distance / speed <= 5:
		print("The car should go.")
	else:
		print("The car should stop.")
if light == "red":
	if distance / speed <= 2:
		print("The car should go.")
	else:
		print("The car should stop.")
if light != "green":
	if light != "yellow":
		if light != "red":
			print("The car should stop.")