light = str(input("What color is the traffic light? "))
speed = float(input("How fast is the car going? "))
distance = float(input("How far to the intersection? "))
if (light == "green") or (light == "yellow" and distance / speed <= 5) or (light == "red" and distance / speed <= 2):
	print("The car should go.")
else:
	print("The car should stop.")