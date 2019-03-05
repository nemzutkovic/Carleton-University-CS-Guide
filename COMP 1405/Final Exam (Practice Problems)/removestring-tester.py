import removestring

information = [
['jello hello dogma dogma jellybean bell hello jello hello dog', 'ello', 'j h dogma dogma jellybean bell h j h dog'],
['dogma dogma hello', 'og', 'dma dma hello'],
['hotdog dog dogma jello jellybean hotdog hello', 'el', 'hotdog dog dogma jlo jlybean hotdog hlo'],
['jello dogma bell jellybean hotdog', 'g', 'jello doma bell jellybean hotdo'],
['bell jello dogma dog hello', 'g', 'bell jello doma do hello'],
['jellybean jello jello hello bell hello jello dogma hello dogma', 'ell', 'jybean jo jo ho b ho jo dogma ho dogma'],
['bell hotdog hello hello hello jellybean hotdog', 'og', 'bell hotd hello hello hello jellybean hotd'],
['jellybean hotdog jello jello jello hello jellybean dogma', 'ell', 'jybean hotdog jo jo jo ho jybean dogma'],
['dogma jello bell hello hello dog hotdog', 'el', 'dogma jlo bl hlo hlo dog hotdog'],
['hotdog dog dog bell bell hotdog', 'ello', 'hotdog dog dog bell bell hotdog'],
['hello hotdog hello hello jellybean bell jellybean dogma hello jello', 'el', 'hlo hotdog hlo hlo jlybean bl jlybean dogma hlo jlo'],
['jellybean hello jellybean dogma bell jello dogma dog', 'el', 'jlybean hlo jlybean dogma bl jlo dogma dog'],
['jellybean hotdog dogma', 'ell', 'jybean hotdog dogma'],
['jellybean jello jello dogma hotdog dog dogma dogma dog jello', 'g', 'jellybean jello jello doma hotdo do doma doma do jello'],
['dogma hotdog jellybean bell dog hotdog bell jellybean jellybean dog', 'g', 'doma hotdo jellybean bell do hotdo bell jellybean jellybean do'],
['jellybean dog bell bell jello jellybean dog dogma jellybean', 'ell', 'jybean dog b b jo jybean dog dogma jybean'],
['bell hello jello', 'og', 'bell hello jello'],
['jello jello dogma bell dog', 'og', 'jello jello dma bell d'],
['bell dog dogma jellybean jellybean jello hotdog jello', 'g', 'bell do doma jellybean jellybean jello hotdo jello'],
['dog hotdog dog dog hello dogma', 'og', 'd hotd d d hello dma'],
['dog hotdog dog dog hello dogma', 'peanuts', 'dog hotdog dog dog hello dogma']
]

correct = 0
incorrect = []
print("Checking removestring function with test lists...")
print()
for info in information:
	l1 = info[0]
	remove = info[1]
	goal = info[2]
	result = removestring.removestring(l1, remove)
	print("Text:", l1)
	print("Remove:", remove)
	print("Goal:", goal)
	print("Your result:", result)
	if result == goal:
		correct += 1
		print("Good!")
	else:
		incorrect.append([l1, remove, goal, result])
		print("Incorrect!")
	print()
		
print()
print("Your code produced",correct,"out of", len(information),"correct results.")
print()
if len(incorrect) > 0:
	print("The cases for which your program failed were:")
	print()
	for info in incorrect:
		print("Text:", info[0])
		print("Remove:", info[1])
		print("Goal:", info[2])
		print("Your Result:", info[3])
		print()
	