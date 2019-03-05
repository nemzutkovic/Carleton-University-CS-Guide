package comp2402a6;

public class GeometricTreeNode extends BinaryTreeNode<GeometricTreeNode> {
	public Point2D position;
	
	public GeometricTreeNode() {
		position = new Point2D();
	}
	
	public String toString() {
		return position.toString();
	}
}
