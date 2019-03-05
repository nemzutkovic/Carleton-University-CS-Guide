package comp2402a5;

public abstract class CuckooHashTable<T> extends OpenAddressHashTable<T> {
	
	int[] codes;
	int arrpos1 = 0;
	int arrpos2 = 1;
	int collision;
	MultiplicativeHashFunction h1 = null;
	MultiplicativeHashFunction h2 = null;
	MultiplicativeHashFunction destination;
	MultiplicativeHashFunction location;
	T tempobject;

	public CuckooHashTable(Class<T> clazz, int[] zz) {
		super(clazz);
		d = 4;
		f = new Factory<T>(clazz);
		t = f.newArray(1<<d);
		h1 = new MultiplicativeHashFunction(zz[arrpos1],w,d);
		h2 = new MultiplicativeHashFunction(zz[arrpos2],w,d);
		codes = zz;
	}
	
	public void rehash(){
		System.out.println("REHASH CALLED");
		T[] oldTable = t;
	    t = f.newArray(1<<d);
	   	arrpos1 += 2;
		arrpos2 += 2;
	    h1 = new MultiplicativeHashFunction(codes[arrpos1],w,d);
        h2 = new MultiplicativeHashFunction(codes[arrpos2],w,d);
        n = 0;
	    for (int i = 0; i < oldTable.length; i++){
	        if (oldTable[i] != null){
	            add(oldTable[i]);
            }
        }
    }

	public void resize(){
		System.out.println("RESIZE CALLED");
		if ((float)(n+1)/t.length > 0.5){
			d++;
			rehash();
		}
		if (((float)n/t.length < 0.125) && d >= 5){
			d--;
			rehash();
		}
	}

	public boolean add(T x){
		if (find(x) != null) return false;
		if ((n+1.0)/t.length > 0.5) resize();
		if (t[h1.hash(x)] == null){
			t[h1.hash(x)] = x;
			n++;
			return true;
		}
		collision = h1.hash(x);
		for (int i= 0; i<=n; i++){
			if (h1.hash(t[collision]) == collision){
				destination = h2;
				location = h1;
			}
			else{
				destination = h1;
				location = h2;
			}
			if (destination.hash(t[collision]) == collision && location.hash(t[collision]) == collision && destination.hash(x) == collision && location.hash(x) == collision){
				rehash();
				add(x);
				return true;
			}
			T tempobject = t[collision];
			t[collision] = x;
			x = tempobject;
			collision = destination.hash(x);
			if (t[destination.hash(x)] == null){
				t[destination.hash(x)] = x;
				n++;
				return true;
			}
		}
		return false;
	}

	public T remove(T x){
		for(int i = 0; i < t.length; i++){
			if (x.equals(t[i])){
				n--;
				t[i] = null;
				if((n*1.0/t.length < 0.125) && d >= 5) resize();
				return x;		
			}
		}
		return null;
	}

	public T find(Object x){
		if(t[h1.hash(x)] == x) return (T)x;
		else if(t[h2.hash(x)] == x) return (T)x;
		return null;
	}
	
}