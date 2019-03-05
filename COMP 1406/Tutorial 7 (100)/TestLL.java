public class TestLL{
	public static void main(String[] args){
		// create an empty linked list
		LList list = new LList();
		System.out.print("empty list : ");
		System.out.println(list);
		
		// create a list with one element
		// list = [cat]
		list = new LList(new Node("cat"));
		System.out.print("singleton  : ");
		System.out.println(list);

		// add some elements to the back and front
		list.addBack("dog");
		list.addFront("owl");
		list.addBack("bat");
		System.out.print("some adds  : ");
		System.out.println(list);
		
		// abritrary adds
		list.add(1, "crow");
		list.add(1, "goat");
		list.add(2, "eel");
		System.out.print("more adds  : ");
		System.out.println(list);
		
		// some gets
		System.out.println("x0 = " + list.get(0));
		System.out.println("x1 = " + list.get(1));
		System.out.println("x5 = " + list.get(5));
		System.out.println("xn = " + list.get(list.getSize()-1));
		
		// removes
		list.removeFront();
		list.removeFront();
		System.out.println("removes  : " + list);

		// removes
		list.remove(3);
		list.remove(list.getSize()-1);
		System.out.println("removes  : " + list);

		// remove front add to back
		System.out.println("before  : " + list);
		System.out.println("move front to back ");
		list.addBack( list.removeFront() );
		System.out.println("after   : " + list);

		LList l1 = new LList(new Node("a"));
		l1.addFront("b").addBack("c").add(1,"d");
		LList l2 = new LList(new Node("a"));
		l1.addFront("b").addBack("c").add(1,"eeee");
		
		System.out.println( "l1.compareTo(l2) = " + l1.compareTo(l2));
		
		// uncomment this next line
		// System.out.println( "same(l1,l2) = " + same(l1,l2))	;
		
		
	}

}