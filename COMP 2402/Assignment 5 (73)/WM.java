package comp2402a5;


/**
 * This class implements the cuckoo hash 
 * 
 * See: Rasmus Pagh, Flemming Friche Rodler, Cuckoo Hashing, Algorithms - ESA 2001, 
 * Lecture Notes in Computer Science 2161, Springer 2001, ISBN 3-540-42493-8
 *
 * @param <T>
 */
public class CuckooHashTable<T> extends OpenAddressHashTable<T> {
	
	/* add any attributes you may need here */
	
	MultiplicativeHashFunction h1 = null;
	MultiplicativeHashFunction h2 = null;
	int[] zValueArray;
	int h1Index = 0;
	int h2Index = 1;
	T[] oldArray;
	Boolean currentlyRehashing = false;
	Boolean rehashCollision = false;

	
	/**
	 * Create a new empty hash table
	 * @param clazz is the class of the data to be stored in the hash table
	 * @param zz is an array of integer values to be used for the has functions
	 */
	public CuckooHashTable(Class<T> clazz, int[] zz) {
		
		// add your code for the constructor here
		
		super(clazz);
		d = 4;
		f = new Factory<T>(clazz);
		t = f.newArray(1<<d);
		zValueArray = zz;

		h1 = new MultiplicativeHashFunction(zz[h1Index], 32, d);
		h2 = new MultiplicativeHashFunction(zz[h2Index], 32, d);

	}
	
	/* define all abstract methods inherited from parent class here */

	public void arrayIndexOutOfBoundsCheck(){
		// h1
		if (h1Index + 2 >= zValueArray.length)
			h1Index = 0;
		else
			h1Index += 2;

		 //h2
		if (h2Index + 2 >= zValueArray.length)
			h2Index = 1;
		else
			h2Index += 2;
	}

	public void rehash(){

		if (!currentlyRehashing){
			oldArray = t;
			currentlyRehashing = true;
			System.out.println("     oldArray is: " + java.util.Arrays.toString(oldArray));
		}

	    t = f.newArray(1<<d);

		 //check if h1 and/or h2 index will go out of bounds of zValueArray
	    arrayIndexOutOfBoundsCheck();

		System.out.println();
	    System.out.println("  REHASHING with: h1 as: " + zValueArray[h1Index] + " h2 as: " + zValueArray[h2Index]);
		System.out.println();

	    h1 = new MultiplicativeHashFunction(zValueArray[h1Index], 32, d);
        h2 = new MultiplicativeHashFunction(zValueArray[h2Index], 32, d);


        // reset n to 0
		n = 0;

	    for (int j = 0; j < oldArray.length; j++){
	        if (oldArray[j] != null){
				System.out.println("REHASH ADDING: j = " + j + " value = " + oldArray[j]);
				System.out.println("     oldArray is: " + java.util.Arrays.toString(oldArray));
	        	System.out.println(" j is : " + j + " and adding : " + oldArray[j]);
				add(oldArray[j]);
				if (j == 2)
					System.out.println("          2 IS HERE");
				if (currentlyRehashing && rehashCollision){
	        		System.out.println("           REHASH COLLISION - RETURNING AT j = " + j + " value = " + oldArray[j]);
	        		if ((j+1) >0)
						add(oldArray[j+1]);
					rehashCollision = false;
					currentlyRehashing = false;
					return;
				}
            }
        }

        if (currentlyRehashing)
        	currentlyRehashing = false;
	    if (rehashCollision)
	    	rehashCollision = false;

		System.out.println();
		System.out.println("*****REHASH COMPLETE****** READY TO ADD CRASHED ELEMENT");
		System.out.println();
    }

	/**
	 * Resize the table
	 */
	public void resize(){

		// HANDLE GROWING
		if ((n+1.0)/t.length > 0.5){
			++d;
			rehash();
		}

		 //HANDLE SHRINKING
		if (((float)n/t.length < 0.125) && d>=5){
			--d;
			rehash();
		}

	}

	/**
	 * Clear the table (i.e., empty the table of all items)
	 */
	public void clear() {
		n = 0;
		d = 4;
		t = f.newArray(1<<d);
	}

