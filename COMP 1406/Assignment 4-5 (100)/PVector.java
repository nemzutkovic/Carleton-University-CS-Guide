/*
     Devon Robitaille
     101031827
*/

import java.util.Random;

public class PVector {
  
  private Random rand1 = new Random(); // create a random object of later
  
  public float x; // a x value (will be the x coordinate of any vector)
  public float y; // a y value (will be the y coordinate of any vector)
  
  public PVector(float xPos, float yPos) { // constructor is made up of an xPos and a yPos
    this.x = xPos; // store the xPos into the x variable
    this.y = yPos; // store the yPos into the y variable
  }
  
  public PVector() { // empty constructor
   this.x = 0; // set the x variable as 0
   this.y = 0; // set the y variable as 0
  }
  
  public PVector getPos() { return this; } // return this object as itself (position as a PVector)
  
  public void normalise() { // method to create a unit vector
    x = x/mag(); // divide the x value by the magnitude of the vector
    y = y/mag(); // divide the y value by the magnitude of the vector
  }
  
  public float angleBetween(PVector v) { // method to get the value between two vectors ( should be clarified, 2 vectors as in 2 points)
    float delta_x = v.x - x; // difference in the x values of the vectors
    float delta_y = v.y - y; // difference in the y values of the vectors
    float theta_radians = (float)Math.atan2(delta_y, delta_x); // the theta (in radians) of the vector
    return theta_radians; // return the theta in radians
  }
  
  public float mag() { // method to calculate magnitude of the vector
    return (float)Math.sqrt(Math.pow(x,2) + Math.pow(y,2)); // return magnitde of the vector
  }
  
  public float dot(PVector v) { // method to calculate the dot product of 2 vectors
    return (x*v.x)+(y*v.y); // returns the dot product of 2 vectors
  }
  
  public float dist(PVector v) { // method to calculate distance between 2 vectors ( remember vector is a point)
    return (float)Math.sqrt(Math.pow(x-v.x,2) + Math.pow(y-v.y,2)); // return the distance between 2 vectors
  }
  
  public PVector random(PVector v) { // create a random vector (purpose was to create a random location that a character is going to travel to)
    Random r = new Random(); // create a new random object
    float theta_radians = r.nextFloat() * (float)Math.PI*2; // theta (in radians) is equals to some random float between 0 and 2 PI
    
    int max = 50; // max distance the character can travel
    int min = 10; // min distance the character can travel
    
    float distance = min + r.nextFloat() * (max-min); // pick a random distance between the min and max distance the character can travel
    
    float xPos = v.x + ((float)Math.cos(theta_radians) * (distance)); // set the xPos as the direction times the distance
    float yPos = v.y + ((float)Math.sin(theta_radians) * (distance)); // set the yPos as the direction times the distance

    return (new PVector(xPos, yPos)); // return the xPos and yPos as a PVector
  }
  
  @Override
  public String toString() { // if the PVector is called by its object/variables name
    return "x: " + x + "   y: " + y; // return the x position and the y position
  }
  
}
  