print("The actor being considered is Christian Bale.")
print("The possible movies are: Batman Begins, American Psycho, The Dark Knight, Terminator Salvation")
choice = input("Does his character play as a superhero? ")
if choice == "Yes":
	firstbranch = input("Does his character face the Joker? ")
	if firstbranch == "No":
		print("The movie you are thinking about is Batman Begins.")
	elif firstbranch == "Yes":
		print("The movie you are thinking about is The Dark Knight.")
	else:
		print("Please answer with a Yes or No.")
elif choice == "No":
	secondbranch = input("Does his character battle against machines? ")
	if secondbranch == "Yes":
		print("The movie you are thinking about is Terminator Salvation.")
	elif secondbranch == "No":
		print("The movie you are thinking about is American Psycho.")
	else:
		print("Please answer with a Yes or No.")
else:
	print("Please answer with a Yes or No.")