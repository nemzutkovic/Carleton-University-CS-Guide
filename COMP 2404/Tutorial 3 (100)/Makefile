OPT = -Wall

t03:	main.o Book.o Library.o Array.o
	g++ $(OPT) -o t03 main.o Book.o Library.o Array.o

main.o:	main.cc Book.h
	g++ $(OPT) -c main.cc

Book.o:	Book.cc Book.h
	g++ $(OPT) -c Book.cc

Library.o: Library.cc Library.h
	g++ $(OPT) -c Library.cc

Array.o: Array.cc Array.h
	g++ $(OPT) -c Array.cc

clean:
	rm -f *.o t03
