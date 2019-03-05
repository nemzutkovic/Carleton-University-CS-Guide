package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

public class Part3 {
	
	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */

	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		Map<String, Integer> sortedduplicatesmap = new TreeMap<>();

		for (String line = r.readLine(); line != null; line = r.readLine()) {
            if(sortedduplicatesmap.containsKey(line)){
            	sortedduplicatesmap.put(line, sortedduplicatesmap.get(line) + 1);
            }
            else{
            	sortedduplicatesmap.put(line, 1);
            }
        }

        for (String text : sortedduplicatesmap.keySet()) {
        	if(sortedduplicatesmap.get(text) > 1){
        		for(int i = 0; i < sortedduplicatesmap.get(text); i++){
        			w.println(text);
        		}
        	}
        	else{
        		w.println(text);
        	}
            
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