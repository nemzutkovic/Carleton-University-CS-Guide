public class Dog extends Animal{

	public Dog(String name, int birthYear){
		super(name, birthYear);
	}

	@Override
	public String noise(){
		return "Woof";
	}
}