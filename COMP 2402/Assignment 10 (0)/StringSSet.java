package comp2402a8;

public abstract class StringSSet{
	// constants to use in your 2-4 Tree implementation
	public static final Node NIL = new Node();
	public static final String EMPTY = null;

	/**
	 * @return the number of elements in this SSet
	 */
  public abstract int size();

	/**
	 * Find the smallest element in the SSet that is greater than or equal to x.
	 * 
	 * @param x
	 * @return the smallest element in the SSet that is greater than or equal to
	 *         x or null if no such element exists
	 */
	public abstract String find(String x);


	/**
	 * Add the element x to the SSet
	 * 
	 * @param x
	 * @return true if the element was added or false if x was already in the
	 *         set
	 */
	public abstract boolean add(String x);

	/**
	 * Remove the element x from the SSet
	 * 
	 * @param x
	 * @return true if x was removed and false if x was not removed (because x
	 *         was not present)
	 */
	public abstract boolean remove(String x);

	/**
	 * Clear the SSet, removing all elements from the set
	 */
	public abstract void clear();


}
