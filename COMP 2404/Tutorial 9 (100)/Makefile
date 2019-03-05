OPT = -Wall

OBJS = main.o Control.o View.o Book.o Library.o List.o FictionBook.o NonFictionBook.o

t09:	$(OBJS) BookServer.o Array.o
	g++ $(OPT) -o t09 $(OBJS) BookServer.o Array.o

main.o:	main.cc Book.h
	g++ $(OPT) -c main.cc

Control.o:	Control.cc Control.h
	g++ $(OPT) -c Control.cc

View.o:	View.cc View.h
	g++ $(OPT) -c View.cc

Library.o:	Library.cc Library.h
	g++ $(OPT) -c Library.cc

List.o:	List.cc List.h
	g++ $(OPT) -c List.cc

Book.o:	Book.cc Book.h
	g++ $(OPT) -c Book.cc

FictionBook.o:	FictionBook.cc FictionBook.h Book.h
	g++ $(OPT) -c FictionBook.cc

NonFictionBook.o:	NonFictionBook.cc NonFictionBook.h Book.h
	g++ $(OPT) -c NonFictionBook.cc

clean:
	rm -f $(OBJS) t09
