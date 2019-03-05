package comp2402a6;

import java.util.Collections;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class GeometricTree extends BinaryTree<GeometricTreeNode> {

	int xdistance = 0;
	Map<GeometricTreeNode, Integer> nodeMap = new HashMap<GeometricTreeNode, Integer>();

	public GeometricTree() { super (new GeometricTreeNode()); }
	
	public void inorderDraw() {
		assignLevels();
		GeometricTreeNode node = firstNode();
		for (int i = 0; node != nil; i++) {
			node.position.x = i;
			node = nextNode(node);
		}
	}
	
	protected void randomX(GeometricTreeNode u, Random r) {
		if (u == null) return;
		u.position.x = r.nextInt(60);
		randomX(u.left, r);
		randomX(u.right, r);
	}
	
	public void leftistDraw() {
		assignLevels();
		HashMap<Integer,Integer> xytable = new HashMap<Integer,Integer>();
		GeometricTreeNode node;
		for (node = firstNode(); node != nil; node = nextNode(node)) {
			if(xytable.containsKey(node.position.y)){
				xytable.put(node.position.y, xytable.get(node.position.y) + 1);
				node.position.x = xytable.get(node.position.y);
			}
			else{
				xytable.put(node.position.y, 0);
				node.position.x = 0;
			}
		}
	}
	
	public void balancedDraw() {
		assignLevels();
		GeometricTreeNode node = r;
		GeometricTreeNode prev = nil;
		GeometricTreeNode next = nil;
		GeometricTreeNode temp = nil;
		
		while (true) {
			if(node == nil) break;
			if (prev == node.parent) {
				if (node.left != nil) next = node.left;
				else if (node.right != nil) next = node.right;
				else{
					if(node.left != nil){
						if(node.right != nil) nodeMap.put(node, nodeMap.get(node.left) + nodeMap.get(node.right) + 1);
						else nodeMap.put(node, nodeMap.get(node.left) + 2);
					}
					else{
						if(node.right != nil) nodeMap.put(node, nodeMap.get(node.right) + 2);
						else nodeMap.put(node, 3);
					}
					next = node.parent;
				}
			} 
			else if (prev == node.left) {
				if (node.right != nil) next = node.right;
				else{
					if(node.left != nil){
						if(node.right != nil) nodeMap.put(node, nodeMap.get(node.left) + nodeMap.get(node.right) + 1);
						else nodeMap.put(node, nodeMap.get(node.left) + 2);
					}
					else{
						if(node.right != nil) nodeMap.put(node, nodeMap.get(node.right) + 2);
						else nodeMap.put(node, 3);
					}
					next = node.parent;
				}
			} 
			else{
				if(node.left != nil){
					if(node.right != nil) nodeMap.put(node, nodeMap.get(node.left) + nodeMap.get(node.right) + 1);
					else nodeMap.put(node, nodeMap.get(node.left) + 2);
				}
				else{
					if(node.right != nil) nodeMap.put(node, nodeMap.get(node.right) + 2);
					else nodeMap.put(node, 3);
				}
				next = node.parent;
			}
			prev = node;
			node = next;
		}
		changeCoordinates(r,0,0);
	}

	public void changeCoordinates(GeometricTreeNode node, int x, int y) {
		if (node == nil) return;
		if(x > xdistance) xdistance = x;
		node.position.x = x;
		node.position.y = y;

		if(node.left != nil && node.right == nil) this.changeCoordinates(node.left, xdistance+1, y);
		else if(node.left != nil && node.right != nil){
			if(nodeMap.get(node.left) > nodeMap.get(node.right)){
				this.changeCoordinates(node.right, x, y+1);
				this.changeCoordinates(node.left, xdistance+1, y);
			}
			else{
				this.changeCoordinates(node.left, x, y+1);
				this.changeCoordinates(node.right, xdistance+1, y);
			}
		}
		else this.changeCoordinates(node.right, xdistance+1, y);
	}
		
	protected void assignLevels() {
		LinkedList<GeometricTreeNode> queue = new LinkedList<GeometricTreeNode>();
		GeometricTreeNode node;
		int count = 0;
		int nodecount = queue.size();
		if (r == nil) return;
		r.position.y = count;
		queue.add(r);
		while(true){
			nodecount = queue.size();
			if(nodecount == 0) break;
			count++;
			while (nodecount > 0) 
			{
				node = queue.peek();
				queue.remove();
				if (node.left != nil) {
					node.left.position.y = count;
					queue.add(node.left);
				} 
				if (node.right != nil){
					node.right.position.y = count;
					queue.add(node.right);
				}
				nodecount--;
			}
		}
	}
	
	protected void assignLevels(GeometricTreeNode u, int i) {
		if (u == null) return;
		u.position.y = i;
		assignLevels(u.left, i+1);
		assignLevels(u.right, i+1);
	}
	
	public static void main(String[] args) {
	}
	
}