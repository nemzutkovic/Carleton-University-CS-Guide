celcius = int(input("Current tremperature in Celcius: "))
print("The current temperature in Celcius is " + str(celcius) + ".")
fahrenheit = round(((9/5)*celcius) + 32, 1)
print("The current temperature in Fahrenheit is " + str(fahrenheit) + ".")
kelvin = celcius + 273
print("The current temperature in Kelvin is " + str(kelvin) + ".")