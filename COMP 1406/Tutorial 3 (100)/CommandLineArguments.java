public class CommandLineArguments{
	public static void main(String[] args){
		int len = args.length;
		if(len == 0){
			System.out.println("You didn't pass any command line arguments");
			System.out.println("Try again.");
			return; // exit program
		}
		
		for(int i=0; i<len; i+=1){
			System.out.println( "args[" + i + "] = " + args[i]);
		}
		
	}
}