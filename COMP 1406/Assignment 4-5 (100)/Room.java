/*
     Devon Robitaille
     101031827
*/

import java.util.*; // import arraylist

public class Room {
  
  private int roomNumber; // keep track of the room number
  private String roomName; // keep track of the room name
  private String faction; //neutral, ally, enemy
  private ArrayList<Room> adjacentRooms = new ArrayList<Room>(); // store all the adjacent rooms in an arrayList
  private ArrayList<Character> character = new ArrayList<Character>(); // store all the characters in the room in an arrayList
  private ArrayList<Thing> things = new ArrayList<Thing>(); // store all the things in the room in an arrayList
  
  public Room(String roomNumber, String roomName) { // constructor that takes a room number and a room name
    this.roomNumber = Integer.valueOf(roomNumber); // set the room number ( room number is orginally string and has to be converted to an integer )
    this.roomName = roomName; // set the room name
  }
  
  public String getName() { return roomName; }
  
  public void addRoom(Room[] r) { // add an array of rooms
    for (Room n : r) { // iterate through the array of rooms
     adjacentRooms.add(n); // add each room to the adjacent rooms arrayList
    }
  }
  
  public void addPlayer(Character[] p) { // add an array of characters
    for (Character n : p) { // iterate through the array of characters
      character.add(n); // add each character to the character arrayList
    }
  }
  
  public void addThing(Tool[] t) { // add an array of things
    for (Tool n : t) { // iterate through the array of things
      things.add(n); // add each things to the things arrayList
    }
  }
  
  public void setRoomNumber(int rN) { this.roomNumber = rN; } // set the room number
  public int getRoomNumber() { return this.roomNumber; } // get the room number
  
  public void setRoomName(String rN) { this.roomName = rN; } // set the room name
  public String getRoomName() { return this.roomName; } // get the room name
  
  public void addRoom(Room r) { adjacentRooms.add(r); } // add a singular room to the adjacent room arrayList
  public ArrayList<Room> getRoom() { return adjacentRooms; } // return the adjacent rooms as an arrayList
  
  public void addPlayer(Character p) { character.add(p); } // add a singular character to the character arrayList
  public ArrayList<Character> getPlayer() { return character; } // return all characters in the room as an arrayList
  public int sizeOfPlayer() { return character.size(); } // return the size of the character arrayList
  
  public void addThing(Tool t) { things.add(t); } // add a singular thing to the thing arrayList
  public void addThing(Food f) { things.add(f); } // add a singular food to the thing arrayList
  public void addThing(Treasure x) { things.add(x); } // add a singular food to the thing arrayList
  
  
  public ArrayList<Thing> getThing() { return things; } // return all things in the room as an arrayList
  
  @Override
  public String toString() { // if the room is called by its object/variable name
   return getRoomNumber() + " " + getRoomName(); // return the room number and the room name
  }
  
}