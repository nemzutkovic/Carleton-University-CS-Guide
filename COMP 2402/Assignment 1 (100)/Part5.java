package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class Part5 {
	
	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */

	public static class LengthSortComparator implements Comparator<String> {

		@Override
		public int compare(String line1, String line2){
			if(line1.length() > line2.length()){
				return 1;
			}
			else if(line1.length() < line2.length()){
				return -1;
			}
			else{
				return line1.compareTo(line2);
			}	
		}

	}

	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		List<String> sortedlist = new ArrayList<String>();

		for (String line = r.readLine(); line != null; line = r.readLine()) {
            sortedlist.add(line);
        }

        Collections.sort(sortedlist, new LengthSortComparator());

        for (String text : sortedlist) {
            w.println(text);
        }

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
			System.out.println("Execution time: " + 1e-9 * (stop - start));
		} catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}
}
