import java.util.Scanner;

public class Player{

	public static char currentPlayer;
	public static int rowmove;
	public static int columnmove;

	// Helps the TicTacToeApp change from Player X to Player O when playing.
	public static void changePlayer(){
		if(currentPlayer == 'X'){
			currentPlayer = 'O';
		}else{
			currentPlayer = 'X';
		}
	}

	// Takes in the user input for the Board while playing.
	public static void userInput(){
		System.out.print ("Please enter a valid move: ");
		Scanner movedetector = new Scanner(System.in);
			
		String moveinput;
		String[] initialarray;
        moveinput = movedetector.nextLine();
        initialarray = moveinput.split("");

        String nospaces = "";
        for(int i = 0; i < initialarray.length; i += 1){
        	if(!initialarray[i].equals(" ")){
           		nospaces += initialarray[i];
        	}
        }

        String[] rowscolumns;
        rowscolumns = nospaces.split(",");

        rowmove = Integer.parseInt(rowscolumns[0]);
        columnmove = Integer.parseInt(rowscolumns[1]);
	}

}