import java.util.Arrays;  // helper functions for arrays

public class Tutorial01{

 
  // create and return a new array
	// the new array contains all the even integers
	// found in the input numbers
	public static int[] evens(int[] numbers){

	int n = 0;
	for(int integer=0; integer<numbers.length; integer+=1){
		if(numbers[integer] % 2 == 0){
			n+=1;
		}
	}
	
	int[] evenlist = new int[n];
	int counter = 0;
	for(int i =0; i<numbers.length; i+=1){
		if(numbers[i] % 2 == 0){
			evenlist[counter] = numbers[i];
			counter+=1;
		}
	}
	
	return evenlist;
	}

	/* the main method is the "program"              */
	/* this is what is executed when we run the code */
	public static void main(String[] args){
		
		// array of integers (declaration and initialization)
		int[] nums = {1,2,3,4,5,6,7,8,9,10,11};
		System.out.println("nums = " + Arrays.toString(nums) );
		
		
		// array to hold 5 integers
		int[] first = new int[5];
		System.out.println("first (before) = " + Arrays.toString(first) );
		
		// add code to copy the first 5 elements of nums into 
		// the first array

		for(int i=0; i<first.length; i+=1){
			first[i] = nums[i];
		}
		
		System.out.println("first (after)  = " + Arrays.toString(first) );
		
		// add code to the evens() function so that when you pass
		// nums as input it returns the correct array
		
		int[] second = evens(nums);

		System.out.println("second = " + Arrays.toString(second) );
		
	}
}