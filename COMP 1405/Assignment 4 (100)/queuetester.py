import queue

print("Printing the initial queue, should be: []")
print("Your queue is:", queue.getlist())
print("Adding 10 values to queue")
for i in range(10):
    queue.enqueue(i)

print("The queue should now be: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]")
print("Your queue is:", queue.getlist())

print("Trying to add 'cow' to queue")
result = queue.enqueue('cow')
print("The return value of that call to enqueue should be False")
print("The returned value from your queue was", result)
print("Printing the queue, should be: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]")
print("Your queue is:", queue.getlist())

print("Removing and printing 4 values from queue")
print("Should be 0:", queue.dequeue())
print("Should be 1:", queue.dequeue())
print("Should be 2:", queue.dequeue())
print("Should be 3:", queue.dequeue())

print("Looking at the first value, but not removing")
print("Should be 4:", queue.peek())

print("Trying to add 'cow' to queue")
queue.enqueue('cow')
print("The queue should now be: [4, 5, 6, 7, 8, 9, 'cow']")
print("Your queue is:", queue.getlist())

print("Trying to enqueue the list ['a', 'b', 'c', 'd', 'e']")
added = queue.multienqueue(['a', 'b', 'c', 'd', 'e'])
print("The number added should be 3.")
print("The number added was", added)

print("The queue should now be: [4, 5, 6, 7, 8, 9, 'cow', 'a', 'b', 'c']")
print("Your queue is:", queue.getlist())

print("Trying to dequeue a list of 5 items")
removed = queue.multidequeue(5)
print("The dequeued list should be: [4, 5, 6, 7, 8]")
print("The dequeued list was: ", removed)

print("The queue should now be: [9, 'cow', 'a', 'b', 'c']")
print("Your queue is:", queue.getlist())

print("Trying to dequeue a list of 7 items")
removed = queue.multidequeue(7)
print("The dequeued list should be: [9, 'cow', 'a', 'b', 'c']")
print("The dequeued list was: ", removed)

print("The queue should now be: []")
print("Your queue is:", queue.getlist())

print("Dequeing from empty queue, should be None")
print("Your queue produced: ", queue.dequeue())

print("Peeking at empty queue, should be None")
print("Your queue produced: ", queue.peek())
