//Rusen Mustafa Ozoruc
//101010199

import java.util.ArrayList;
import java.util.Random; 
import javax.swing.*;
import java.awt.*;



public class Treasure extends Thing{
  
  public Treasure(float xPos, float yPos,String name) {
   super(xPos,yPos, 0,"Flashy", "Treasure");
  }  
 @Override
 public void characterMod(Character c) {
   c.setSpeed(12);
   c.addTool("Flashy");
   System.out.println("The characters new speed is: " + c.getSpeed());
  }
 
 @Override
 public void paint(Graphics g) {
   g.setColor(Color.BLACK);
   g.fillRect((int)getxPos(), (int)getyPos(), getWidth(), getHeight());
  }
 
  @Override
 public void move(){
 setDirection(getCPos().angleBetween((getTarget().getTarget())));   
    if (pathFinding()) {
      updateMovement();}
  }
  
 public boolean pathFinding(){return true;}
}


 








