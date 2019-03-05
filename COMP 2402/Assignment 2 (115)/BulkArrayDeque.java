package comp2402a2;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BulkArrayDeque<T> extends ArrayDeque<T> {
	
	public BulkArrayDeque(Class<T> clazz) {
		super(clazz);
	}

	/**
	 * Remove all the elements with indices that belong to the range [i, j)
	 * @param i the starting index
	 * @param c the stopping index
	 */
   @Override
	 public void removeRange(int start, int end) {
		if (start < 0 || start >= n || end < 0 || end > n) {
			throw new IndexOutOfBoundsException();
		}
		if (start >= end) {
		}
		else{
			int removing = end - start;
			int leftover = n - removing;
			T[] output = f.newArray(leftover);
			for (int k = 0; k < start; k++) {
				output[k] = a[(j + k) % a.length];
			}
			for (int k = start; k < leftover; k++) {
				int shiftedIndex = k + removing;
				output[k] = a[(j + shiftedIndex) % a.length];
			}
			a = output;
			j = 0;
			n = a.length;			
		}
	}
	
	/**
	 * testing method
	 */
	public static void doIt(BufferedReader r, PrintWriter w){

	    BulkArrayDeque c = new BulkArrayDeque(Integer.class);
	    List<Integer> arr = new ArrayList<Integer>();
	    
	    System.out.println("\nTest Case 1 - add 20 elements:");
	    for(int i=0;i<20;i++)     c.add(i);
	    for(int i=0;i<20;i++)     arr.add(i);
	    System.out.print("BulkArrayDeque:");
	    for (Object o:c){   System.out.print(" "+(Integer)o+" ");     }
	   	System.out.print("\nArrayList:     ");
	    for (int num:arr){ System.out.print(" "+num+" ");				  }  
		
		System.out.println();
	    System.out.print("\nTest Case 2 - remove(2):");
	    c.remove(2);
	    arr.remove(2);	
	    System.out.print("\nBulkArrayDeque:");
	    for (Object o:c){   System.out.print(" "+(Integer)o+" ");     }
	   	System.out.print("\nArrayList:     ");
	    for (int num:arr){ System.out.print(" "+num+" ");				  }  
		
		System.out.println();
	    System.out.println("\nTest Case 3 - removeRange(2,8):");
	    System.out.print("PreremoveRange:  ");
		for (Object o:c){   System.out.print(" "+o+" ");     }

		c.removeRange(2,8);
		System.out.print("\nPostremoveRange: ");
		for (Object o:c){   System.out.print(" "+o+" ");     }

		arr.subList(2,8).clear();
	    System.out.print("\nBulkArrayDeque:  ");
	    for (Object o:c){   System.out.print(" "+(Integer)o+" ");     }
	   	System.out.print("\nArrayList:       ");
	    for (int num:arr){ System.out.print(" "+num+" ");				  }  
	    System.out.println();
	    System.out.println("\nTest Case 4:");
    	for(int i=5;i<10;i++)     c.add(i,i);
    	for(int i=5;i<10;i++)     arr.add(i,i);
	    System.out.print("BulkArrayDeque:");
	    for (Object o:c){   System.out.print(" "+(Integer)o+" ");     }
	   	System.out.print("\nArrayList:     ");
	    for (int num:arr){ System.out.print(" "+num+" ");				  }  

	    System.out.println();
	    System.out.print("\nTest Case 5:");
	    c.removeRange(4,10);
		arr.subList(4,10).clear();
	    System.out.print("\nBulkArrayDeque:");
	    for (Object o:c){   System.out.print(" "+(Integer)o+" ");     }
	   	System.out.print("\nArrayList:     ");
	    for (int num:arr){ System.out.print(" "+num+" ");				  }  

	    System.out.println();
	    System.out.print("\nTest Case 6:");
		c.removeRange(7,4);
		arr.subList(0,0).clear();
	    System.out.print("\nBulkArrayDeque:");
	    for (Object o:c){   System.out.print(" "+(Integer)o+" ");     }
	   	System.out.print("\nArrayList:     ");
	    for (int num:arr){ System.out.print(" "+num+" ");				  }
	}
	
	
	/**
	 * The driver.  Open a BufferedReader and a PrintWriter, either from System.in
	 * and System.out or from filenames specified on the command line, then call doIt.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader r;
			PrintWriter w;
			if (args.length == 0) {
				r = new BufferedReader(new InputStreamReader(System.in));
				w = new PrintWriter(System.out);
			} else if (args.length == 1) {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(System.out);				
			} else {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(new FileWriter(args[1]));
			}
			long start = System.nanoTime();
			doIt(r, w);
			w.flush();
			long stop = System.nanoTime();
			System.err.println("\nExecution time: " + 1e-9 * (stop-start));
		} catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}
}
