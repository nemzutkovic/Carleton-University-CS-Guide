import sentences


string = "The sky is clear; the stars are twinkling. I was very proud of my nickname throughout high school but today- I could not be any different to what my nickname was. We have never been to Asia, nor have we visited Africa. Check back tomorrow; I will see if the book has arrived. A song can make or ruin a persons day if they let it get to them. Mary plays the piano."
print("Input Text:", string)
print("Start words should be", ['I', 'We', 'Check', 'A', 'Mary'])
print("Your start words are:", sentences.startwords(string))
print("End words should be", ['twinkling', 'was', 'Africa', 'arrived', 'them', 'piano'])
print("Your end words are:", sentences.endwords(string))


string = "The river stole the gods. A glittering gem is not enough. Lets all be unique together until we realise we are all the same. The clock within this blog and the clock on my laptop are 1 hour different from each other. Sometimes, all you need to do is completely make a fool of yourself and laugh it off to realise that life isnt so bad after all. Hurry! Where do random thoughts come from? She advised him to come back at once. The sky is clear; the stars are twinkling. My Mum tries to be cool by saying that she likes all the same things that I do."
print("Input Text:", string)
print("Start words should be", ['A', 'Lets', 'The', 'Sometimes,', 'Hurry!', 'My'])
print("Your start words are:", sentences.startwords(string))
print("End words should be", ['gods', 'enough', 'same', 'other', 'all', 'once', 'twinkling', 'do'])
print("Your end words are:", sentences.endwords(string))

string = "The sky is clear; the stars are twinkling. The river stole the gods. A glittering gem is not enough. A song can make or ruin a persons day if they let it get to them. My Mum tries to be cool by saying that she likes all the same things that I do. I am happy to take your donation; any amount will be greatly appreciated. I could not be any different to what my nickname was. The book is in front of the table."
print("Input Text:", string)
print("Start words should be", ['The', 'A', 'My', 'I'])
print("Your start words are:", sentences.startwords(string))
print("End words should be", ['twinkling', 'gods', 'enough', 'them', 'do', 'appreciated', 'was', 'table'])
print("Your end words are:", sentences.endwords(string))

string = "We should wait for everyone to get here. One of them is not here. Writing a list of random sentences is harder than I initially thought it would be. She did her best to help him. A glittering gem is not enough. He did not want to go to the dentist, yet he went anyway. Anyone but him. We should finish this assignment."
print("Input Text:", string)
print("Start words should be", ['One', 'Writing', 'She', 'A', 'He', 'Anyone', 'We'])
print("Your start words are:", sentences.startwords(string))
print("End words should be", ['here', 'be', 'him', 'enough', 'anyway', 'assignment'])
print("Your end words are:", sentences.endwords(string))

string = "It was getting dark, and we were not there yet. It was getting dark, and we were not there yet. It was getting dark, and we were not there yet. It was getting dark, and we were not there yet. It was getting dark, and we were not there yet. Saying the same sentence five times in a row is strange."
print("Input Text:", string)
print("Start words should be", ['It', 'Saying'])
print("Your start words are:", sentences.startwords(string))
print("End words should be", ['yet', 'strange'])
print("Your end words are:", sentences.endwords(string))
