cache = {}
def cachedfactorial(x):
	if x in cache or x == 1:
		cache[x] = x
	else:
		cache[x] = x * cachedfactorial(x-1)
	return cache[x]