#include <stdio.h>
#include <stdlib.h>

int gcd(int,int);

int main() {
	int a,b;
	printf("Enter two integer numbers:\n");
	scanf("%d", &a);
	scanf("%d", &b);
	int i = gcd(a,b);
	printf("gcd(%d,%d):  %d\n", a, b, i);
	return 0;
}

int gcd(int a, int b) {
	if (a > b) {
		return gcd(b,a);
	} else if (a == 0) {
		return b;
	} else {
		return gcd(a, (b % a));
	}
	
}
