import java.io.FileReader;
import java.io.BufferedReader;

import java.io.FileWriter;
import java.io.PrintWriter;


public class CopyFile {
  
  // makes a copy of Files.java
  public static void main(String[] args) { 
    
    String in_fileName = "Files.java";       // name of input file
    String out_fileName = "Files-copy.java"; // name of output file
    
    String line = null;   // string to hold lines read from file
    
    try{
      FileReader ifile = new FileReader( in_fileName );
      FileWriter ofile = new FileWriter( out_fileName );
      
      BufferedReader reader = new BufferedReader(ifile);
      PrintWriter writer = new PrintWriter(ofile);
      
      line = reader.readLine();
      while(line != null) {
        writer.println(line);
        line = reader.readLine();    
      }
      reader.close();
      writer.close();
      
    }catch(java.io.FileNotFoundException e){
      // might be thrown by FileReader constructor
      System.err.println("A File was not found");
    }catch(java.io.IOException e){
      // might be thrown when reading data
      System.err.println("IOException thrown : " + e);
    } 
    
    System.out.println(in_fileName + " --> " + out_fileName);
    
    
  }
  
  
}
