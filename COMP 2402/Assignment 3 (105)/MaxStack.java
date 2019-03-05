package comp2402a3;

/**
 * An implementation of a Stack that also can efficiently return  
 * the maximum element in the current Stack.  Every operation -
 * push, pop, peek, and max - must run in constant time
 *
 * @param <T> the class of objects stored in the queue
 */
public class MaxStack<T extends Comparable<T>> extends SLListStack<T> {

	@Override
	public T push(T x) {
		if(size() == 0 || x.compareTo(max()) >= 0){
			super.push(x);
		}
		else{
			push(peek());
		}
		return x;
	}

	public T max(){
		if(size() == 0){
			return null;
		}
		else{
			return peek(); 			
		}
	}

	public static void main(String[] args){
		/*
		MaxStack<Character> stack1 = new MaxStack<Character>();
		String datasequence1 = "ABXABYABZAB";
		for (int i = 0; i < datasequence1.length(); i++){
			stack1.push(datasequence1.charAt(i));
			System.out.println(stack1 + ", max = " + stack1.max());
		}
		while(stack1.size() > 0) {
			stack1.pop();
			System.out.println(stack1 + ", max = " + stack1.max());
		}
		
		MaxStack<Integer> stack2 = new MaxStack<>();
		int[] datasequence2 = new int[]{1,2,3,4,5,6,7,8};
		for (int i = 0; i < datasequence2.length; i++){
			stack2.push(datasequence2[i]);
			System.out.println(stack2 + ", max = " + stack2.max());
		}
		for (int i = 0; i < datasequence2.length/2; i++){
			stack2.pop();
			System.out.println(stack2 + ", max = " + stack2.max());
		}
		for (int i = 0; i < datasequence2.length-1; i++){
			stack2.push(datasequence2[i]);
			System.out.println(stack2 + ", max = " + stack2.max());
		}
		
		MaxStack<Integer> stack3 = new MaxStack<>();
		int[] datasequence3 = new int[]{7,5,6,8,9,2,3,10,1};
		for (int i = 0; i < datasequence3.length; i++){
			stack3.push(datasequence3[i]);
			System.out.println(stack3 + ", max = " + stack3.max());
		}
		for (int i = 0; i < datasequence3.length/2; i++){
			stack3.pop();
			System.out.println(stack3 + ", max = " + stack3.max());
		}
		for (int i = 0; i < datasequence3.length-1; i++){
			stack3.push(datasequence3[i]);
			System.out.println(stack3 + ", max = " + stack3.max());
		}
		*/
	}

}
