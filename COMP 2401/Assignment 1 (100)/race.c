#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define MAX_POS 70

int  randomInt(int);
void initArray(char*);
void moveTortoise(int, int*);
void moveHare(int, int*);
void printArray(char*, int*, int*);

int main(){
	char racetrack[MAX_POS];
	int tortoiseposition = 0;
	int hareposition = 0;

	initArray(racetrack);

	srand(time(NULL));
  
	printf("TIMMY THE TORTOISE VS HAROLD THE HARE!\n");
	printf("3...2...1...GO!\n");

	while(tortoiseposition < MAX_POS || hareposition < MAX_POS){
    	moveTortoise(randomInt(10), &tortoiseposition);
    	// The tortoise wins if the end of the array(index 69) or greater is reached.
    	if(tortoiseposition >= MAX_POS-1){
	    	printf("TIMMY IS THE WINNER!\n");
	    	break;
    	}
    	moveHare(randomInt(10), &hareposition);
    	// The hare wins if the end of the array(index 69) or greater is reached.
	    if(hareposition >= MAX_POS-1){
	    	printf("HAROLD IS THE WINNER!\n");
	    	break;
	    }
	    printArray(racetrack, &tortoiseposition, &hareposition);
	}
	return 0;
}

/*
  Function:  randomInt
  Purpose:   returns a random number in the range [0,max)
  Parameters:
    in:      maximum of range
    return:  randomly generated number
*/

int randomInt(int max){
	return(rand() % max);
}

/*
  Function:  initArray
  Purpose:   initializes an array with empty space characters
  Parameters:
    out:     racetrack array to be populated
    return:  none
*/

void initArray(char *racetrack){
    for (int i = 0; i < MAX_POS; ++i){
    	racetrack[i] = ' '; 
    }
}

/*
  Function:  moveTortoise
  Purpose:   updates the tortoise's position in the array (based one Table 1)
  Parameters:
    in:      random number from 0-9
    in-out:  tortoise's position
    return:  none
*/

void moveTortoise(int randommove, int *tortoiseposition){ 
    // Slip
    if(randommove == 0 || randommove == 1){
        if(*tortoiseposition > 5){
            *tortoiseposition -= 6;
        }
        else{
        	*tortoiseposition = 0;
        }
    }
    // Slow Plod
    else if(randommove == 2 || randommove == 3 || randommove == 4){
        *tortoiseposition += 1;
    }
    // Fast Plod
    else{
        *tortoiseposition += 3;
    }
}

/*
  Function:  moveHare
  Purpose:   updates the hare's position in the array (based one Table 1)
  Parameters:
    in:      random number from 0-9
    in-out:  hare's position
    return:  none
*/

void moveHare(int randommove, int *hareposition){ 
	// Big Slip
    if(randommove == 0){
        if(*hareposition > 11){
            *hareposition -= 12;
        }
        else{
        	*hareposition = 0;
        }
    }
    // Small Slip
    else if(randommove == 1 || randommove == 2){
        if(*hareposition > 1){
            *hareposition -= 2;
        }
        else{
        	*hareposition = 0;
        }
    }
    // Sleep
    else if(randommove == 3 || randommove == 4){
    }
    // Big Hop
    else if(randommove == 5 || randommove == 6){
    	*hareposition += 9;
    }
    // Small Hop
    else{
    	*hareposition += 1;
    }
}

/*
  Function:  printArray
  Purpose:   prints out the race track with each runner's updated position (T, H, or !)
  Parameters:
    out:     racetrack array to be updated
    in:      tortoise's position 
    in:      hare's position
    return:  none
*/

void printArray(char *racetrack, int *tortoiseposition, int *hareposition){
	if(*tortoiseposition == *hareposition){
		racetrack[*tortoiseposition] = '!';
	}
	else{
		racetrack[*tortoiseposition] = 'T';
		racetrack[*hareposition] = 'H';
	}
	printf("|");
	for(int i = 0; i < MAX_POS; ++i){
	    printf("%c", racetrack[i]);
	}
	printf("|\n");
	racetrack[*tortoiseposition] = ' ';
	racetrack[*hareposition] = ' ';
}