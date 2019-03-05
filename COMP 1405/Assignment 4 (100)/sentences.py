def startwords(string):
	keylist = []
	updatedkeylist = []
	finallist = []
	brokenstring = string.split(". ")
	for i in brokenstring:
		keylist.append(i.split(" "))
	for i in keylist:
		updatedkeylist.append(i[0])
	del updatedkeylist[0]
	for i in updatedkeylist:
		if i not in finallist:
			finallist.append(i)
	return finallist

def endwords(string):
	keylist = []
	finalkeylist = []
	brokenstring = string.split(" ")
	for i in brokenstring:
		if "." in i:
			keylist.append(i.strip("."))
	for i in keylist:
		if i not in finalkeylist:
			finalkeylist.append(i)
	return finalkeylist