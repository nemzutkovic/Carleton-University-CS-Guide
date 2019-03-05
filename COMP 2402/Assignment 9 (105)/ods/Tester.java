package ods;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Tester{
	static long startTime;
	static long endTime;
	static String[] sortedthousand = new String[1000];
	static String[] randomthousand = new String[1000];	
	static String[] sortedtenthousand = new String[10000];
	static String[] randomtenthousand = new String[10000];
	static String[] sortedhundredthousand = new String[100000];
	static String[] randomhundredthousand = new String[100000];	
	static String[] sortedmillion = new String[1000000];
	static String[] randommillion = new String[1000000];

	static void initArrays(){
		System.out.println("GENERATING SORTED ARRAYS...");
		String[] capital = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"};
		int arrlength = capital.length;
		int count = 0;

		// GENERATE THOUSAND ELEMENT ARRAY
		outerloop:
		for(int h=0; h<3; h+=1){
			for(int i=0; i<arrlength; i+=1){
				for(int j=0; j<arrlength; j+=1){
					for(int k=0; k<arrlength; k+=1){
						for(int l=0; l<arrlength; l+=1){
							for(int m=0; m<arrlength; m+=1){
								for(int n=0; n<3; n+=1){
								sortedthousand[count] = (capital[i] + capital[j] + capital[k] + capital[l] + capital[m] + capital[n]);
								randomthousand[count] = (capital[i] + capital[j] + capital[k] + capital[l] + capital[m] + capital[n]);
								count++;
								if(count == 1000) break outerloop;
								}
							}
						}
					}
				}

			}
		}
		System.out.println("GENERATED " + count + " SORTED ELEMENT ARRAY");
		count = 0;

		// GENERATE TEN THOUSAND ELEMENT ARRAY
		outerloop:
		for(int h=0; h<3; h+=1){
			for(int i=0; i<arrlength; i+=1){
				for(int j=0; j<arrlength; j+=1){
					for(int k=0; k<arrlength; k+=1){
						for(int l=0; l<arrlength; l+=1){
							for(int m=0; m<arrlength; m+=1){
								for(int n=0; n<3; n+=1){
								sortedtenthousand[count] = (capital[i] + capital[j] + capital[k] + capital[l] + capital[m] + capital[n]);
								randomtenthousand[count] = (capital[i] + capital[j] + capital[k] + capital[l] + capital[m] + capital[n]);
								count++;
								if(count == 10000) break outerloop;
								}
							}
						}
					}
				}

			}
		}	
		System.out.println("GENERATED " + count + " SORTED ELEMENT ARRAY");
		count = 0;

		// GENERATE HUNDRED THOUSAND ELEMENT ARRAY
		outerloop:
		for(int h=0; h<3; h+=1){
			for(int i=0; i<arrlength; i+=1){
				for(int j=0; j<arrlength; j+=1){
					for(int k=0; k<arrlength; k+=1){
						for(int l=0; l<arrlength; l+=1){
							for(int m=0; m<arrlength; m+=1){
								for(int n=0; n<3; n+=1){
								sortedhundredthousand[count] = (capital[i] + capital[j] + capital[k] + capital[l] + capital[m] + capital[n]);
								randomhundredthousand[count] = (capital[i] + capital[j] + capital[k] + capital[l] + capital[m] + capital[n]);
								count++;
								if(count == 100000) break outerloop;
								}
							}
						}
					}
				}

			}
		}	
		System.out.println("GENERATED " + count + " SORTED ELEMENT ARRAY");
		count = 0;

		// GENERATE ONE MILLION ELEMENT ARRAY
		outerloop:
		for(int h=0; h<3; h+=1){
			for(int i=0; i<arrlength; i+=1){
				for(int j=0; j<arrlength; j+=1){
					for(int k=0; k<arrlength; k+=1){
						for(int l=0; l<arrlength; l+=1){
							for(int m=0; m<arrlength; m+=1){
								for(int n=0; n<3; n+=1){
								sortedmillion[count] = (capital[i] + capital[j] + capital[k] + capital[l] + capital[m] + capital[n]);
								randommillion[count] = (capital[i] + capital[j] + capital[k] + capital[l] + capital[m] + capital[n]);
								count++;
								if(count == 1000000) break outerloop;
								}
							}
						}
					}
				}

			}
		}	
		System.out.println("GENERATED " + count + " SORTED ELEMENT ARRAY");
		System.out.println("GENERATED ALL SORTED DATA SETS");
		System.out.println();
	}
	
	static void randomizeArrays(){
		System.out.println("GENERATING RANDOM DATA SETS...");
		List<String> randomthousandlist = Arrays.asList(randomthousand);
		List<String> randomtenthousandlist = Arrays.asList(randomtenthousand);
		List<String> randomhundredthousandlist = Arrays.asList(randomtenthousand);
		List<String> randommillionlist = Arrays.asList(randommillion);
		Collections.shuffle(randomthousandlist);
		System.out.println("GENERATED 1000 RANDOM ELEMENT ARRAY");
		Collections.shuffle(randomtenthousandlist);
		System.out.println("GENERATED 10000 RANDOM ELEMENT ARRAY");
		Collections.shuffle(randomhundredthousandlist);
		System.out.println("GENERATED 100000 RANDOM ELEMENT ARRAY");
		Collections.shuffle(randommillionlist);
		System.out.println("GENERATED 1000000 RANDOM ELEMENT ARRAY");
		System.out.println("GENERATED ALL RANDOM DATA SETS");
		System.out.println();
	}

	static void skiplistTester(String[] sorted, String[] random){
		SkiplistSSet<String> skiplist = new SkiplistSSet<String>();

		startTime = System.nanoTime();
		for (int i = 0; i < sorted.length; i++) { skiplist.add(sorted[i]); }
		endTime = System.nanoTime();
		System.out.println("Skiplist sorted add()    Execution time: " + 1e-9 * (endTime - startTime));

		startTime = System.nanoTime();		
		for (int i = 0; i < sorted.length; i++) { skiplist.find(sorted[i]); }
		endTime = System.nanoTime();
		System.out.println("Skiplist sorted find()   Execution time: " + 1e-9 * (endTime - startTime));

		startTime = System.nanoTime();
		for (int i = 0; i < sorted.length; i++) { skiplist.remove(sorted[i]); }
		endTime = System.nanoTime();
		System.out.println("Skiplist sorted remove() Execution time: " + 1e-9 * (endTime - startTime));

		startTime = System.nanoTime();
		for (int i = 0; i < random.length; i++) { skiplist.add(random[i]); }
		endTime = System.nanoTime();
		System.out.println("Skiplist random add()    Execution time: " + 1e-9 * (endTime - startTime));

		startTime = System.nanoTime();		
		for (int i = 0; i < random.length; i++) { skiplist.find(random[i]); }
		endTime = System.nanoTime();
		System.out.println("Skiplist random find()   Execution time: " + 1e-9 * (endTime - startTime));

		startTime = System.nanoTime();
		for (int i = 0; i < random.length; i++) { skiplist.remove(random[i]); }
		endTime = System.nanoTime();
		System.out.println("Skiplist random remove() Execution time: " + 1e-9 * (endTime - startTime));
	}
	
	static void goatTester(String[] sorted, String[] random){
		ScapegoatTree<String> scapegoattree = new ScapegoatTree<String>();

		startTime = System.nanoTime();
		for (int i = 0; i < sorted.length; i++) { scapegoattree.add(sorted[i]); }
		endTime = System.nanoTime();
		System.out.println("Scapegoat Tree sorted add()    Execution time: " + 1e-9 * (endTime - startTime));

		startTime = System.nanoTime();		
		for (int i = 0; i < sorted.length; i++) { scapegoattree.find(sorted[i]); }
		endTime = System.nanoTime();
		System.out.println("Scapegoat Tree sorted find()   Execution time: " + 1e-9 * (endTime - startTime));

		startTime = System.nanoTime();
		for (int i = 0; i < sorted.length; i++) { scapegoattree.remove(sorted[i]); }
		endTime = System.nanoTime();
		System.out.println("Scapegoat Tree sorted remove() Execution time: " + 1e-9 * (endTime - startTime));

		startTime = System.nanoTime();
		for (int i = 0; i < random.length; i++) { scapegoattree.add(random[i]); }
		endTime = System.nanoTime();
		System.out.println("Scapegoat Tree random add()    Execution time: " + 1e-9 * (endTime - startTime));

		startTime = System.nanoTime();		
		for (int i = 0; i < random.length; i++) { scapegoattree.find(random[i]); }
		endTime = System.nanoTime();
		System.out.println("Scapegoat Tree random find()   Execution time: " + 1e-9 * (endTime - startTime));

		startTime = System.nanoTime();
		for (int i = 0; i < random.length; i++) { scapegoattree.remove(random[i]); }
		endTime = System.nanoTime();
		System.out.println("Scapegoat Tree random remove() Execution time: " + 1e-9 * (endTime - startTime));
	}

	static void treapTester(String[] sorted, String[] random){
		Treap<String> treap = new Treap<String>();

		startTime = System.nanoTime();
		for (int i = 0; i < sorted.length; i++) { treap.add(sorted[i]); }
		endTime = System.nanoTime();
		System.out.println("Treap sorted add()    Execution time: " + 1e-9 * (endTime - startTime));

		startTime = System.nanoTime();		
		for (int i = 0; i < sorted.length; i++) { treap.find(sorted[i]); }
		endTime = System.nanoTime();
		System.out.println("Treap sorted find()   Execution time: " + 1e-9 * (endTime - startTime));

		startTime = System.nanoTime();
		for (int i = 0; i < sorted.length; i++) { treap.remove(sorted[i]); }
		endTime = System.nanoTime();
		System.out.println("Treap sorted remove() Execution time: " + 1e-9 * (endTime - startTime));

		startTime = System.nanoTime();
		for (int i = 0; i < random.length; i++) { treap.add(random[i]); }
		endTime = System.nanoTime();
		System.out.println("Treap random add()    Execution time: " + 1e-9 * (endTime - startTime));

		startTime = System.nanoTime();		
		for (int i = 0; i < random.length; i++) { treap.find(random[i]); }
		endTime = System.nanoTime();
		System.out.println("Treap random find()   Execution time: " + 1e-9 * (endTime - startTime));

		startTime = System.nanoTime();
		for (int i = 0; i < random.length; i++) { treap.remove(random[i]); }
		endTime = System.nanoTime();
		System.out.println("Treap random remove() Execution time: " + 1e-9 * (endTime - startTime));	
	}

	static void redblackTester(String[] sorted, String[] random){
		RedBlackTree<String> redblacktree = new RedBlackTree<String>();
		
		startTime = System.nanoTime();
		for (int i = 0; i < sorted.length; i++) { redblacktree.add(sorted[i]); }
		endTime = System.nanoTime();
		System.out.println("Red Black Tree sorted add()    Execution time: " + 1e-9 * (endTime - startTime));

		startTime = System.nanoTime();		
		for (int i = 0; i < sorted.length; i++) { redblacktree.find(sorted[i]); }
		endTime = System.nanoTime();
		System.out.println("Red Black Tree sorted find()   Execution time: " + 1e-9 * (endTime - startTime));

		startTime = System.nanoTime();
		for (int i = 0; i < sorted.length; i++) { redblacktree.remove(sorted[i]); }
		endTime = System.nanoTime();
		System.out.println("Red Black Tree sorted remove() Execution time: " + 1e-9 * (endTime - startTime));

		startTime = System.nanoTime();
		for (int i = 0; i < random.length; i++) { redblacktree.add(random[i]); }
		endTime = System.nanoTime();
		System.out.println("Red Black Tree random add()    Execution time: " + 1e-9 * (endTime - startTime));

		startTime = System.nanoTime();		
		for (int i = 0; i < random.length; i++) { redblacktree.find(random[i]); }
		endTime = System.nanoTime();
		System.out.println("Red Black Tree random find()   Execution time: " + 1e-9 * (endTime - startTime));

		startTime = System.nanoTime();
		for (int i = 0; i < random.length; i++) { redblacktree.remove(random[i]); }
		endTime = System.nanoTime();
		System.out.println("Red Black Tree random remove() Execution time: " + 1e-9 * (endTime - startTime));		
	}
		
	public static void main(String[] args){
		initArrays();
		randomizeArrays();

		System.out.println("GENERATING RUN TIMES...");

		System.out.println();
		System.out.println("SKIPLIST 1000 ELEMENTS:");
		System.out.println("-----------------------");
		skiplistTester(sortedthousand, randomthousand);
		System.out.println();
		System.out.println("SKIPLIST 10000 ELEMENTS:");
		System.out.println("------------------------");
		skiplistTester(sortedtenthousand, randomtenthousand);	
		System.out.println();
		System.out.println("SKIPLIST 100000 ELEMENTS:");
		System.out.println("-------------------------");
		skiplistTester(sortedhundredthousand, randomhundredthousand);	
		System.out.println();
		System.out.println("SKIPLIST 1000000 ELEMENTS:");
		System.out.println("--------------------------");
		skiplistTester(sortedmillion, randommillion);
		
		System.out.println();
		System.out.println("SCAPEGOAT TREE 1000 ELEMENTS:");
		System.out.println("-----------------------------");
		goatTester(sortedthousand, randomthousand);
		System.out.println();
		System.out.println("SCAPEGOAT TREE 10000 ELEMENTS:");
		System.out.println("------------------------------");
		goatTester(sortedtenthousand, randomtenthousand);	
		System.out.println();
		System.out.println("SCAPEGOAT TREE 100000 ELEMENTS:");
		System.out.println("-------------------------------");
		goatTester(sortedhundredthousand, randomhundredthousand);	
		System.out.println();
		System.out.println("SCAPEGOAT TREE 1000000 ELEMENTS:");
		System.out.println("--------------------------------");
		goatTester(sortedmillion, randommillion);
		
		System.out.println();
		System.out.println("TREAP 1000 ELEMENTS:");
		System.out.println("--------------------");
		treapTester(sortedthousand, randomthousand);
		System.out.println();
		System.out.println("TREAP 10000 ELEMENTS:");
		System.out.println("---------------------");
		treapTester(sortedtenthousand, randomtenthousand);	
		System.out.println();
		System.out.println("TREAP 100000 ELEMENTS:");
		System.out.println("----------------------");
		treapTester(sortedhundredthousand, randomhundredthousand);	
		System.out.println();
		System.out.println("TREAP 1000000 ELEMENTS:");
		System.out.println("-----------------------");
		treapTester(sortedmillion, randommillion);

		System.out.println();
		System.out.println("RED BLACK TREE 1000 ELEMENTS:");
		System.out.println("-----------------------------");
		redblackTester(sortedthousand, randomthousand);
		System.out.println();
		System.out.println("RED BLACK TREE 10000 ELEMENTS:");
		System.out.println("------------------------------");
		redblackTester(sortedtenthousand, randomtenthousand);	
		System.out.println();
		System.out.println("RED BLACK TREE 100000 ELEMENTS:");
		System.out.println("-------------------------------");
		redblackTester(sortedhundredthousand, randomhundredthousand);	
		System.out.println();
		System.out.println("RED BLACK TREE 1000000 ELEMENTS:");
		System.out.println("--------------------------------");
		redblackTester(sortedmillion, randommillion);
		
		System.out.println("GENERATED ALL RUN TIMES");
	}

}	