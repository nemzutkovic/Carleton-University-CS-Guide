def part_a(arg):
	if arg == "":
		return True
	elif arg[0] >= "A" and arg[0] <= "Z":
		return False
	else:
		return part_a(arg[1:])

def part_b(arg):
	if arg == "":
		return 0
	elif arg[0] == "_":
		return 0
	else:
		return 1 + part_b(arg[1:])
		
def part_c(arg):
	i = part_b(arg)
	if i == 0:
		return []
	else:
		return [arg[:i]] + part_c(arg[i+1:])
	
def part_d(arg):
	if arg == "":
		return 0
	elif arg[0] == "a" or arg[0] == "e" or arg[0] == "i" or arg[0] == "o" or arg[0] == "u":
		return 0
	else:
		return 1 + part_d(arg[1:])

def part_e(arg):
	i = part_d(arg)
	return arg[i:] + arg[:i] + "ay"

def part_f(arg):
	if arg == []:
		return []
	else:
		return [part_e(arg[0])] + part_f(arg[1:])

		
def main():
	snake_case = input("Enter a string in snake case, without uppercase letters: ")
	if not part_a(snake_case):
		print("Since the input contained uppercase letters, this program will now terminate.")
	else:
		igpay_atinlay = part_f(part_c(snake_case))
		print(snake_case, "becomes", igpay_atinlay)
	
main()
	
