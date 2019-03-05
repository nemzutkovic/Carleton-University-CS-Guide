import java.util.Random;
import java.util.Calendar;

/** COMP 1006/1406 Tutorial Material
    <p>
		Testing program for the Animal, Cat and Dog classes.
        
	  @version 1.0
	 */

public class AnimalApp{
	/** number of animals in collection */
	public static final int SIZE = 5;

	/** a collection of names for generating random animals */
	public static String[] names = {"Fluffy", "Tiger", "Spot", 
	    "Bubbles", "Dodger", "Ace", "Flower", "Tiny", "Pip"};
	
	
	public static void main(String[] args){
		Random rnd = new Random();
		
		/* get the current year based on computer's clock */
	  Calendar now = Calendar.getInstance();   
		int year = now.get(Calendar.YEAR);  
		System.out.println("The year is " + year);
		
		/* some animals */
		Animal[] animal_list = new Animal[SIZE];

		/* randomly pick a cat or dog */
		for(int i=0; i<SIZE; i+=1){
			if(Math.random() < 0.3){
				animal_list[i] = new Cat( names[rnd.nextInt(names.length)],rnd.nextInt(12) + 2);
			}else if(Math.random() < 0.6 && Math.random() > 0.3){
				animal_list[i] = new Dog( names[rnd.nextInt(names.length)],rnd.nextInt(14) + 2);
			}else{
				animal_list[i] = new Owl( names[rnd.nextInt(names.length)],rnd.nextInt(16) + 2, rnd.nextBoolean());
			}
		}
	
		for(Animal animal: animal_list){
			System.out.print(animal + "...");
			System.out.println(animal.noise());
		}
	}


}