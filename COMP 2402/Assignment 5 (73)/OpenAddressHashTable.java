package comp2402a5;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class OpenAddressHashTable<T> implements USet<T> {
		
	protected Factory<T> f;
	
	/** the backing array */
	protected T[] t;

	/** The "dimension" of the virtual table (t.length = 2^d) */
	protected int d;

	/** The number of elements in the hash table */
	protected int n;
		
	/** The number of bits in an int */
	protected static final int w = 32;
	
	/** create a new hash table */
	@SuppressWarnings("unchecked")
	public OpenAddressHashTable(Class<T> clazz) {
		f = new Factory<T>((Class<T>)clazz.getClass());
		d = 1;
		t = f.newArray(1<<d);
	}
	
	/**
	 * Resize the table  
	 */
	protected abstract void resize();

	/**
	 * Clear the table (i.e., empty the table of all items)
	 */
	public void clear() {
		n = 0;
		d = 1;
		t = f.newArray(1<<d);		
	}
	
	/**
	 * Return the number of elements stored in this hash table
	 */
	public int size() {
		return n;
	}

	/** 
	 * Adds element x to the table if there does not already exist an item y
	 * in the table such that x.equals(y) is true.
	 *
	 * @param x is the item to add to the table
	 * @return true if x is successfully added to the table, returns false if there already 
	 * an item y in the table such that x.equals(y) is true. 
	*/	
	public abstract boolean add(T x);
	
	
	/** 
	* Remove the copy of x stored in this table if it exists.
	* @param x  the item to remove
	* @return the element y stored in the table such that x.equals(y) is true,
	*  or null if no such element y exists 
	*/
	public abstract T remove(T x);
	
	
	/**
	 * Get the copy of x stored in this table.
	 * @param x - the item to get 
	 * @return - the element y stored in this table such that x.equals(y)
	 * is true, or null if no such element y exists
	 */
	public abstract T find(Object x);

	@Override
	public final String toString(){
		return java.util.Arrays.toString(t);
	}
	
	/**
	 * Iterator for the hash table.  You can implement your own if you wish but
	 * this is not necessary and will not be tested.
	 */
	public Iterator<T> iterator() {
		return null;
	}

	
}
