//SOLUTIONS to TUTORIAL 2 by Alex Bisaillion

import java.util.Scanner;

public class PartC {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);

		//User inputs
		System.out.print("Enter an integer: ");
		String intInput = keyboard.next();
		System.out.print("Enter a decimal: ");
		String decInput = keyboard.next();

		//Converting to integer
		int intConvert = Integer.parseInt(intInput);
		System.out.println("String converted to integer: " + Integer.parseInt(intInput));

		//Converting to double
		double decConvert = Double.parseDouble(decInput);
		System.out.println("String converted to double: " + Double.parseDouble(decInput));

		//Adding
		System.out.println("Sum converted to string: " + String.valueOf(intConvert+decConvert));
	}
}
