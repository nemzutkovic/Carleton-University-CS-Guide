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
    #enter code here, you can remove pass keyword once you have added code
    pass



filename = "positivewords.txt"
wordlist = readwords(filename) #stores all the words from the file in a list
worddict = {} #dictionary to store all the words


for word in wordlist:
    #enter code here to add each word from the list to the proper spot in words dictionary
    #you can remove pass once you have added code
    pass


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
