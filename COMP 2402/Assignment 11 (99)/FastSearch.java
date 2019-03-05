package comp2402a11;

public class FastSearch{

  public static final int UNIFORM = 1;
  public static final int INCREASING = 2;
  public static final int DECREASING = 3;
  public static final int UNKNOWN = 4;

  public static int search(long[] a, long key, int method){
    // UNIFORM DATA
    if(method == 1) return bs2(a,key);
    // EARLY TARGET
    if(key < a[a.length>>13]) return bs0(a, 0, a.length>>13, key);
    // LATE TARGET
    if(key > a[a.length & 9996588]) return bs0(a, a.length & 9996588, a.length, key);
    // ANY TARGET
    int i = 1;
    while (i < a.length && a[i] <= key) i*=2;
    return bs0(a, i/2, i<a.length? i+1:a.length, key);
  }

  // If Key < Bit Shifted Array: Perform Galloping Search, Else: Interpolation Search
	public static int search(long[] a, long key){
		if( key < a[a.length>>6] ) return gallopingSearch(a,key);
		return bs2(a,key);
	}
  // Galloping Search To Create Bounds
  static int gallopingSearch(long[] a, long key){
    int bound = 1;
    int size = a.length;
    while (bound < size && a[bound] < key)
      bound = bound << 1;
    return bs0(a, bound>>1, bound<size? bound+1:size, key);
  }
  // Binary Search With Bounds
  static int bs0(long[] a, int fromIndex, int toIndex, long key){
    int low = fromIndex;
    int high = toIndex - 1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      long midVal = a[mid];
        if (midVal < key)
          low = mid + 1;
        else if (midVal > key)
          high = mid - 1;
        else
          return mid;
    }
    return -1;
  }
  // Interpolation Search
  static int bs2(long[] arr, long key){
    int low = 0;
    int high = arr.length - 1;
    int mid=1;
    try{
      while ((arr[high] != arr[low]) && (key >= arr[low]) && (key <= arr[high])) {
        mid = low + (int)((key - arr[low]) * (high - low) / (arr[high] - arr[low]));
        if (arr[mid] < key)
          low = mid + 1;
        else if (key < arr[mid])
          high = mid - 1;
        else
          return mid;
      }
    }catch(Throwable t){   
      throw(t);
    }
    if (key == arr[low]) return low ;
    else return -1;
  }

}