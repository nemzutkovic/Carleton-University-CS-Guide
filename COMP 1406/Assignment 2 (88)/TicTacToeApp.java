import java.util.Scanner;

public class TicTacToeApp{

	public static void main(String[] args){
		System.out.print("Welcome to Tic Tac Toe! Please enter the grid parameters: ");
		
		// Creates a scanner object to take in input from the user.
		Scanner keyboard = new Scanner(System.in);
		String input = keyboard.next();

		// Plays Tic Tac Toe on a 3X3 Grid.
		if(input.equals("three") || input.equals("3")){
			int boardlength = 3;
			Board ttt = new Board(boardlength);
			System.out.println(ttt.display());
			while(!ttt.win3x3() == true){
				Player.changePlayer();
				Player.userInput();			
				while(Player.rowmove > boardlength - 1 || Player.columnmove > boardlength - 1){
					System.out.println("Invalid move. Please try again.");
					Player.userInput();
				}
				System.out.println(ttt.play(Player.rowmove,Player.columnmove,Player.currentPlayer));
				if(ttt.checkTie() == true){
					System.out.println("The game is a tie!");
					System.exit(0);
				}
			}
			System.out.println("Congratulations! Player " + Character.toString(Player.currentPlayer) + " wins!");
		}

		// Plays Tic Tac Toe on a 5X5 Grid.
		else if(input.equals("five") || input.equals("5")){
			int boardlength = 5;
			Board ttt = new Board(boardlength);
			System.out.println(ttt.display());
			
			// Checks if the game has been won.
			while(!ttt.win5x5() == true){
				Player.changePlayer();
				Player.userInput();
				while(Player.rowmove > boardlength - 1 || Player.columnmove > boardlength - 1){
					System.out.println("Invalid move. Please try again.");					
					Player.userInput();
				}
				System.out.println(ttt.play(Player.rowmove,Player.columnmove,Player.currentPlayer));
				
				// Checks if the game is a tie.
				if(ttt.checkTie() == true){
					System.out.println("The game is a tie!");
					System.exit(0);
				}
			}
			System.out.println("Congratulations! Player " + Character.toString(Player.currentPlayer) + " wins!");
		}

		/* If inproper input is provided, the program will terminate   
		   and instructions will be given to run Tic Tac Toe properly. */
		else{
			System.out.println("Incorrect input. Please enter 3, three, 5 or five as input to play Tic Tac Toe.");
			System.exit(0);
		}
	}
}