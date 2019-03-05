OPT = -Wall

t08:	main.o Control.o View.o Library.o List.o Book.o FictionBook.o NonFictionBook.o
	g++ $(OPT) -o t08 main.o Control.o View.o Library.o List.o Book.o FictionBook.o NonFictionBook.o

main.o:	main.cc Book.h
	g++ $(OPT) -c main.cc

Control.o: Control.cc Control.h
	g++ $(OPT) -c Control.cc

View.o: View.cc View.h
	g++ $(OPT) -c View.cc

Library.o: Library.cc Library.h
	g++ $(OPT) -c Library.cc

List.o: List.cc List.h
	g++ $(OPT) -c List.cc

Book.o:	Book.cc Book.h
	g++ $(OPT) -c Book.cc

FictionBook.o:	FictionBook.cc Book.h
	g++ $(OPT) -c FictionBook.cc

NonFictionBook.o:	NonFictionBook.cc Book.h
	g++ $(OPT) -c NonFictionBook.cc

clean:
	rm -f *.o t08