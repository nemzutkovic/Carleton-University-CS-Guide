assignments = float(input("Assignments="))
midterm = float(input("Midterms="))
tutorials = float(input("Tutorials="))
finalexam = float(input("Exam="))
finalmark = float(((assignments * 50) + (midterm * 15) + (tutorials * 5) + (finalexam * 30))/100)
print("The final grade is",finalmark)
if finalmark >= 60 and finalexam >= 50:
	print("The student passes.")
else:
	print("The student does not pass.")