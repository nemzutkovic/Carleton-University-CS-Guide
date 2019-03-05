def load(string):
	file = open(string, "r")
	global filealpha
	filealpha = file.read()
	global fileomega
	fileomega = filealpha.split(" ")
	
def commonword(list):
	freqword = []
	common = {}
	for i in fileomega:
		if i in list:
			freqword.append(i)
	for i in freqword:
		count = common.get(i,0)
		common[i] = count + 1
	if common == {}:
		return None
	else:
		return(max(common, key=common.get))	
		
def commonletter(list):
	freqletter = []
	common = {}
	for i in filealpha:
		if i in list:
			freqletter.append(i)
	for i in freqletter:
		count = common.get(i,0)
		common[i] = count + 1
	if common == {}:
		return None
	else:
		return(max(common, key=common.get))

def commonpair(string):
	list = []
	pairlist = []
	common = {}
	filebeta = filealpha.split(" " + string + " ")
	for i in filebeta:
		list.append(i.split(" "))
	for i in list:
		for word in i:
			if word == string:
				i.remove(word)
	for i in list[1:]:
		if i == []:
			continue
		pairlist.append(i[0])
	for i in pairlist:
		count = common.get(i,0)
		common[i] = count + 1
	if common == {}:
		return None
	else:
		return(max(common, key=common.get))	

def countall():
	return len(fileomega)
	
def countunique():
	countlist = []
	for i in fileomega:
		if i not in countlist:
			countlist.append(i)
	return(len(countlist))