package comp2402a5;

public class Cuckoo<T> extends CuckooHashTable<T>{
	public Cuckoo(Class<T> clazz, int[] zz) {
		super(clazz, zz);
	}
	
	T[] getArray(){
		T[] copyOfArray = f.newArray(1<<d);
		System.arraycopy(t, 0, copyOfArray, 0, t.length);
		return copyOfArray;
	}
	
	
	public static void main(String[] args){
		int[] Z;
		//Z = new int[]{203603160,795059951,635370791,148986655,722330249,289380458,878844539,207980018,807032966,323662852,625299260,117040950,732242342,308195263,195261182,333114067,514663906,512051041,337645530,883157390,661116422,836097764,710023872,899936543,26857598,695484241,180613357,444659657};
		Z = new int[]{1445229539, 372911817, 2113534159, 1880685119, 532791015, 1428079773, 465632135,515464461,662629821,762656823,9825825,226123127,662629829,322648487,516546433,662454625};

		System.out.println("zz=" + java.util.Arrays.toString(Z) );
		CuckooHashTable<String> c = new Cuckoo<String>(String.class, Z);
		System.out.println( "h1=" + java.util.Arrays.toString(c.h1.getParams()) );
		System.out.println( "h2=" + java.util.Arrays.toString(c.h2.getParams()) );
		System.out.println(c);
		c.add("cat");
		System.out.println(c);
		c.add("dog");
		System.out.println(c);
		c.add("eel");
		System.out.println(c);
		c.add("cow");
		System.out.println(c);
		c.add("elf");
		System.out.println(c);
		c.add("car");
		System.out.println(c);
		c.add("elk");
		System.out.println(c);
		c.add("cop");
		System.out.println(c);
		c.add("mop");
		System.out.println(c);
		c.add("muk");
		System.out.println(c);
		System.out.println("OUTPUT IS:");
		System.out.println( java.util.Arrays.toString( ((Cuckoo)c).getArray() ) );
		System.out.println("OUTPUT SHOULD BE:");
		//System.out.println("[elk, null, null, null, null, null, bat, eel, null, null, null, null, elf, cat, null, null, null, calf, rat, cow, null, null, null, null, null, null, null, dog, null, null, null, null]");
		System.out.println("[elf, null, eel, dog, cat, cow, null, null, null, null, null, null, null, null, null, null]");
		System.out.println("");
		c.remove("cat");
		System.out.println(c);
		c.remove("dog");
		System.out.println(c);
		c.remove("eel");
		System.out.println(c);
		c.remove("cow");
		System.out.println(c);
		c.remove("elf");
		System.out.println(c);
		c.remove("car");
		System.out.println(c);
		c.remove("elk");
		System.out.println(c);
		c.remove("cop");
		System.out.println(c);
		c.remove("mop");
		System.out.println(c);
		c.remove("muk");
		System.out.println(c);
		System.out.println("");

		int[] zz={465632135,515464461,662629821,762656823,9825825,226123127,662629829,322648487,516546433,662454625,621621233,97878421,35489871,35489873,97878425,762656823,9825825};
		CuckooHashTable<Integer> map = new Cuckoo<Integer>(Integer.class,zz);
		map.add(1);
		System.out.println( java.util.Arrays.toString( ((Cuckoo)map).getArray() ) );
		map.add(10);
		System.out.println( java.util.Arrays.toString( ((Cuckoo)map).getArray() ) );
		map.add(38);
		System.out.println( java.util.Arrays.toString( ((Cuckoo)map).getArray() ) );
		map.add(47);
		System.out.println( java.util.Arrays.toString( ((Cuckoo)map).getArray() ) );
		map.add(56);
		System.out.println( java.util.Arrays.toString( ((Cuckoo)map).getArray() ) );
		map.add(84);
		System.out.println( java.util.Arrays.toString( ((Cuckoo)map).getArray() ) );
		map.add(93);
		System.out.println( java.util.Arrays.toString( ((Cuckoo)map).getArray() ) );
		map.add(141);
		System.out.println( java.util.Arrays.toString( ((Cuckoo)map).getArray() ) );
		map.add(157);
		System.out.println("OUTPUT IS:");
		System.out.println( java.util.Arrays.toString( ((Cuckoo)map).getArray() ) );
		System.out.println("OUTPUT SHOULD BE:");
		System.out.println("[10, 1, 38, 47, 56, null, 84, null, null, null, 141, 157, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 93, null, null, null]");
		map.remove(1);
		System.out.println( java.util.Arrays.toString( ((Cuckoo)map).getArray() ) );
		map.remove(10);
		System.out.println( java.util.Arrays.toString( ((Cuckoo)map).getArray() ) );
		map.remove(38);
		System.out.println( java.util.Arrays.toString( ((Cuckoo)map).getArray() ) );
		map.remove(47);
		System.out.println( java.util.Arrays.toString( ((Cuckoo)map).getArray() ) );
		map.remove(56);
		System.out.println( java.util.Arrays.toString( ((Cuckoo)map).getArray() ) );
		map.remove(84);
		System.out.println( java.util.Arrays.toString( ((Cuckoo)map).getArray() ) );
		map.remove(93);
		System.out.println( java.util.Arrays.toString( ((Cuckoo)map).getArray() ) );
		map.remove(141);
		System.out.println( java.util.Arrays.toString( ((Cuckoo)map).getArray() ) );
		map.remove(157);
		System.out.println( java.util.Arrays.toString( ((Cuckoo)map).getArray() ) );
	}
	
}
