OPT = -Wall

cal:	main.o Calendar.o Event.o Date.o Time.o
	g++ $(OPT) -o cal main.o Calendar.o Event.o Date.o Time.o

main.o:	main.cc Date.h
	g++ $(OPT) -c main.cc

Calendar.o: Calendar.cc Calendar.h
	g++ $(OPT) -c Calendar.cc

Event.o: Event.cc Event.h
	g++ $(OPT) -c Event.cc

Date.o:	Date.cc Date.h
	g++ $(OPT) -c Date.cc

Time.o:	Time.cc Time.h
	g++ $(OPT) -c Time.cc

clean:
	rm -f *.o cal