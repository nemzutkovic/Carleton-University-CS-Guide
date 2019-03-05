#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define MAX_ARR_SIZE	10
#define MAX_STR  			16

typedef struct {
  char 		name[MAX_STR];
  int 		strength;
  int 		armour;
  int  		health;
} HeroType;

typedef struct {
  int 		piratestrength;
  int  		piratehealth;
} PirateType;

typedef struct {
  PirateType  *arr[MAX_ARR_SIZE];
  int  				numpirates;
} ArrayType;

int  randomInt(int);
void initHero(char*, int, int, int, HeroType*);
void initPirates(ArrayType*);
void resetHealth(HeroType*, HeroType*);
void fight(HeroType*, ArrayType*);
void updateCounters(HeroType*, HeroType*, int*, int*, int*, int*);
void freePirates(ArrayType*);

int main(){
	srand(time(NULL));

	HeroType timmy, harold;
	initHero("Timmy",  5, 5, 30, &timmy);
  initHero("Harold", 7, 3, 30, &harold);

	ArrayType piratecrew;

  int timmywins = 0, haroldwins = 0, bothwin = 0, pirateswin = 0;

  for(int i = 0; i < 100; ++i){
	  initPirates(&piratecrew);
	  resetHealth(&timmy, &harold);
		while(piratecrew.numpirates != 0 && (timmy.health > 0 || harold.health > 0)){
  		fight(&timmy, &piratecrew);
  		fight(&harold, &piratecrew);	
		}
		updateCounters(&timmy, &harold, &timmywins, &haroldwins, &bothwin, &pirateswin);
		freePirates(&piratecrew);
  }

  printf("-------------------\nTimmy Wins: %7d\nHarold Wins: %6d\nBoth Win: %9d\nPirates Win: %6d\n-------------------\n", timmywins, haroldwins, bothwin, pirateswin);
	
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
  Function:  initHero
  Purpose:   initializes a HeroType structure
  Parameters:
  	in:			 name of the hero
  	in:			 strength of the hero
  	in:			 armour of the hero
  	in:			 health of the hero
  	out:	   hero
    return:  none
*/

void initHero(char *newname, int newstrength, int newarmour, int newhealth, HeroType *hero){
	strcpy(hero->name, newname);
	hero->strength =  newstrength;
	hero->armour = newarmour;
	hero->health = newhealth;
}

/*
  Function:  initPirates
  Purpose:   initializes an ArrayType structure by dynamically allocating PirateType pointers
  Parameters:
  	out:	   arraytype structure to be initialized and populated
    return:  none
*/

void initPirates(ArrayType *piratecrew){
	piratecrew->numpirates = 0;
	for(int i = 0; i < MAX_ARR_SIZE; ++i){
		PirateType *pirate = malloc(sizeof(PirateType));
		pirate->piratestrength = (randomInt(4)+3);
		pirate->piratehealth = 20;
		piratecrew->arr[i] = pirate;
		piratecrew->numpirates++;
	}
}

/*
  Function:  resetHealth
  Purpose:   resets the health of two HeroType structures
  Parameters:  
    out:     hero
    out:     hero
    return:  none
*/

void resetHealth(HeroType *hero1, HeroType *hero2){
	hero1->health = 30;
	hero2->health = 30;
}

/*
  Function:  fight
  Purpose:   carries out the fight sequence between a HeroType structuresand PirateType structures
  Parameters:
    in-out:  hero 
    in-out:  arraytype structure to be updated
    return:  none
*/

void fight(HeroType *hero, ArrayType *piratecrew){
	int piratedamage;
	while(hero->health > 0){
		piratecrew->arr[piratecrew->numpirates-1]->piratehealth -= hero->strength;
		if(piratecrew->arr[piratecrew->numpirates-1]->piratehealth <= 0){
			hero->health += 3;
			piratecrew->numpirates--;
			break;
		}
		piratedamage = (randomInt((piratecrew->arr[piratecrew->numpirates-1]->piratestrength)+1) + piratecrew->arr[piratecrew->numpirates-1]->piratestrength) - hero->armour;
		if(piratedamage > 0){
			hero->health -= piratedamage;
		}
	}
}

/*
  Function:  updateCounters
  Purpose:   checks the health of both HeroType Structures (Harold/Timmy) and updates the counters accordingly
  Parameters:
    in:			 hero
    in:      hero
    out:  	 updates win counter for timmmy
    out:  	 updates win counter for harold
    out:  	 updates win counter for both
    out:  	 updates win counter for pirates
    return:  none
*/

void updateCounters(HeroType *timmy, HeroType *harold, int *timmywins, int *haroldwins, int *bothwin, int *pirateswin){
		if(timmy->health > 0 && harold->health > 0){
			*bothwin += 1;
		}
		else if(timmy->health > 0 && harold->health <= 0){
			*timmywins += 1;
		}
		else if(timmy->health <= 0 && harold->health > 0){
			*haroldwins += 1;
		}
		else{
			*pirateswin += 1;
		}
}

/*
  Function:  freePirates
  Purpose:   deallocates the dynamically allocated memory in the ArrayType structure
  Parameters:
    out:     deallocates dynamically allocated pirates in arraytype structure   
    return:  none
*/

void freePirates(ArrayType *piratecrew){
	 	for(int k = 0; k < MAX_ARR_SIZE; ++k){
 			free(piratecrew->arr[k]);
 		}
}