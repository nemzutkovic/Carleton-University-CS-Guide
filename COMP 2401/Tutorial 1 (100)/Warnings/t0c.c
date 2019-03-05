#include<stdio.h>

int foo(int x){
  if( x < 3) {
     return 3;
  }
}

int main(int argc, char* argv[]){
 int y = foo(2);
 printf("y is %d \n", y);
 return 0;
}
