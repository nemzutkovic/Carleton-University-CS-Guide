import time
import random
def factorial(number):
    result = number
    for i in range(number-1, 0, -1):
            result *= i
    return result

'''
cache = {}
repeats = 5000
start = time.time()
for i in range(repeats):
    factorial(i)
end = time.time()
print("Non-cached time: ", end-start)

start = time.time()
for i in range(repeats):
    cachedfactorial(i, cache)
end = time.time()
print("Cached time: ", end-start)
'''