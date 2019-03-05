//SOLUTIONS to TUTORIAL 2 by Alex Bisaillion

import java.util.Scanner;
import java.util.Random;

public class PartD {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("What should be the dimension of the grid (it'll be square)? ");
		int size = keyboard.nextInt();
		System.out.print("What should be the maximum random number? ");
		int max = keyboard.nextInt();

		//Initializing the array
		int[][] arr = new int[size][size];
		Random rand = new Random();

		//Building the array
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				arr[i][j]=rand.nextInt((max)+1);
			}
		}

		//Printing the array
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();

		//Greedy sum
		System.out.println("Beginning greedy sum...\n");
		//currI and currJ are initially set to 0 because the greedy sum starts here
		int currI = 0;
		int currJ = 0;
		//a count variable is initialized to keep track of the values that are walked to
		int count = arr[currI][currJ];

		//the first position is set to 0 because this is the first step in the walk
		arr[currI][currJ] = 0;
		do{
			//maxI and maxJ will hold the coordinates of the adjacent element with the largest value
			//by default, it is set to the current position
			int maxI = currI;
			int maxJ = currJ;
			int maxVal = 0;

			//Remember to check that the proposed movement does not fall outside of the array (i.e. curr+1>=0, etc.)
			//The logic here is that the element moving down is set to be the largest adjacent element by default
			//The element moving down is used as the default because the path should always allow for the walk to move downwards
			//This default value is only changed if a higher value is found after moving up/left/right

			//Checking element moving down
			if(currI+1>=0 && arr[currI+1][currJ]>=maxVal) {
				maxVal = arr[currI+1][currJ];
				maxI = currI+1;
				maxJ = currJ;
			}
			//Checking element moving up
			if(currI-1>=0 && arr[currI-1][currJ]>maxVal) {
				maxVal = arr[currI-1][currJ];
				maxI = currI-1;
				maxJ = currJ;
			}
			//Checking element moving right
			if(currJ+1>=0 && arr[currI][currJ+1]>maxVal) {
				maxVal = arr[currI][currJ+1];
				maxI = currI;
				maxJ = currJ+1;
			}
			//Checking element moving left
			if(currJ-1>=0 && arr[currI][currJ-1]>maxVal) {
				maxVal = arr[currI][currJ-1];
				maxI = currI;
				maxJ = currJ-1;
			}
			count+=maxVal;
			currI = maxI;
			currJ = maxJ;
			arr[currI][currJ] = 0;
		}while(currI<size-1 && currJ<size-1);

		//Printing the array after greedy sum
		System.out.println("Final path: ");
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("Greedy sum: " + count);
	}
}
