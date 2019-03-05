import java.io.FileReader;
import java.io.BufferedReader;

/*
 a multi-line block of comments
 at the top of the class
 that should be filtered out!
*/

public class Files {
 
  /* here is a block
   * of comments
   * with a // inside it!
   * and a blank line!
   
   */
 
  public static void main(String[] args) { 
    
    String fileName = "Files.java";  // name of file to read
  
    String line = null;   // string to hold lines read from file

    
    try{
      FileReader file = new FileReader( fileName );
      BufferedReader reader = new BufferedReader(file);
      
     line = reader.readLine();
      while(line != null) {
        System.out.println(line);
          line = reader.readLine();    
      }
      reader.close();
            
    }catch(java.io.FileNotFoundException e){
      // might be thrown by FileReader constructor
      System.err.println("File " + fileName + " was not found");
    }catch(java.io.IOException e){
      // might be thrown when reading data
      System.err.println("IOException thrown : " + e);
    } 
    
    
    
    
    // lots of blank lines here!
    
    
    
    
    
    
  }

  
}
