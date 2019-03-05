#include <stdio.h>
#include <stdlib.h>

double factorial(int);
double permutation(int, int);
double combination(double, double);
void drawPascalTriangle(int);

int main() {
	int l;
	printf("Enter the number of rows:\n");
	scanf("%d", &l);
	drawPascalTriangle(l);
	return 0;
}

double factorial(int n) {
	if (n <= 0) { 
		return 1;
	} else {
		return factorial(n-1) * n;
	}
}

double permutation(int k, int n) {
	return factorial(n)/factorial(n-k);
}

double combination(double k, double n) {
	return permutation(k,n)/factorial(k);
}

void printBlankSpace(int l) {
	int i;
	for(i = 0; i < l; i++) {
		printf("   ");
	}
}

void drawPascalTriangle(int n) {
	double l;
	double c;
	for (l = 0; l <= n; l++) {
		printBlankSpace(2*n-l);
		for (c = 0; c <= l; c++) {
			printf("  %f   ", combination(c, l));
		}
		printf("\n\n");
	}
}
