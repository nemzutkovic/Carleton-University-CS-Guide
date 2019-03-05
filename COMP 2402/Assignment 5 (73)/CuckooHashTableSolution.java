package comp2402a5;

/**
 * This class implements the cuckoo hash
 *
 * See: Rasmus Pagh, Flemming Friche Rodler, Cuckoo Hashing, Algorithms - ESA 2001,
 * Lecture Notes in Computer Science 2161, Springer 2001, ISBN 3-540-42493-8
 *
 * @param <T>
 */
public class CuckooHashTable<T> extends OpenAddressHashTable<T>
{
	/** The first object to obtain hash values. */
	protected MultiplicativeHashFunction h1;

	/** The second object to obtain hash values. */
	protected MultiplicativeHashFunction h2;

	/** The minimum dimension this virtual table is allowed to have. */
	protected final int MIN_D = 4;

	/** The values to use for the hashing. */
	protected int[] zValues;

	/** The index of the next z value to use. */
	private int zValueIndex;

	/**
	 * Create a new empty hash table
	 * @param clazz is the class of the data to be stored in the hash table
	 * @param zz is an array of integer values to be used for the hash functions
	 */
	@SuppressWarnings("unchecked")
	public CuckooHashTable(Class<T> clazz, int[] zz)
	{
		super(clazz);

		super.d = this.MIN_D;
		super.f = new Factory<T>(clazz);
		super.n = 0;
		super.t = f.newArray(1 << this.MIN_D);

		this.zValues = zz;
		this.zValueIndex = 0;

		this.h1 = new MultiplicativeHashFunction(this.zValues[zValueIndex++], super.w, this.MIN_D);
		this.h2 = new MultiplicativeHashFunction(this.zValues[zValueIndex++], super.w, this.MIN_D);

	}

	/**
	 * Resize and rehash the table.
	 */
	public void resize()
	{
		if(2 * super.n > super.t.length)
		{
			rehash(grow());
		}

		else if(8 * super.n < super.t.length && super.d > this.MIN_D)
		{
			rehash(shrink());
		}
	}

	/**
	 * Increment the dimension to grow the backing array.
	 *
	 * @return oldT the previous backing array.
	 */
	private T[] grow()
	{
		super.d += 1;
		return newBackingArray();
	}

	/**
	 * Decrement the dimension to shrink the backing array.
	 *
	 * @return oldT the previous backing array.
	 */
	private T[] shrink()
	{
		super.d -= 1;
		return newBackingArray();
	}

	/**
	 * Create a new backing array.
	 *
	 * @return oldT the previous backing array.
	 */
	private T[] newBackingArray()
	{
		T[] oldT = super.t;
		super.t = super.f.newArray(1 << super.d);
		return oldT;
	}

	/**
	 * Rehash the old elements into a new backing array.
	 *
	 * @param oldT the old array to copy and rehash into the new backing array.
	 */
	protected void rehash(T[] oldT)
	{
		this.h1 = new MultiplicativeHashFunction(this.zValues[zValueIndex++], super.w, super.d);
		this.h2 = new MultiplicativeHashFunction(this.zValues[zValueIndex++], super.w, super.d);

		for (T e : oldT)
		{
			if (e != null)
			{
				this.shuffle(e);
			}
		}

	} // end rehash()

	/**
	 * Clear the table (i.e., empty the table of all items)
	 */
	@Override
	public void clear()
	{
		super.n = 0;
		super.d = this.MIN_D;
		super.t = super.f.newArray(1 << this.MIN_D);

	} // end clear()

	/**
	 * Adds element x to the table if there does not already exist an item y in the
	 * table such that x.equals(y) is true.
	 *
	 * @param x is the element to add to the table
	 * @return true if x is successfully added to the table, returns false if there
	 *         already an item y in the table such that x.equals(y) is true.
	 */
	public boolean add(T x)
	{
		if(x == null || this.find(x) != null)
		{
			return false;
		}

		super.n++;
		this.resize();
		this.shuffle(x);

		return true;

	} // end add(T)

	/**
	 * Returns two hash codes of the object from h1 and h2.
	 *
	 * @param x is the Object to get the hash code values from.
	 * @return array of length 2 with the hash code values of x.
	 */
	protected int[] hashOf(Object x)
	{
		return new int[]{this.h1.hash(x), this.h2.hash(x)};

	} // end of hashOf(Object)

	/**
	 * Shuffle the hash table around as needed to add the element.
	 *
	 * @param x is the element to add to the hash table
	 *
	 */
	protected void shuffle(T x)
	{
		int i = 0; // Used to toggle between h1 and h2 hash values.
		T y = x;
		T z = null;
		int loopCount = 0;

		do
		{
			z = super.t[this.hashOf(y)[i % 2]];
			super.t[this.hashOf(y)[i % 2]] = y;

			if(z == null) break; // Add successful

			i = (this.hashOf(z)[i % 2] == this.hashOf(y)[i % 2]) ? i + 1 : i;
			y = z;

			// If the removed value is the same item we're trying to add on the
			// next loop, rehash and try again. Worst case O(n) before finally
			// rehashing.
			if(x == z && this.hashOf(x)[0] == this.hashOf(z)[i % 2] || loopCount >= super.n)
			{
				this.rehash(this.newBackingArray());
				loopCount = 0;
			}

			loopCount++;

		} while(true);

	} // end of shuffle(T)

	/**
	 * Remove the copy of x stored in this table if it exists.
	 *
	 * @param x the item to remove
	 * @return the element y stored in the table such that x.equals(y) is true, or
	 *         null if no such element y exists
	 */
	public T remove(T x)
	{
		if(x == null)
		{
			return null;
		}

		T y1 = super.t[this.hashOf(x)[0]];
		T y2 = super.t[this.hashOf(x)[1]];

		if(x.equals(y1))
		{
			super.t[this.h1.hash(y1)] = null;
			super.n -= 1;
			this.resize();
			return y1;
		}
		else if(x.equals(y2))
		{
			super.t[this.h2.hash(y2)] = null;
			super.n -= 1;
			this.resize();
			return y2;
		}

		return null;

	} // end of remove(T)

	/**
	 * Get the copy of x stored in this table.
	 *
	 * @param x - the item to get
	 * @return - the element y stored in this table such that x.equals(y) is true,
	 *         or null if no such element y exists
	 */
	public T find(Object x)
	{
		T y1 = super.t[this.hashOf(x)[0]];
		T y2 = super.t[this.hashOf(x)[1]];

		if(x.equals(y1))
		{
			return y1;
		}
		else if(x.equals(y2))
		{
			return y2;
		}

		return null;

	} // end of find(Object)

} // end of class CuckooHashTable

// EOF
