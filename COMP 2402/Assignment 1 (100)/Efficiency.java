import comp2402a1.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class Efficiency{
	
	static String[] a = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "", "K", "L", "M"};
	
	static void genExampleInput(String outFile){
		try{
			int size = a.length;
			PrintWriter output   = new PrintWriter(new FileWriter(outFile));
			
			for(int h=0; h<3; h+=1){
				for(int i=0; i<size; i+=1){
					for(int j=0; j<size; j+=1){
						for(int k=0; k<size; k+=1){
							for(int l=0; l<size; l+=1){
								for(int m=0; m<size; m+=1){
									output.println( a[i] + a[j] + a[k] + a[l] + a[m]);
								}
							}
						}
					}
				}
			}
			output.close();
		}catch(Exception e){
			System.out.println(e);}
		
	}
	
	public static void main(String[] args) throws IOException{
		if( args.length == 1){
			if(args[0].equals("gen") ){
				genExampleInput("sample_input.txt");
			}else{
				System.out.println("Usage: java Efficiency [gen]");
			}
			return;
		}
		Part1.main(new String[]{"sample_input.txt", "sample_output1.txt"});
		Part2.main(new String[]{"sample_input.txt", "sample_output1.txt"});
		Part3.main(new String[]{"sample_input.txt", "sample_output1.txt"});
		Part4.main(new String[]{"sample_input.txt", "sample_output1.txt"});
		Part5.main(new String[]{"sample_input.txt", "sample_output1.txt"});
		Part6.main(new String[]{"sample_input.txt", "sample_output1.txt"});
		Part7.main(new String[]{"sample_input.txt", "sample_output1.txt"});
		Part8.main(new String[]{"sample_input.txt", "sample_output1.txt"});
		
	}
}	