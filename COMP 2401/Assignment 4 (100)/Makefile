OPTS= -std=c99
OBJS= main.o timmy.o harold.o

chatline: ${OBJS} a4-util.o
	gcc -o chatline ${OBJS} a4-util.o

main.o: main.c defs.h
	gcc ${OPTS} -c main.c

timmy.o: timmy.c defs.h
	gcc ${OPTS} -c timmy.c

harold.o: harold.c defs.h
	gcc ${OPTS} -c harold.c

clean:
	rm -f ${OBJS} chatline