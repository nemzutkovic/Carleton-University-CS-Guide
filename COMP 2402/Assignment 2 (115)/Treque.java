package comp2402a2;

import java.util.AbstractList;

/**
 * Treque : an implementation of the List interface 
 * that allows for fast modifications at the head, tail
 * and middle of the list.
 *
 * Modify the methods so that 
 *  -set/get have constant runtime
 *  -add/remove have O(1+min{i, size()-i, |size()/2-i|})
 *              amortized runtime.
 * 
 * @author morin/ArrayDeque
 *
 * @param <T> the type of objects stored in this list
 */
public class Treque<T> extends AbstractList<T> {
	/**
	 * The class of elements stored in this queue
	 */
	protected Factory<T> f;
	
	/**
	 * Array used to store elements
	 */
	protected T[] a;
	
	/**
	 * Index of next element to de-queue
	 */
	protected int j;
	
	/**
	 * Number of elements in the queue
	 */
	protected int n;
	
	/**
	 * Grow/shrink the treque if one side gets more than 1 element.
	 */
	protected void resize() {
		if ((frontarrdq.size() - backarrdq.size()) >= 2){
			backarrdq.add(0, frontarrdq.remove(frontarrdq.size() - 1));
		} 
		else if((backarrdq.size() - frontarrdq.size()) >= 2){
			frontarrdq.add(frontarrdq.size(), backarrdq.remove(0));
		}
	}

	// Declare the front ArrayDeque and back ArrayDeque.
	protected ArrayDeque<T> frontarrdq;
	protected ArrayDeque<T> backarrdq;

	/**
	 * Constructor: Initialize the front ArrayDeque and back ArrayDeque.
	 */
	public Treque(Class<T> t) {
		frontarrdq = new ArrayDeque<T>(t);
		backarrdq = new ArrayDeque<T>(t);
	}
	
	// Return the total length of the Treque (length of front ArrayDeque + length of back ArrayDeque).
	public int size() {
		n = frontarrdq.size() + backarrdq.size();
		return n;
	}

	/**
	 *  If the index is less than 0 or greater than the length of the Treque, throw an exception.
     *  If the index is in the front portion of the Treque, we return the element at index i.
	 *  Otherwise, the element is in the back ArrayDeque, and we return the element at, index i minus the length of the front ArrayDeque.
	 */
	public T get(int i) {
		if (i < 0 || i >= size()){
			throw new IndexOutOfBoundsException();
		}
		else if(i < frontarrdq.size()){
			return frontarrdq.get(i);
		}
		else{
			return backarrdq.get(i - frontarrdq.size());
		}
	}
	
	/** 
	 *  If the index is less than 0 or greater than the length of the Treque, throw an exception.
     *  If the index is in the front portion of the Treque, we set x at index i and return the element.
	 *  Otherwise, we set x in the back ArrayDeque and return the element at, index i minus the length of the front ArrayDeque.
	 */
	public T set(int i, T x) {
		if (i < 0 || i >= size()){
			throw new IndexOutOfBoundsException();
		}
		else if(i < frontarrdq.size()){
			return frontarrdq.set(i, x);
		}
		else{
			return backarrdq.set(i - frontarrdq.size(), x);
		}
	}
	
	/** 
	 *  If the index is less than 0 or greater than the length of the Treque, throw an exception.
     *  If the index is in the front portion of the Treque, we add x at index i.
	 *  Otherwise, we will be adding x in the back ArrayDeque, at index i minus the length of the front ArrayDeque.
	 *  Lastly, to ensure both sides of the Treque are balanced, we call resize().
	 */
	public void add(int i, T x) {
		if (i < 0 || i > size()){
			throw new IndexOutOfBoundsException();	
		}
		else if(i < frontarrdq.size()){
			frontarrdq.add(i, x);
		}
		else{
			backarrdq.add(i - frontarrdq.size(), x);
		}
		resize();
	}
	
	/** 
	 *  If the index is less than 0 or greater than the length of the Treque, throw an exception.
     *  If the index is in the front portion of the Treque, we remove the element at index i.
	 *  Otherwise, the element to remove is in the back ArrayDeque, at index i minus the length of the front ArrayDeque.
	 *  Lastly, to ensure both sides of the Treque are balanced, we call resize().
	 */
	public T remove(int i) {
		T element;
		if (i < 0 || i >= size()){
			throw new IndexOutOfBoundsException();
		}
		else if(i < frontarrdq.size()){
			element = frontarrdq.remove(i);
		}
		else{
			element = backarrdq.remove(i - frontarrdq.size());
		}
		resize();
		return element;
	}
	
	// Clear the front ArrayDeque and back ArrayDeque.
	public void clear() {
		frontarrdq.clear();
		backarrdq.clear();
	}
}