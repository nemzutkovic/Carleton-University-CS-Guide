public class Node{
	protected String data;
	protected Node   next;

	public Node(String d){ this(d, null);}
	public Node(String d, Node n){
		this.data = d; this.next = n;
	}
	public String getData(){ return data; }
	public Node   getNext(){ return next; }
	
	public void setData(String d){ data = d; }
	public void setNext(Node n){ next = n;}

	@Override
	public String toString(){
		return data;
	}
}