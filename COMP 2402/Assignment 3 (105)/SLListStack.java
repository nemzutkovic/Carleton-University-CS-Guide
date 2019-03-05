package comp2402a3;

import java.util.AbstractCollection;
import java.util.Iterator;

/**
 * An implementation of a LIFO Stack as a singly-linked list.
 *
 * @param <T> the class of objects stored in the queue
 */
public class SLListStack<T> extends AbstractCollection<T> {

	class Node {
		T x;
		Node next;
	}
	
	Node head;
	Node tail;
	int n;
	
	public Iterator<T> iterator() {
		class SLIterator implements Iterator<T> {
			protected Node p;

			public SLIterator() {
				p = head;
			}
			public boolean hasNext() {
				return p != null;
			}
			public T next() {
				T x = p.x;
				p = p.next;
				return x;
			}
			public void remove() {
				throw new UnsupportedOperationException();
			}
		}
		return new SLIterator();
	}

	@Override
	public int size() {
		return n;
	}

	/**
	 * Stack peek operation - return head's data element
	 * @return x
	 */
	public T peek() {
		return head.x;
	}
	
	/**
	 * Stack push operation - push x onto the head of the list
	 * @param x the element to push onto the stack
	 * @return x
	 */
	public T push(T x) {
		Node u = new Node();
		u.x = x;
		u.next = head;
		head = u;
		if (n == 0)
			tail = u;
		n++;
		return x;
	}

	/**
	 * Stack pop operation - pop off the head of the list
	 * @return the element popped off 
	 */
	public T pop() {
		if (n == 0)	return null;
		T x = head.x;
		head = head.next;
		if (--n == 0) tail = null;
		return x;
	}	

}
