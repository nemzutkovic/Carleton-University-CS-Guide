#include <stdio.h>

int gcd(int, int);

int main()
{  
    gcd(18,6);

    return 0;
}

int gcd(int numberone, int numbertwo){
    
    int commondivisor;

    for(int i = 1; i <= numberone && i <= numbertwo; ++i){     
        if(numberone % i == 0 && numbertwo % i == 0){    
            commondivisor = i;   
        }    
    }

    printf("The greatest common denominator is %d\n", commondivisor);

    return 0;
}
