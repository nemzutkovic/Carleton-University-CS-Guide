//SOLUTIONS to TUTORIAL 2 by Alex Bisaillion

import java.util.Scanner; // for keyboard input

public class PartA {
    public static void main(String[] args) {
        //Generating this pattern:
		//QQQQQ
		//QQQQQ
		//QQQQQ
		//QQQQQ
		//QQQQQ
        Scanner keyboard = new Scanner(System.in);
		System.out.print("ENTER A DIGIT: ");
		int input = keyboard.nextInt();
		for(int i=0; i<input; i++) {
			for(int j=0; j<input; j++) {
				System.out.print("Q");
			}
			System.out.println();
		}
		System.out.println();
		
		//Generating this pattern:
		//Q
		//QQ
		//QQQ
		//QQQQ
		for(int i=0; i<input; i++) {
			for(int j=0; j<input; j++) {
				if (j<=i) {
					System.out.print("Q");
				}
			}
			System.out.println();
		}
		System.out.println();
		
		//Generating this pattern:
		//QQQQ
		//QQQ
		//QQ
		//Q
		for(int i=0; i<input; i++) {
			for(int j=0; j<input; j++) {
				if (j>=i) {
					System.out.print("Q");
				}
			}
			System.out.println();
		}
		System.out.println();
		
		//Generating this pattern:
		//QQQQ
		// QQQ
		//  QQ
		//   Q
		for(int i=0; i<input; i++) {
			for(int j=0; j<input; j++) {
				if (j>=i) {
					System.out.print("Q");
				}
				else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		System.out.println();
		
		//Generating this pattern:
		//   Q
		//  QQ
		// QQQ
		//QQQQ
		for(int i=0; i<input; i++) {
			for(int j=0; j<input; j++) {
				if(j<(input-1-i)) {
					System.out.print(" ");
				}
				else {
					System.out.print("Q");
				}
			}
			System.out.println();
		}
		System.out.println();
    }
}
