"""
README
- Ensure you have at least Python 3 installed on your device.
- Type the following command on your terminal in the directory where this file is located:
	- Windows: python comp3007_w19_101085982_a1_1.py
	- Mac:     python3 comp3007_w19_101085982_a1_1.py
- Note: A Snake Case string "uses only lowercase letters and each word is seperated by an underscore".
"""

### PART A ###
def validateString(string):
	if not string:
		return True
	elif string[0] != '_' and ord(string[0]) < ord('a') or ord(string[0]) > ord('z'):
		return False
	else:
		return validateString(string[1:])

### PART B ###
def underscoreIndex(string):
	if underscoreExistence(string) == False:
		return -1
	else:
		if string[0] == '_':
			return 0
		else:
			return 1 + underscoreIndex(string[1:])

### PART B - RECURSIVE HELPER ###
def underscoreExistence(string):
	if not string:
		return False
	elif string[0] == '_':
		return True
	else:
		return underscoreExistence(string[1:])

### PART C ###
def stringToList(string):
	if not string or underscoreIndex(string) < 0:
		return [string]
	else:
		return [string[:underscoreIndex(string)]] + stringToList(string[underscoreIndex(string) + 1:])

### PART D ###
def vowelPosition(string):
	if vowelExistence(string) == False:
		return -1
	else:
		if string[0] == 'a' or string[0] == 'e' or string[0] == 'i' or string[0] == 'o' or string[0] == 'u':
			return 0
		else:
			return 1 + vowelPosition(string[1:])

### PART D - RECURSIVE HELPER ###
def vowelExistence(string):
	if not string:
		return False
	elif string[0] == 'a' or string[0] == 'e' or string[0] == 'i' or string[0] == 'o' or string[0] == 'u':
		return True
	else:
		return vowelExistence(string[1:])

### PART E ###
def toPigLatin(string):
	if not string:
		return ''
	elif vowelPosition(string) == -1 or vowelPosition(string) == 0:
		return string + "ay"
	else:
		return string[vowelPosition(string):] + string[:vowelPosition(string)] + 'ay'

### PART F ###
def listToPigList(oglist):
	if not oglist:
		return oglist
	else:
		return [toPigLatin(oglist[0])] + listToPigList(oglist[1:])

### MAIN ###
def main():
	print("This program will convert Snake Case to Pig Latin.\n")
	while True:
		incoming = input("Snake Case string: ")
		if validateString(incoming) != False:
			print("Pig Latin  string: " + str(listToPigList(stringToList(incoming))))
		else:
			print("This is not a valid Snake Case string.")
			
main()