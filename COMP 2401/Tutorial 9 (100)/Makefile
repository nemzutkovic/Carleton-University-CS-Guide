OPT = -Wall
EXECUTABLES = send handle

all: $(EXECUTABLES)	

send: send.c
	gcc $(OPT) -o send send.c

handle: handle.c
	gcc $(OPT) -o handle handle.c

clean:
	rm -f $(EXECUTABLES)
