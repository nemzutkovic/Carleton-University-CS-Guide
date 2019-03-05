#include <stdio.h>
#include <stdlib.h>

#define DIGIT_NUM 4

void convertToHex(int,char*);
void initDigits(char*);

int main() {
	int n;
	printf("Enter an integer number:");
	scanf("%d", &n);
	printf("Decimal = %d\n", n);
	char digits[DIGIT_NUM];
	initDigits(digits);
	convertToHex(n, digits);
	int i;
	printf("Hex = 0x");
	for (i = DIGIT_NUM; i > 0; i--) {
		printf("%c", digits[i-1]);
	}
	printf("\n");
	return 0;
}

void convertToHex(int n, char* digits) {
	int c = 0;
	while (n > 0) {
		digits[c] = n % 16;
		if (digits[c] > 9) {
			digits[c] += ('A' - 10); 
		} else {
			digits[c] += '0';
		}
		n = n / 16;
		c++;
	}
}

void initDigits(char* digits) {
	int i;
	for (i = 0; i < DIGIT_NUM; i++) {
		digits[i] = 0;
	}
}