	/**
	 * Adds element x to the table if there does not already exist an item y
	 * in the table such that x.equals(y) is true.
	 *
	 * @param x is the item to add to the table
	 * @return true if x is successfully added to the table, returns false if there already
	 * an item y in the table such that x.equals(y) is true.
	 */
	public boolean add(T x){

		System.out.println();
		System.out.println("ADDING: " + x);
		System.out.println("|" + x + "|" + " h1 is: " + h1.hash(x) + " h2 is: " + h2.hash(x));

		System.out.println("  LOAD FACTOR: " + (n+1.0)/t.length);
			if ((n+1.0)/t.length > 0.5){
			System.out.println();
			System.out.println("   ***RESIZING*** ");
			System.out.println();
				resize();
			}

		// If no value exists at position, just add it and increment n
		if (t[h1.hash(x)] == null){
			t[h1.hash(x)] = x;
			n++;
			System.out.println("	*SUCCESS* Added " + x + " at position " + h1.hash(x) + " n is now: " + n);
			System.out.println("Array now:");
			System.out.println(java.util.Arrays.toString(t));
			System.out.println("======================================================================================================================");
			return true;
		}

	    // If value is already in table
		if (find(x) != null){
			System.out.println("	*FAIL* " + x + " is already in position " + h1.hash(x));
			return false;
		}

		// Now, we know value isn't in table:
		// Declare a movingHashFunction variable
		MultiplicativeHashFunction movingHashFunc = h1;
		MultiplicativeHashFunction currentHashFunc = h1;
		int collisionIndex = h1.hash(x);

		System.out.println("*FAIL* " + "COLLISION AT POSITION: " + collisionIndex + " where " + t[collisionIndex] + " is.");
		// If it's not already in HashTable, this means that there is another item in x's h1
		// position that should be shifted to its other hash function position
		for (int i= 0; i<=n; i++){

			// Figure out which hash function to use for the element that needs
			// to be bumped out
			if (h1.hash(t[collisionIndex]) == collisionIndex){
				System.out.println("SHIFTING " + t[collisionIndex] + " to its *h2*" + " position of: " + h2.hash(t[collisionIndex]));
				movingHashFunc = h2;
				currentHashFunc = h1;
			}
			else{
				System.out.println("SHIFTING " + t[collisionIndex] + " to its *h1*" + " position of: " + h1.hash(t[collisionIndex]));
				movingHashFunc = h1;
				currentHashFunc = h2;
			}

			// Check for inifinite collision loop
			if (movingHashFunc.hash(t[collisionIndex]) == collisionIndex && currentHashFunc.hash(t[collisionIndex]) == collisionIndex
					&& movingHashFunc.hash(x) == collisionIndex && movingHashFunc.hash(x) == collisionIndex){
				// same value, infinite loop
				System.out.println("		*HASHES HAVE SAME VALUES AS COLLISION INDEX - INFINITE LOOP DETECTED* - rehashing:");
				if (currentlyRehashing){
					rehashCollision = true;
					System.out.println("    CURRENTLY REHASHING & INFINITE LOOP");
				}
				rehash();
				System.out.println("	Adding back infinite loop element: " + x);
				if (!currentlyRehashing && !rehashCollision)
					add(x);
				return true;
			}

			// Pattern to keep repeating
			T valueToMove = t[collisionIndex];  // Store value that x is replacing
			System.out.println("	Storing : " + valueToMove);
			t[collisionIndex] = x;              // Replace value with x
			System.out.println("	Added " + x + " at position " + currentHashFunc.hash(x) + " n is now: " + n);
			x = valueToMove;                             // Replace x with value to move and keep going
			collisionIndex = movingHashFunc.hash(x);	// update collisionIndex with potential new value

			// If we've found a slot that's null, add it, increment n and return
			if (t[movingHashFunc.hash(x)] == null){
				t[movingHashFunc.hash(x)] = x;
				n++;
				System.out.println("	*COLLISION AVOIDANCE: found spot! Added " + x + " at position " + movingHashFunc.hash(x) + " n is now: " + n);
				System.out.println("Array now:");
				System.out.println(java.util.Arrays.toString(t));
				System.out.println("======================================================================================================================");
				return true;
			}

		}


		// If we get out of this for loop, we have to REHASH, means we couldn't find an empty space
		System.out.println();
		System.out.println("	LOOP FAILED - REHASHING ");
		System.out.println("Array now:");
		System.out.println(java.util.Arrays.toString(t));
		System.out.println();
		if (currentlyRehashing){
			rehashCollision = true;
			System.out.println("    CURRENTLY REHASHING & ENCOUNTERED END OF LOOP");
		}

		rehash();
		if (!currentlyRehashing && !rehashCollision)
			add(x);
		return false;

	}


