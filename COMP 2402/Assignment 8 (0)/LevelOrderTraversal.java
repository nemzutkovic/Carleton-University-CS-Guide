package comp2402a8;

/**
 * The LevelOrderReaversal interface is a simple interface that allows a 
 * class to implement a level order traversal.
 */
public interface LevelOrderTraversal{

	/*
	* Performs an level order traversal of the tree and outputs a string representation of it. 
	*
	* Levels are separated in the output string by a pipe "|" 
  * Nodes are separated in the output string by a colon ":"
	* Values in a node separated in the output string by a comma ","
	* For example, if the tree has the structure (internal nodes shown, leaves that 
	* are NILs are not shown here). Output is only values in the tree so we don't print NILs.
	*
	*             <10, 20>
	*           /    |     \
	*        <5>  <15,18>  <40>
	*       /        |    /
	*      <2>     <16>  <30,32>
	*
	* The output string would be
	* "10,20|5:15,18:40|2:16:30,32"
	*
	* Note that we do NOT include NILs (leafs) in the output. That is, it only contains the values
	* in the internal nodes of the tree.
	*
	* Note that in the Node data structure, missing values are represented by StringSSet.EMPTY
  *	(which is just null). Do NOT include the nulls in this output. Only include non-null values. 
	*
	* @return a string that represent the level order traversal of the tree
	*/
	public String levelOrder();
}
