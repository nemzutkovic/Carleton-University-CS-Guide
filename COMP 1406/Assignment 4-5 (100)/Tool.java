//Poe 100809178
import java.util.Random; 
import javax.swing.*;
import java.awt.*;

public class Tool extends Thing{
  
  protected String name;
  protected String type;
  
  /***/
  public Tool(float xPos, float yPos, float speed, String name, String type) {
    super(xPos,yPos, 0,name, type);
    this.type = type;
    this.name = name;
  }
  /**Modify the characracter attribute*/
  public void characterMod(Character c){
    
    if(type == "Armour"){c.addTool("Armour");}
    else if (type == "Key"){c.addTool("Key");}
    else if (type == "Potion"){
      c.setHealth(100);
      c.setIsPoisoned(false);
    }
    else{c.addTool("Sword");}
  }
  //Color code each treasure 
  @Override
  public void paint(Graphics g) {
    if(type == "Armour"){g.setColor(Color.MAGENTA);}
    else if (type == "Key"){g.setColor(Color.CYAN);}
    else if (type == "Potion"){g.setColor(Color.PINK);}
    else if (type == "Sword") {g.setColor(Color.ORANGE);}
    g.fillRect((int)getxPos(), (int)getyPos(), getWidth(), getHeight());
  }
  
  @Override
  public String toString(){ 
    return this.type +"(" +  this.name+")";
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
}