	/**
	 * Remove the copy of x stored in this table if it exists.
	 * @param x  the item to remove
	 * @return the element y stored in the table such that x.equals(y) is true,
	 *  or null if no such element y exists
	 */
	public T remove(T x){

		System.out.println();
		System.out.println("REMOVING: " + x);
		System.out.println("  |" + x + "|" + " h1 is: " + h1.hash(x) + " h2 is: " + h2.hash(x));

		T valueToReturn;

		// If both positions are null
		if (t[h1.hash(x)] == null && t[h2.hash(x)] == null){
			System.out.println("   BOTH INDEXES NULL - NOT FOUND");
			return  null;
		}

		// If h1 is null
		if (t[h1.hash(x)] == null){
			if (t[h2.hash(x)].equals(x)){
				valueToReturn = removeAt((h2.hash((x))));
				if (needToShrink()){
					resize();
				}
				return valueToReturn;
			}

			else{
				System.out.println("   H1 INDEX NULL - NOT FOUND AT H2");
				return  null;
			}
		}

		 //If h2 is null
		if (t[h2.hash(x)] == null){
			if (t[h1.hash(x)].equals(x)){
				valueToReturn = removeAt((h1.hash((x))));
				if (needToShrink()){
					resize();
				}
				return valueToReturn;
			}
			else{
				System.out.println("   H2 INDEX NULL - NOT FOUND AT H1");
				return  null;
			}

		}

		 //Handling if neither is null
		if (t[h1.hash(x)].equals(x)){
			valueToReturn = removeAt((h1.hash((x))));
			if (needToShrink()){
				resize();
			}
			return valueToReturn;
		}
		else if (t[h2.hash(x)].equals(x)){
			valueToReturn = removeAt((h2.hash((x))));
			if (needToShrink()){
				resize();
			}
			return valueToReturn;
		}
		else{
			System.out.println("   NEITHER INDEX NULL - NOT REMOVED");
			return  null;
		}
	}

	 //Just a helper method for remove to make the code cleaner
	public T removeAt(int index){
		T valueToReturn;

		valueToReturn = t[index];
		n--;
		t[index] = null;
		System.out.println(" REMOVED AT " + index + " -- n is now: " + n + " --");

		return valueToReturn;
	}

	public Boolean needToShrink(){
		return (((float)n/t.length < 0.125) && d>=5);
	}

	/**
	 * Get the copy of x stored in this table.
	 * @param x - the item to get
	 * @return - the element y stored in this table such that x.equals(y)
	 * is true, or null if no such element y exists
	 */
	public T find(Object x){

		System.out.println();
		System.out.println("FINDING: " + x);
		System.out.println("  |" + x + "|" + " h1 is: " + h1.hash(x) + " h2 is: " + h2.hash(x));

		T valueToReturn;

		// If both positions are null
		if (t[h1.hash(x)] == null && t[h2.hash(x)] == null){
			System.out.println("   BOTH INDEXES NULL - NOT FOUND");
			valueToReturn = null;
			return  valueToReturn;
		}

		// If h1 is null
		if (t[h1.hash(x)] == null){
			if (t[h2.hash(x)].equals(x)){
				System.out.println(" FOUND AT " + h2.hash(x));
				valueToReturn = t[h2.hash(x)];
				return valueToReturn;
			}
			else{
				System.out.println("   H1 INDEX NULL - NOT FOUND AT H2");
				valueToReturn = null;
				return  valueToReturn;
			}

		}

		 //If h2 is null
		if (t[h2.hash(x)] == null){
			if (t[h1.hash(x)].equals(x)){
				System.out.println(" FOUND AT " + h1.hash(x));
				valueToReturn = t[h1.hash(x)];
				return valueToReturn;
			}
			else{
				System.out.println("   H2 INDEX NULL - NOT FOUND AT H1");
				valueToReturn = null;
				return  valueToReturn;
			}

		}

		// Handling if neither is null
		if (t[h1.hash(x)].equals(x)){
			System.out.println(" FOUND AT " + h1.hash(x));
			valueToReturn = t[h1.hash(x)];
			return valueToReturn;
		}
		else if (t[h2.hash(x)].equals(x)){
			System.out.println(" FOUND AT " + h2.hash(x));
			valueToReturn = t[h2.hash(x)];
			return valueToReturn;
		}
		else{
			System.out.println("   NEITHER INDEX NULL - NOT FOUND");
			valueToReturn = null;
			return  valueToReturn;
		}

	}
	
}
