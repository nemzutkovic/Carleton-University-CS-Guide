// Nemanja Zutkovic
// 101085982

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;
import javax.sound.sampled.*;

public class Elf extends Character implements Noise{
  
  public Elf(int xPos, int yPos, String name) {
    super(xPos, yPos, 10, name, 60, 15, "Elf");
    setTarget(new PVector(xPos,yPos));
  }
  public  void makeNoise(Character c) {
    try {
      String a;
      if (c.getHealth() <=0){
        a = World.fileLocation + "\\death" + ".wav";
      }
      else{
        a = World.fileLocation + "\\" + c.getRace() + ".wav";
      }
      //System.out.println("Played this sound" + " " + a);
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(a).getAbsoluteFile());
      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      clip.start();
    } catch(Exception ex) {
      //System.out.println("Error with playing sound.");
      ex.printStackTrace();
    }
  }
  
  //where everything about this character will be drawn (ie. rectangle, image)
  public void paint(Graphics g) {
    g.setColor(Color.MAGENTA);
    g.fillRect((int)getxPos(), (int)(getyPos()), getWidth(), getHeight());
  }
  
  public void move() {
    if(World.getCharacter().equals("Elf")){
      setDirection(getCPos().angleBetween((getTarget().getTarget())));   
      if (pathFinding()) {
        updateMovement();
      }
      checkCollision();
    }else{
      if (getCPos().dist(DrawWorld.player.getCPos())<350 && getJustAttacked() == false) {
        setTarget(DrawWorld.player);
      } else if (!pathFinding() || (getTarget().getTarget() == DrawWorld.player.getCPos() && getCPos().dist(DrawWorld.player.getCPos())>=350)) {
        setTarget((new PVector()).random(getCPos()));
      }
      
      if (pathFinding()) {
        updateMovement();
      }
      checkCollision();
    }
  }
  public boolean pathFinding() {
    if (getCPos().dist(getTarget().getTarget()) < 5) {
      setJustAttacked(false);
      return false;
    }
    return true;
  }
  
  public void checkCollision() {
    checkCollisionWithRoom();
    
    for (int i = 0; i < DrawWorld.characterList.size(); i++) { //size - 1 because of self, +1 because of player, net total is original size
      Character c = DrawWorld.characterList.get(i);
      if (c.getRace().equals(getRace())) { //checks to see if race does not match its own
        PVector cV = c.getPos(); // save the PVector of the character
        if ( (cV.x < getPos().x) && (cV.x+c.getWidth() > getPos().x) && (cV.y < getPos().y) && (cV.y+c.getHeight() > getPos().y) ) {
          if (c.getRace().equals("Goblin")){
            System.out.println(toolMap);
            c.steal(this);
            System.out.println(toolMap);
          }
        }
      }
    }
        //check collision with food or thing
    for (int i = 0 ; i < World.rooms.get(World.currentRoom).getThing().size(); i++) {
      Thing t = World.rooms.get(World.currentRoom).getThing().get(i);
      PVector tV = t.getPos();
      if (tV.x < getPos().x && tV.x+t.getWidth() > getPos().x && tV.y < getPos().y && tV.y+t.getHeight() > getPos().y) {
        System.out.println("Elf collided with " + t.getName());
        World.rooms.get(World.currentRoom).getThing().remove(i);
        //how the player reacts to items
        t.characterMod(this); // problem
      }
    }
          PVector dimension = new PVector();
      PVector coordinate = new PVector();
      int counter = 0;
      for (Room room : World.rooms.get(World.currentRoom).getRoom()) {
        if (counter == 0) { //top
          dimension.x = 100;
          dimension.y = 100;
          coordinate.x = 450;
          coordinate.y = 0;
        } else if (counter == 1) { //right
          dimension.x = 100;
          dimension.y = 100;
          coordinate.x = 900;
          coordinate.y = 450;
        } else if (counter == 2) { //bottom
          dimension.x = 100;
          dimension.y = 100;
          coordinate.x = 450;
          coordinate.y = 900;
        } else { //left
          dimension.x = 100;
          dimension.y = 100;
          coordinate.x = 0;
          coordinate.y = 450;
        }
        counter++;
        
        //below checks to see if player hasnt entered the "door"
        if ( (coordinate.x < getPos().x) && (coordinate.x+dimension.x > getPos().x) && (coordinate.y < getPos().y) && (coordinate.y+dimension.y > getPos().y) ) {
          if (room.getName().equals("Goal") && !haveTool("Key")) {
            System.out.println("You need a key to continue");
            break;
          }else if(!World.getCharacter().equals(getRace())){break;} 
          else {
            World.currentRoom = room.getRoomNumber()-1;
            DrawWorld.roomSetup();
            setxPos(500-getWidth()); // x coordinate of the upper left of the rectangle
            setyPos(500-getHeight()); // y coordinate of the upper left of the rectangle
            setTarget(new PVector(500,500));
          }
          break;
        }
      }
    Character c = DrawWorld.player;
    PVector cV = c.getPos();
    //kinda buggy needs to be fixed
    if ( (cV.x < getPos().x) && (cV.x+c.getWidth() > getPos().x) && (cV.y < getPos().y) && (cV.y+c.getHeight() > getPos().y) ) {    
      if (getJustAttacked() == false ) {
        //System.out.println("Damage: " + getDamage() + "    Health: " + c.getHealth());
        c.attacked(getDamage());
        setJustAttacked(true);
        setTarget(new PVector(((float)getDir().x*-(float)Math.PI)*50, getDir().y*-1*50)); //need to fix direction
        //System.out.println(pathFinding() + "   " + c.getHealth());
      }
      if ( c.getHealth() <= 0 ) {        
        //System.out.println(c + " has died...");
        System.exit(0);
      }
    }        
  }
  
}