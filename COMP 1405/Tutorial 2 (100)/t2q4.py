movie = input("movie title : ")
rating = input("rating: ")
if len(rating) == 0 and rating == "":
	print("You have not entered in any stars.")
elif len(rating) == 1 and rating == "*":
	print(movie + " sucked." + " You only gave it " + str(len(rating)) + " stars.")
elif len(rating) == 2 and rating == "**":
	print(movie + " was okay." + " You only gave it " + str(len(rating)) + " stars.")
elif len(rating) == 3 and rating == "***":
	print(movie + " was pretty good." + " You gave it " + str(len(rating)) + " stars.")
elif len(rating) == 4 and rating == "****":
	print(movie + " was great!" + " You gave it " + str(len(rating)) + " stars!")
elif len(rating) == 5 and rating == "*****":
	print(movie + " was AWESOME!" + " You gave it " + str(len(rating)) + " stars!")
else:
	print("You have entered in too many stars or have entered in other characters.")