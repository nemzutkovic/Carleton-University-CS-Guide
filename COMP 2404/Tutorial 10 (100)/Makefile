OPT = -Wall

OBJS = main.o Control.o View.o Book.o Library.o FictionBook.o NonFictionBook.o

t10:	$(OBJS) BookServer.o Array.o List.h
	g++ $(OPT) -o t10 $(OBJS) BookServer.o Array.o

main.o:	main.cc Book.h
	g++ $(OPT) -c main.cc

Control.o:	Control.cc Control.h
	g++ $(OPT) -c Control.cc

View.o:	View.cc View.h
	g++ $(OPT) -c View.cc

Library.o:	Library.cc Library.h
	g++ $(OPT) -c Library.cc

Book.o:	Book.cc Book.h
	g++ $(OPT) -c Book.cc

FictionBook.o:	FictionBook.cc FictionBook.h Book.h
	g++ $(OPT) -c FictionBook.cc

NonFictionBook.o:	NonFictionBook.cc NonFictionBook.h Book.h
	g++ $(OPT) -c NonFictionBook.cc

clean:
	rm -f $(OBJS) t10
