/*
 Devon Robitaille
 101031827
 
 ---------
 
 Muse built companion targeting
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import javax.sound.sampled.*;

public class DrawWorld extends JPanel implements MouseListener, MouseMotionListener {
  
  public static Character player; // create a static instance of an orc class, named player
  public static ArrayList<Character> characterList = new ArrayList<Character>(); // an arrayList that will contain information on all characters except for the player
  public static ArrayList<Thing> thingList = new ArrayList<Thing>(); // an arrayList that will contain information on all thing except for the player
  //help move players around, the number refers to how often per second / 1000 = 1 sec
  private Timer timer1 = new Timer(1000/30, new TimerListener()); // timer for moving all characters, but the player
  private Timer playerMovementTimer = new Timer(1000/20, new MovePlayerTimer()); // timer for moving the player
  private Random rand1 = new Random(); // a random instance for use later
  private PVector mouseLocation = new PVector(); // create a PVector to store the location of the mouse
  
  public DrawWorld() { // create an instance of drawWorld
    setBorder(BorderFactory.createLineBorder(Color.black)); // set the layout of the panel
    setFocusable(true);
  } 
  
  public void initSetup() { // this method will be called on initial setup of the world
    
    //Setup the character depending on the character selected in the Main Menu(TestMenu)
    System.out.println("the player");
    System.out.println(World.getCharacter());
    if (World.getCharacter().equals("Elf")){player = new Elf(500, 750, "Player");}
    else if (World.getCharacter().equals("Human")){player = new Human(500, 750, "Player");}
    else if (World.getCharacter().equals("Goblin")){player = new Goblin(500, 750, "Player");}
    else{player = new Orc(500, 750, "Player");}
    System.out.println("the player");
     // give player an actual constructor
    roomSetup(); // call roomSetup(), which creates a fresh instance of each room
    timer1.start(); // start the timer of the characters
    addMouseListener(new MouseAdapter() { // create a listener that listens for mouse action
      @Override
      public void mousePressed(MouseEvent e) { // on mouse pressed
        if (SwingUtilities.isLeftMouseButton(e)) { // check to see if it was the left mouse button pressed            
          mouseLocation.x = e.getX(); // x position of mouseLocation is set to the x position of the mouse
          mouseLocation.y = e.getY(); // y position of mouseLocation is set to the y position of the mouse
          boolean characterSelected = false; // states whether the user selected a character or not
          for (Character character : characterList) { // iterate through the list of all characters in the room
            if ((character.getxPos() <= mouseLocation.x && character.getxPos()+character.getWidth() >= mouseLocation.x) && (character.getyPos() <= mouseLocation.y && character.getyPos()+character.getHeight() >= mouseLocation.y)) { // is the mouse position inside of a character's bounding box
              player.setTarget(character); // set the target of the player as a character
              characterSelected = true; // set it so that the user selected a character
            }      
          }          
          if (!characterSelected) { // if the character was not selected
            player.setTarget(new PVector(mouseLocation.x, mouseLocation.y)); // set the target of the character as a location of the window
          }            
          playerMovementTimer.start(); // start the timer that will move the player a set amount of times per second
        }
        // Muse on right click target enemy
        if (SwingUtilities.isRightMouseButton(e)) { // check to see if it was the right mouse button pressed
          for (Character character : characterList) { // iterate through the list of all characters in the room
            
            //set direction
            //set target - use format character.setTarget(target); - target could be another character or even the player or just some point on the window ei (new PVector(x,y));
            //attack
          }
        }
      }
    });
  }
  
  public void paintComponent(Graphics g) { // method to draw the characters
    super.paintComponent(g); // call to the parent class (world.java) to paint the window
    
    // Below deals with painting the doors
    if (World.rooms.size() > 0) {
      int counter = 0;
      PVector dimension = new PVector();
      PVector coordinate = new PVector();
      FontMetrics metrics = g.getFontMetrics();
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
          coordinate.y = 850;
        } else { //left
          dimension.x = 100;
          dimension.y = 100;
          coordinate.x = 0;
          coordinate.y = 450;
        }
        g.setColor(Color.GRAY);
        g.fillRect((int)coordinate.x,(int)coordinate.y,(int)dimension.x,(int)dimension.y);
        g.setColor(Color.BLACK);
        g.drawString(room.getRoomName(), (int)coordinate.x + (int)((dimension.x-metrics.stringWidth(room.getRoomName()))/2),(int)coordinate.y + (int)((dimension.y-metrics.getHeight())/2 + metrics.getAscent()));
        counter++;
      }
    }
    
    for (Character character : characterList) { // iterate through all characters in the room
      character.paint(g); // draw the character on the window
    }
    for (Thing thing : thingList){
      thing.paint(g);
    }
    if (player != null) { // as long as a object has been attached to the player
      player.paint(g); // draw the player on the window
    }    
    repaint(); // needed, but not sure why
  }
  
  public static void roomSetup() { // setup the room
    characterList.clear();
    thingList.clear();
    for (Character c: World.rooms.get(World.currentRoom).getPlayer()) {
      characterList.add(c);
    }
    for (Thing t: World.rooms.get(World.currentRoom).getThing()) {
      thingList.add(t);
    }
    try {
      String a = World.fileLocation + "\\Door" + ".wav";
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
  
  
  public class TimerListener implements ActionListener { // method that corresponds to the character timer
    int width = 1000; // width of the window
    int height = 1000; // height of the window
    public void actionPerformed(ActionEvent e) { // on the event of timer
      for (int i = 0; i < characterList.size(); i++) { // iterate through the character list
        characterList.get(i).move(); // move the character
      }
    }      
  }
  
  public class MovePlayerTimer implements ActionListener { // method that corresponds to the player timer
    public void actionPerformed(ActionEvent e) { // on the event of timer
      player.move(); // move the player
      if (player.getCPos().dist(player.getTarget().getTarget())<=5) { // if the distance between the player and its target is less the 5
        playerMovementTimer.stop(); // stop the timer
      }
    }
  }
  
  
  //@Override
  public void mouseClicked(MouseEvent e) { // on mouse clicked
    int x = e.getX(); // store the x mouse location into this variable
    int y = e.getY(); // store the y mouse location into the variable
    //System.out.println(x+","+y); // print out to console the x and y of the mouse click
  }
  
  @Override
  public void mousePressed(MouseEvent e) { // on mouse pressed
  }
  
  @Override
  public void mouseReleased(MouseEvent e) { // on mouse released
  }
  
  @Override
  public void mouseEntered(MouseEvent e) { // on mouse entered
  }
  
  @Override
  public void mouseExited(MouseEvent e) { // on mouse exited
  }
  
  @Override
  public void mouseDragged(MouseEvent e) { // on mouse being pressed and moved
  }
  
  public void mouseMoved(MouseEvent e) { // detect to see if mouse has entered the area
    int x = e.getX();
    int y = e.getY();
  }
}