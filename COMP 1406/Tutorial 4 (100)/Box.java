/**
 * A Box object models a box that stores words (strings)
 **/
 
public class Box extends Object{
	private String stuff; // do not change this line

	public void firstconstructor(){
		stuff = null;
	}

	public void secondconstructor(String input){
		stuff = input;
	}

	public String getStuff(){
		if(stuff == null){
			System.out.println("no stuff in here");
		}
		return stuff;
	}

	public String setStuff(String input){
		if(stuff == null){
			stuff = input;
		}
		else{
			stuff = input + "|" + stuff;
		}
		return stuff;
	}

	public String toString(){
		String boxonestars = "";
		if(stuff == null){
			boxonestars = "*****" + "\n" + "*   *" + "\n" + "*****";
		}

		String boxtwostars = "";
		if(stuff != null){
			boxtwostars += "**";
			for(int i = 0; i < stuff.length(); i += 1){
				boxtwostars += "*";
			}
			boxtwostars += "**";
			boxtwostars = boxtwostars + "\n" + "* " + stuff + " *" + "\n" + boxtwostars;		
		}

		return boxonestars + boxtwostars;
	}


	@Override
	public boolean equals(Object o){
		Box o1=(Box)o;
		if(this.getStuff() == o1.getStuff()){
			return true;
		}
		else{
			return false;
		}
	}


	public static void main(String[] args){
		/*Box b = new Box();
		b.getStuff();
		System.out.println(b.setStuff("cat"));
		System.out.println(b.setStuff("dog"));*/

		Box b = new Box();
		System.out.println(b);
		b.setStuff("monsterrrrrrr");
		System.out.println();
		System.out.println(b);

		/*Box c = new Box();
		c.setStuff("cat");
		Box d = new Box();
		d.setStuff("cat");
		System.out.println(c.equals(d));*/

	}
}