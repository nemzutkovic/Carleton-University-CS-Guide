import java.time.ZonedDateTime;
import java.time.ZoneId;  

public class Owl extends Animal{

	public boolean wise;
	public Owl(String name, int year, boolean wise){
		super(name, year);
		this.wise = wise;
	}

	public String noise(){
		return "Hoooo";
	}

	@Override
	public String toString(){
		int year = ZonedDateTime.now( ZoneId.of("America/Montreal")).getYear();
		int result = year - birthYear;

		String wiseornot;

		if(this.wise == true){
			wiseornot = "...I am wise!";
		}
		else{
			wiseornot = "...I am not wise!";
		}

		return name + " " + Integer.toString(birthYear) + wiseornot; 
	}
}