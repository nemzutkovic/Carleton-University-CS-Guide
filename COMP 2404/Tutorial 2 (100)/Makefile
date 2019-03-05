OPT = -Wall

t01:	main.o Book.o Library.o
	g++ $(OPT) -o t02 main.o Book.o Library.o

main.o:	main.cc Book.h
	g++ $(OPT) -c main.cc

Book.o:	Book.cc Book.h
	g++ $(OPT) -c Book.cc

Library.o: Library.cc Library.h
	g++ $(OPT) -c Library.cc

clean:
	rm -f *.o t02
