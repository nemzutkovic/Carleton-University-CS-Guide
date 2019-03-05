public class Cat extends Animal{

	public Cat(String name, int birthYear){
		super(name, birthYear);
	}

	@Override
	public String noise(){
		return "Meow";
	}
}