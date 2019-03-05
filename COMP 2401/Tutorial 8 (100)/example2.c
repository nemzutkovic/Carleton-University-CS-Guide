#include <stdio.h>
#include <stdlib.h>

char* convertToOct(int, char*);

int main() {
	int n;
	printf("Enter an integer number:");
	scanf("%d", &n);
	printf("Decimal = %d\n", n);
	char* digits = malloc(sizeof(char)*6);
	digits = convertToOct(n, digits);
	int i;
	printf("Octal = 0");
	for (i = sizeof(digits); i > 0; i--) {
		printf("%d", digits[i-1]);
	}
	printf("\n");
	return 0;
}

char* convertToOct(int n, char* digits) {
	int c = 0;
	while (n > 0) {
		digits[c] = '0' + n % 8;
		n = n / 8;
		c++;
	}
	return digits;
}
