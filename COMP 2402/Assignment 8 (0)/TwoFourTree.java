package comp2402a8;

/**
 * The TwoFourTree class is an implementation of the 2-4 tree from notes/textbook.
 * The tree will store Strings as values. 
 * It extends the (modified) sorted set interface (for strings).
 * It implements the LevelOrderTraversal interface. 
 */
public class TwoFourTree extends StringSSet implements LevelOrderTraversal{

		// your class MUST have a zero argument constructor. All testing will use this ocnstructor to create a 2-3 tree.
		
		public TwoFourTree(){
			Node r = new Node();
		}

		public void clear(){
		}

		public boolean add(String x){
			return true;
		}

		public String find(String x){
			return "";
		}

		public int size(){
			return 0;
		}

		public boolean remove(String x){
			return true;
		}

		@Override
		public String levelOrder(){
			// Base Case 
	        if(root == null) 
	            return; 
	        // Create an empty queue for level order tarversal 
	        Queue<Node> q =new LinkedList<Node>(); 
	        // Enqueue Root and initialize height 
	        q.add(root); 
	        while(true){  
	        // nodeCount (queue size) indicates number of nodes at current level. 
	        	int nodeCount = q.size(); 
	            if(nodeCount == 0) 
	                break; 
	        // Dequeue all nodes of current level and Enqueue all nodes of next level 
	            while(nodeCount > 0) 
	            { 
	                Node node = q.peek(); 
	                System.out.print(node.data + " "); 
	                q.remove(); 
	                if(node.left != null) 
	                    q.add(node.left); 
	                if(node.right != null) 
	                    q.add(node.right); 
	                nodeCount--; 
	            } 
	            System.out.println(); 
	        } 
		}

		public static void main(String[] args){

			TwoFourTree tree = new TwoFourTree();
			Node r = new Node();

		 	r.data[0] = "I";
	        r.data[1] = "N";
	        // LEVEL 1
	        r.children[0] = new Node();
	        r.children[0].parent = r;
	        r.children[1] = new Node();
	        r.children[1].parent = r;
	        r.children[2] = new Node();
	        r.children[2].parent = r;
	 
	        r.children[0].data[0] = "B";
	        r.children[0].data[1] = "D";
	        r.children[0].data[2] = "G";
	 
	        r.children[1].data[0] = "L";
	 
	        r.children[2].data[0] = "P";
	        r.children[2].data[1] = "T";
	        // LEVEL 2
	        r.children[0].children[0] = new Node();
	        r.children[0].children[0].parent = r.children[0];
	        r.children[0].children[1] = new Node();
	        r.children[0].children[1].parent = r.children[0];
	        r.children[0].children[2] = new Node();
	        r.children[0].children[2].parent = r.children[0];
	        r.children[0].children[3] = new Node();
	        r.children[0].children[3].parent = r.children[0];
	 
	        r.children[0].children[0].data[0] = "A";
	 
	        r.children[0].children[1].data[0] = "C";
	 
	        r.children[0].children[2].data[0] = "E";
	        r.children[0].children[2].data[1] = "F";
	 
	        r.children[0].children[3].data[0] = "H";
	 
	        r.children[1].children[0] = new Node();
	        r.children[1].children[0].parent = r.children[1];
	        r.children[1].children[1] = new Node();
	        r.children[1].children[1].parent = r.children[1];
	 
	        r.children[1].children[0].data[0] = "J";
	        r.children[1].children[0].data[1] = "K";
	 
	        r.children[1].children[1].data[0] = "M";
	 
	        r.children[2].children[0] = new Node();
	        r.children[2].children[0].parent = r.children[2];
	        r.children[2].children[1] = new Node();
	        r.children[2].children[1].parent = r.children[2];
	        r.children[2].children[2] = new Node();
	        r.children[2].children[2].parent = r.children[2];
	 
	        r.children[2].children[0].data[0] = "O";
	 
	        r.children[2].children[1].data[0] = "Q";
	        r.children[2].children[1].data[1] = "R";
	        r.children[2].children[1].data[2] = "S";
	 
	        r.children[2].children[2].data[0] = "U";

	        //OUTPUT: I,N|B,D,G:L:P,T|A:C:E,F:H:J,K:M:O:Q,R,S:U

		}

}
