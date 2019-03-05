#include <stdio.h>
#include <stdlib.h>

#define DIGIT_NUM 4

void convertTo(int,char*,int);
void printDigits(char*);
void initDigits(char*);

int main() {
	int n;
	printf("Enter an integer number:");
	scanf("%d", &n);
	char digits[DIGIT_NUM];
	initDigits(digits);
	convertTo(n, digits, 16);
	printf("Decimal = %d\n", n);
	printf("Hex = 0x");
	printDigits(digits);
	printf("\n");
	return 0;
}

void convertTo(int n, char* digits, int base) {
	int c = 0;
	while (n > 0) {
		digits[c] = n % base;
		if (digits[c] < 10) {
			digits[c] += '0';
		} else {
			digits[c] += 'A' - 10;
		}
		n = n / base;
		c++;
	}
}

void printDigits(char* digits) {
	int i;
	for (i = DIGIT_NUM; i > 0; i--) {
		printf("%c",digits[i-1]);
	}
}

void initDigits(char* digits) {
	int i;
	for (i = 0; i < DIGIT_NUM; i++) {
		digits[i] = '0';
	}
}
