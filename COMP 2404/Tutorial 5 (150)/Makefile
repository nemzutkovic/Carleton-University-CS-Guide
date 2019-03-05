OPT = -Wall

t05:	main.o Book.o Library.o Array.o View.o Control.o
	g++ $(OPT) -o t05 main.o Book.o Library.o Array.o View.o Control.o

main.o:	main.cc Book.h
	g++ $(OPT) -c main.cc

Book.o:	Book.cc Book.h
	g++ $(OPT) -c Book.cc

Library.o: Library.cc Library.h
	g++ $(OPT) -c Library.cc

Array.o: Array.cc Array.h
	g++ $(OPT) -c Array.cc

View.o: View.cc View.h
	g++ $(OPT) -c View.cc

Control.o: Control.cc Control.h
	g++ $(OPT) -c Control.cc

clean:
	rm -f *.o t05
