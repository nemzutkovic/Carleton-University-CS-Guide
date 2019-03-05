package comp2402a6;

/**
 * This class represents a node in a binary tree. This class is abstract and
 * should be subclassed by a concrete class. See, for example the class
 * SimpleBinaryTreeNode.
 * 
 * @author morin
 * 
 * @param <Node>
 *            the class of the children of this node
 */
public class BinaryTreeNode<Node extends BinaryTreeNode<Node>> {
	public Node left;
	public Node right;
	public Node parent;	
}
