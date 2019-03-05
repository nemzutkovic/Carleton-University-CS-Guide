// Nemanja Zutkovic
// 101085982
import java.util.Random; 
import javax.swing.*;
import java.awt.*;

public class Food extends Thing{
  
  private String name;
  private String foodType;
  
  // Constructor for food. Takes in the name of the food and if it is "GoodFood" or "BadFood". Keeping it simple.
  public Food(float xPos, float yPos, float speed, String name, String foodType){
    super(xPos,yPos, 0,name, foodType);
    this.foodType = foodType;
  }
  
  public void characterMod(Character c){
    if(foodType.equals("GoodFood")){
      // Heals character by 25%.
      System.out.println("Player's old health: " + c.getHealth());
      c.setHealth(c.getHealth() + (int)((float)c.getDefaultHealth()*0.25));
      if (c.getHealth() > c.getDefaultHealth()) {
        c.setFullHealth();
      }
      System.out.println("Player's current health: " + c.getHealth());
    }
    else if(foodType.equals("BadFood")){
      // Poisons character.
      c.setIsPoisoned(true);
      c.getPoisonTimer().start(); // start the poison timer, which will occur a set amount of times over a period until the timer stops
      // just for reference if you want the timer to stop - c.getPoisonTimer().stop(); - simple as that
    }
  }
  @Override
  public void paint(Graphics g) {
    g.setColor(Color.GREEN);
    g.fillRect((int)getxPos(), (int)getyPos(), getWidth(), getHeight());
  }
  
  @Override
  public String toString(){ 
    return this.foodType +", " +  this.name;
  }
  @Override
  public boolean pathFinding(){    
    if (getCPos().dist(getTarget().getTarget()) < 5) {
      //target = null;
      return false;
    }
    return true;
  }
  @Override
  public void move() {
    setDirection(getCPos().angleBetween((getTarget().getTarget())));   
    if (pathFinding()) {
      updateMovement();
    }
  }
  // Just testing out making foods. Erase this when implemented.
//  public static void main (String[] args){
//    Food f1 = new Food("apple", "GoodFood");
//    System.out.println(f1.name + " : " + f1.foodType);
//    Food f2 = new Food("poisonapple", "BadFood");
//    System.out.println(f2.name + " : " + f2.foodType);
//  }
  
}