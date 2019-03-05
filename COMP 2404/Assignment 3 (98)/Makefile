OPT = -Wall

cal:	Main.o Control.o View.o Calendar.o List.o Event.o Date.o Time.o WorkEvent.o SchoolEvent.o
	g++ $(OPT) -o cal Main.o Control.o View.o Calendar.o List.o Event.o Date.o Time.o WorkEvent.o SchoolEvent.o

Main.o:	Main.cc
	g++ $(OPT) -c Main.cc

Control.o: Control.cc Control.h
	g++ $(OPT) -c Control.cc

View.o: View.cc View.h
	g++ $(OPT) -c View.cc

Calendar.o: Calendar.cc Calendar.h
	g++ $(OPT) -c Calendar.cc

List.o: List.cc List.h
	g++ $(OPT) -c List.cc

Event.o: Event.cc Event.h
	g++ $(OPT) -c Event.cc

WorkEvent.o:	WorkEvent.cc WorkEvent.h
	g++ $(OPT) -c WorkEvent.cc

SchoolEvent.o:	SchoolEvent.cc SchoolEvent.h
	g++ $(OPT) -c SchoolEvent.cc

Date.o:	Date.cc Date.h
	g++ $(OPT) -c Date.cc

Time.o:	Time.cc Time.h
	g++ $(OPT) -c Time.cc

clean:
	rm -f *.o cal