package comp2402a8;

/** 
* Node class for a 2-4 tree with String values
*
* Each node stores a String array of size 3. 
* Use null to denote the absence of a string in a position.
* Each node stores a Node array of size 4 (for its children).
* Each node stores a reference to its parent. (StringSet.NIL of the node is the root)
*/

public class Node{
	String[] data;
	Node   parent;
	Node[] children;

	public Node(){
		data = new String[]{StringSSet.EMPTY, StringSSet.EMPTY, StringSSet.EMPTY};
		children = new Node[]{StringSSet.NIL,StringSSet.NIL,StringSSet.NIL,StringSSet.NIL};
		parent = StringSSet.NIL;
	}
}