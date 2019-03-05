all:	t10-fork t10-threads
	make t10-fork t10-threads

t10-fork:	t10-fork.c
	gcc -o t10-fork t10-fork.c

t10-threads:	t10-threads.c
	gcc -pthread -o t10-threads t10-threads.c -lm

clean:
	rm t10-threads t10-fork
