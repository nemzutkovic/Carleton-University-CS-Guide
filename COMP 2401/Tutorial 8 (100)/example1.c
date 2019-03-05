#include <stdio.h>
#include <stdlib.h>

void printBinary(int);
int power(int, int);

int main() {
	int n;
	printf("Enter an integer number:");
	scanf("%d", &n);
	printf("Decimal = %d\n", n);
	printBinary(n);
    printf("Binary = %d\n", n);
	printf("\n");
	return 0;
}

int power(int a, int b) {
	int pow = 1;
	int i;
	for (i=0; i < b; i++) {
		pow *= a;
	}
	return pow;
}

void printBinary(int n) {
	int i,pow;
	for (i=15; i >= 0; i--) {
		pow = power(2,i);
		printf("%d", n % pow);
		n = n / pow;
	}
}
