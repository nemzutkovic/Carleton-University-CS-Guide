public class Box implements Comparable<Box>{
	
	String boxstring;
	
	public Box(String s){
		boxstring = s;
	}

	public int compareTo(Box other){

		return 1;
	}

	@Override
	public String toString(){
		return boxstring;
	}

}