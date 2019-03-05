/*
     Devon Robitaille
     101031827
*/

import java.util.ArrayList;

public class Target {
  
  private ArrayList<Character> c = new ArrayList<Character>(); // an arrayList that will be of size 1 to contain a character object
  private PVector v = new PVector(); // a PVector object to contain a PVector
  
  public Target(Character c) { // constructor that takes a character
    this.c.add(c); // adds the character to the arrayList of characters
  }
  
  public Target(PVector v) { // constructor that takes a PVector
   this.v.x = v.x; // set the x value of the PVector
   this.v.y = v.y; // set the y value of the PVector
  }
  
  public PVector getTarget() { // on asking for the target
 
    if (c.size()==0) { // if the target is not a character
      return v; // return a PVector
    } else { // if the target is a character
      return c.get(0).getCPos(); //return the center position of the character as a PVector
    }
    
  }  
}