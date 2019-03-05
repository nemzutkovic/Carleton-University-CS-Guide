dry = 0

while(dry != 3):
	rain = input("It rained today? ")
	if rain == "True":
		dry = 0
	else:
		dry += 1
		
print("Quick! Water your garden before all the vegetable plants die and you starve to death!")