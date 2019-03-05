import time
import random
import sys # Needed to import sys to change recursion limit
sys.setrecursionlimit(5000) # Needed change due to stack overflow limit of 997

def factorial(number):
    result = number
    for i in range(number-1, 0, -1):
            result *= i
    return result

def cachedfactorial(number, dictionary):
    if number == 0:
        dictionary[number] = 1
        return 1
    elif number in dictionary:
        return dictionary[number]
    else:
        dictionary[number] = number * cachedfactorial(number - 1, dictionary)
        return dictionary[number]

print("Calculating Factorial From 0-4999...")
cache = {}
start = time.time()
for i in range(0,5000):
    factorial(i)
end = time.time()
print("Non-cached time: ", end-start) # 17.750048398971558

start = time.time()
for i in range(0,5000):
    cachedfactorial(i, cache)
end = time.time()
print("Cached time: ", end-start) # 0.019469261169433594
print()

print("Calculating Factorial From 4999-0...")
cache = {}
start = time.time()
for i in range(5000,0,-1):
    factorial(i)
end = time.time()
print("Non-cached time: ", end-start) # 17.606708765029907

start = time.time()
for i in range(5000,0,-1):
    cachedfactorial(i, cache)
end = time.time()
print("Cached time: ", end-start) # 0.021976709365844727

print("Calculating Factorial For Random Integer From 1-5000...")
cache = {}
start = time.time()
for i in range(5000):
    factorial(random.randint(1,5000))
end = time.time()
print("Non-cached time: ", end-start) # 17.569278955459595

start = time.time()
for i in range(5000):
    cachedfactorial(random.randint(1,5000), cache)
end = time.time()
print("Cached time: ", end-start) # 0.028454303741455078

# MORAL OF THE STORY: CACHED FACTORIAL ALWAYS WINS