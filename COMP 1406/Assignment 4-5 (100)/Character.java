/*
 Devon Robitaille
 101031827
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;
import javax.swing.Timer;
import java.awt.event.*;

public abstract class Character implements Noise{  
  

  private float speed; // deals with how fast a character can move
  private String name; // assign some random name for the character (rp purposes)
  private int defaultHealth; // the default health should be treated as final
  private int currentHealth; // current health can change as the character takes damage and recovers
  private int baseDamage; // the base damage output of the character
  private String race; // the race (ie. orc, goblin, elf, human)
  
  private int width = 20; // width of the rectangle
  private int height = 40; // height of the rectangle
  
  private PVector position = new PVector(); // upper left corner of the rectangle
  private PVector velocity = new PVector(); // the velocity of the character
  private PVector direction = new PVector(); // the direction that the character is travelling
  private PVector cPosition = new PVector(); // the center of the rectangle
  private Target target; // the target of the character (can be another character or a PVector)
  
  private boolean justAttacked = false; // keeps track of whether the character has attacked or not
  private boolean isPoisoned = false; // tracks whether the character has been poisoned or not
  private float poisonOccurence = 1000/30; // set this value to be how often you want the character to be aflicted by poison (ie. 1000/30 is 30 times every second, 1000 is 1sec) - ideally I was thinking like 2-5sec intervals
  private Timer poisonTimer = new Timer((int)poisonOccurence, new TimerListener()); // timer for moving all characters, but the player - also the interval has to be an integer, NOT a float
  
  HashMap<String, Integer> toolMap =  new HashMap<String, Integer>();; // an arrayList of all items that the character has in its inventory
  
  public Character(float xPos, float yPos, float speed, String name, int defaultHealth, int baseDamage, String race) {
    position.x = xPos; // x coordinate of the upper left of the rectangle
    position.y = yPos; // y coordinate of the upper left of the rectangle
    cPosition.x = xPos+(width/2); // x coordinate of the middle of the rectangle
    cPosition.y = yPos+(height/2); // y coordinate of the middle of the rectangle
    this.speed = speed; // speed of the character
    this.name= name; // name of the character
    this.defaultHealth = defaultHealth; // default health of the character treat is final, it should never be changed
    currentHealth = defaultHealth; // set current health as the default health
    this.baseDamage = baseDamage; // base damage of the character
    this.race = race; // race of the character
    toolMap.put("Armour",0 );//Setup the characters toolbelt
    toolMap.put("Key",0);
    toolMap.put("Potion",0);
    toolMap.put("Sword",0);
    toolMap.put("Flashy",0);
  }
  
  public abstract void paint(Graphics g); // abstract method from which each character will be drawn onto the screen
  public String getName() { return name; } // return name of the character
  public void setName(String name) { this.name = name; } // set name of character
  public int getHealth() { return this.currentHealth; } // return current health of the character
  public void setHealth(int currentHealth) { this.currentHealth = currentHealth; } // set current health of the character
  public int getDefaultHealth() { return this.defaultHealth; } // return the default health of the character
  public void setFullHealth() { this.currentHealth = defaultHealth; } // set the current health back to default health ( max health )
  public void attacked(int damage) { 
    if (haveTool("Armour")){
      System.out.println("Reduced Damage by Armour");
      this.currentHealth = this.currentHealth - damage + 10; 
    }
    else {
      System.out.println("Regular Damage no Armour");
      this.currentHealth = this.currentHealth - damage;
    }
  } // deal damage to the character depending on he tools
  public int getDamage() { 
    if (haveTool("Sword")){
      System.out.println("Increased Sword Damage");
      return this.baseDamage + 10; 
    } 
    else{
      System.out.println("Regular Sword Damage");
      return this.baseDamage;
    }
  }// return base damage of the character, the base damage is increased if the character has a sword
  public int getWidth() { return width; } // return width of the character
  public void setWidth(int width) { this.width = width; } // set width of the character  
  public int getHeight() { return height; }  // get height of the character
  public void setHeight(int height) { this.height = height; } // set height of the character
  public void setxVel(int xVel) { this.velocity.x = xVel; } // set x velocity of the character
  public void setyVel(int yVel) { this.velocity.y = yVel; }// set y velocity of the character
  public PVector getVel() { return velocity; }  // return velocity as a PVector
  public PVector getDir() { return direction; } // return direction as a PVector
  public float getSpeed() { return speed; }  // return speed of the character
  public void setSpeed(float speed) { this.speed = speed; }  // set the speed of the character
  public boolean getJustAttacked() { return justAttacked; } // return the boolean state of whether the character has attacked or not
  public void setJustAttacked(boolean b) { justAttacked = b; }  // set the boolean state of the "justAttacked" variable of the character
  public String getRace() { return race; } // return the race of the character
  public boolean getIsPoisoned() { return isPoisoned; } // return whether the character has been poisoned or not
  public void setIsPoisoned(boolean b) { isPoisoned = b; } // set the boolean state of whether the character is poisoned or not
  public Timer getPoisonTimer() { return poisonTimer; }
  
  //Deal with x plane
  public float getxPos() { return position.x; } // return the upper left x position of the character
  public void setxPos(float x) { // set the x pos of the upper left and middle of the character
    this.position.x = x;
    this.cPosition.x = position.x+(width/2);
  }
  
  //Deal with the y plane
  public float getyPos() { return position.y;} // return the upper left y position of the character
  public void setyPos(float y) { // set the y pos of the upper left and middle of the character
    this.position.y = y;
    this.cPosition.y = position.y+(height/2);
  }
  
  public PVector getPos() { return position; }   // return the upper left position of the character as a PVector
  public PVector getCPos() { return cPosition; } // returb the center position of the character as a PVector
  
  public void setTarget(Character c) { // set the target of the character as another character
    target = null;
    target = new Target(c);
  }  
  public void setTarget(PVector v) { // set the target of the character
    target = null;
    target = new Target(v);
  }  
  public Target getTarget() { return target; } // return the target as a target
  
  public void setDirection(float theta_radians) { // set the direction the character is headed in using radians to calculate the angle
    direction.x = (float) Math.cos(theta_radians); // set the x direction
    direction.y = (float) Math.sin(theta_radians); // set the y direction
    if (direction.mag()>0) { // if the magnitude of the direction vector is greater than zero
      direction.normalise(); // turn the direction vector into a unit vector
    }
  }  
  
  public void updateMovement() { // update the position of the character
    setDirection(getCPos().angleBetween((getTarget().getTarget()))); // set the direction of the character by using the angle between the center of the character and the target
    getVel().x = getDir().x * getSpeed(); // set the x velocity of the character based on direction and speed
    getVel().y = getDir().y * getSpeed(); // set the y velocity of the character based on direction and speed
    getPos().x += getVel().x; // set the x position of the character by adding x velocity
    getPos().y += getVel().y; // set the y position of the character by adding y velocity
    getCPos().x += getVel().x; // set the x center position of the character by adding the x velocity
    getCPos().y += getVel().y; // set the y center position of the character by addind the y velocity
  }  
  
  public void checkCollisionWithRoom() { // check for collisions with the border of the room
    if (position.x < 0) { // position of character is to the left of the room
      setxPos(1); // set x position inside of room on far left side
      if (!race.equals("Orc")) { // makes sure that the race of the character is not an orc
        setTarget((new PVector()).random(getCPos())); // give the character a new random direction
      }  
    }
    if(position.x+width > 1000){ // position of character is to the right of the room
      setxPos(1000-width); // set x position of the character inside of room on the far right side
      if (!race.equals("Orc")) { // makes sure that the race of the character is not an orc
        setTarget((new PVector()).random(getCPos())); // give the character a new random direction
      } 
    }
    if(position.y < 0){ // position of character is to the top of the room
      setyPos(1); // set y position of character to the top side of the room
      if (!race.equals("Orc")) { // makes sure that the race of the character is not an orc
        setTarget((new PVector()).random(getCPos())); // give the character a new random direction
      } 
    }
    if(position.y+height > 1000){ // position of character is to the bottom of the room
      setyPos(1000-height); // set y position of character to the bottom side of the room
      if (!race.equals("Orc")) { // makes sure that the race of the character is not an orc
        setTarget((new PVector()).random(getCPos())); // give the character a new random direction
      } 
    }
  }
  
  public class TimerListener implements ActionListener { // method that corresponds to the character timer
    int poisonDamage = 5; // the value from which the character will lose health do to posion
    public void actionPerformed(ActionEvent e) { // on the event of timer
      currentHealth -= poisonDamage; // health is being lost do to poison
    }
  }      
  
  public abstract void move(); // abstract method to move the character, dealt by the individual character
  public abstract boolean pathFinding(); // abstract method to return whether the character is currently tracking, dealt by the individual character
  public abstract void checkCollision(); // abstract method to check to see if the character collided with something of importance, dealt by the individual character
  public void steal(Character c){this.name = this.name;}
  
  /**Goblin related character methods*/
  /**Check if the character has a specific tool*/
  public boolean haveTool(String t){
    if(toolMap.get(t) == 0){return false;}
    else {return true;}
  }
  /**Add a tool to the characters arsenal*/
  public void addTool(String t){
    toolMap.put(t,toolMap.get(t)+1);
  }
  /**Remove a tool from the characters arsenal*/
  public void removeTool(String t){
    toolMap.put(t,toolMap.get(t)-1);
  }
  
  
  
  @Override
  public String toString() { return name + " " + race; } // when the character is called by its variable name return the name and race of the character
}