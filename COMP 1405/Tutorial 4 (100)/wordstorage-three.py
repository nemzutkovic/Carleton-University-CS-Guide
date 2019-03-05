import time
import random

#this is how the addword function would work if only words of length two were allowed
#you must implement a function that can take words of any length
#this will require you to use a loop, but the same strategy should still apply
def addword(word):
    if len(word) != 2:
        return

    #we start at the first "level" of dictionary - the main dictionary
    current_dict = words

    #get the first character of the word
    char = word[0]
    #if the first character is not in the current dictionary (the top-level dictionary), add it as a key with an empty dictionary value
    if char not in current_dict:
        current_dict[char] = {}

    #we move one level forward/in (to a dictionary inside a dictionary)
    #current_dict will now be the dictionary associated with the first character of the word
    current_dict = current_dict[char]

    #get the next character of the word and do the same as above
    char = word[1]
    if char not in current_dict:
        current_dict[char] = {}

    #move one more level forward/in
    #current_dict is now the dictionary associated with the second character of the word, which is inside of the dictionary associated with the first character of the word, which is inside the main dictionary
    current_dict = current_dict[char]

    #at the end of each word, we want to add a key/value of '.'/1 to represent a word ended here
    current_dict['.'] = 1

def addwordlist(string):
    wordslist.append(string)


def checkword(string):
    dict = words
    for char in string:
        if char not in dict:
            return False
        dict = dict[char]
    return '.' in dict

def initialize(filename):
    f = open(filename, "r")
    for line in f:
        line = line.strip()
        if len(line) > 0 and line[0] != ";":
            addword(line)
            addwordlist(line)
    f.close()

words = {}
wordslist = []


initialize("positivewords.txt")

checklist = []
checknum = 10000
for i in range(checknum):
    checklist.append(random.choice(wordslist))

start = time.time()
for word in checklist:
    word in wordslist
end = time.time()
print("listtime:", end-start)
start = time.time()
for word in checklist:
    checkword(word)
end = time.time()
print("dicttime:", end-start)
