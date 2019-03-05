package comp2402a5;

/**
* Multiplicative hash function from ODS 5.1.1 
* hash(x) returns ((x.hashCode() * z) mod 2^w) div 2^(w-d)
*/
public class MultiplicativeHashFunction{
	private int z;    
	private int w;
	private int d;
	
	public MultiplicativeHashFunction(int zz, int ww, int dd){
		this.z = zz;
		this.w = ww;
		this.d = dd;
	}

	public int[] getParams(){ return new int[]{z,w,d}; }
	

	public int hash(Object x){
		return (z * x.hashCode()) >>> (w-d);
	}

	/* not used 
	public int hash(int y){
		return (z * y) >>> (w-d);
	}
	*/
	
	@Override
	public String toString(){
		return "(z,w,d)=(" + z + "," + w + "," + d + ")";
	}
}