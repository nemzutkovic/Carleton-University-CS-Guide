import time
import random

def readwords(filename):
    f = open(filename, "r")
    words = []
    for line in f:
        line = line.strip()
        if len(line) > 0 and line[0] != ";":
            words.append(line)
    f.close()
    return words


def isinlist(word, searchlist):
    return word in searchlist

def isindict(word, searchdict):
    return word in searchdict.get(word[0])

def isindict2(word, searchdict):
    return word in searchdict.get(word[:2])

filename = "positivewords.txt"
wordlist = readwords(filename) #stores all the words from the file in a list
worddict = {} #dictionary to store all the words
worddict2 = {}

for word in wordlist:
    firstchar = word[0]
    if firstchar in worddict:
        worddict[firstchar].append(word)
    else:
        worddict[firstchar] = [word]

for word in wordlist:
    firstchar = word[:2]
    if firstchar in worddict2:
        worddict2[firstchar].append(word)
    else:
        worddict2[firstchar] = [word]

#Time to compare the performance of the two approaches!

#we will repeat 5000 times by default, you can change this number to look for more/less words
repeats = 5000


#LIST-BASED SEARCH
foundwrong = False
start = time.time() #this gets a representation of the current time and stores it in start variable
for i in range(repeats):
    word = random.choice(wordlist) #get a random word from the list
    if not isinlist(word,wordlist): #see if the word is in the list using the list-based search function
        foundwrong = True
end = time.time() #get an end representation of the current time

#since we recorded the time we started the search and the time we ended the search, we can calculate the amount of time that elapsed
print("List search time:", (end-start))


#DICT-BASED SEARCH
#repeat the process using the dictionary-based search.
foundwrong = False
start = time.time()
for i in range(repeats):
    word = random.choice(wordlist)
    if not isindict(word,worddict):
        foundwrong = True
end = time.time()

#this is included to ensure your dictionary-based search is returning the correct values
if foundwrong:
    print("Something is wrong with the dict search function.")
else:
    print("Dict search time:", (end-start))

#DICT-BASED SEARCH 2
#repeat the process using the dictionary-based search.
foundwrong = False
start = time.time()
for i in range(repeats):
    word = random.choice(wordlist)
    if not isindict2(word,worddict2):
        foundwrong = True
end = time.time()

#this is included to ensure your dictionary-based search is returning the correct values
if foundwrong:
    print("Something is wrong with the dict search function.")
else:
    print("Dict search time 2:", (end-start))

# Using the first 2 letters of a word as the key increases the search performance!