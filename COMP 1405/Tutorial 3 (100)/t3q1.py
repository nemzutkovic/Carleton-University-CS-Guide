print("Please answer the following questions with a Yes or No:")
wet = input("Are you wet? ")
cold = input("Are you cold? ")
poor = input("Are you poor? ")
hungry = input("Are you hungry? ")

# NESTED IF/ELSE STATEMENTS WITHOUT LOGICAL OPERATORS
if wet == "Yes":
	if cold == "Yes":
		print("You are miserable.")
	else:
		print("You are happy.")
else:
	if poor == "Yes":
		if hungry == "Yes":
			print("You are miserable.")
		else:
			print("You are happy.")
	else:
		print("You are happy.")

print()

# SINGLE IF/ELSE STATEMENT
if (wet == "Yes" and cold == "Yes") or (poor == "Yes" and hungry == "Yes"):
	print("You are miserable.")
else:
	print("You are happy.")