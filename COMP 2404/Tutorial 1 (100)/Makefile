OPT = -Wall

t01:	main.o Book.o
	g++ $(OPT) -o t01 main.o Book.o

main.o:	main.cc Book.h
	g++ $(OPT) -c main.cc

Book.o:	Book.cc Book.h
	g++ $(OPT) -c Book.cc

clean:
	rm -f *.o t01
