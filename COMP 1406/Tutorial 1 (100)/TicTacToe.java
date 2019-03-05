import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe{
	public static char EX = 'X';
	public static char OH = 'O';
	public static char BLANK = '_';
	public static char[] PLAYERS = {EX, OH};
	public static int rows = 3;
	public static int cols = 3;
	
	public static void printBoard(char[][] grid){
	  for(int r=0; r<rows; r+=1){
		for(int c=0; c<cols; c+=1){
			System.out.print("|" + grid[r][c] + "|");
		}
			System.out.println(); 
		}
	}
	
	public static boolean win(char[][] grid, int r, int c){
		// checks vertically
		if(grid[0][c] == grid[1][c] && grid[0][c] == grid[2][c]){
			return true;
		}
		// checks horizontally
		if(grid[r][0] == grid[r][1] && grid[r][0] == grid[r][2]){
			return true;
		}
		// checks both diagonal directions 
		if(grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2] && grid[1][1] != '_'){
			return true;
		}
		if(grid[0][2] == grid[1][1] && grid[0][2] == grid[2][0] && grid[1][1] != '_'){
			return true;
		}else{
		return false;
		 }
	}
	
	public static void main(String[] args){

		boolean playing = true;
		int row, col;
		
		Scanner keyboard = new Scanner(System.in);
		
		int player = 0;
		char symbol = PLAYERS[ player % 2];
		
		char[][] board = new char[rows][cols];		
		for(int r=0; r<rows; r+=1){
			for(int c=0; c<cols; c+=1){
				board[r][c] = BLANK;
			}
		}
  
		while(player < rows*cols){
			printBoard(board);
				
		while(true){
			System.out.print("Player " + symbol + " enter a move [r,c]: ");
			String input = keyboard.next();
				
			row = Integer.parseInt(input.split(",")[0]);
			col = Integer.parseInt(input.split(",")[1]);
				
			if(row >= 0 && row < rows && col >= 0 && col < cols && board[row][col] != BLANK){
					System.out.println("That position is already taken. Try again.");
				}else{
					break;
				}
			}
		
		board[row][col] = symbol;
		
		if (win(board, row, col)){
			playing = true;
			printBoard(board);
			System.out.println("Game Over! Player " + symbol + " wins!");
			return;	
		}
		
		player = player + 1;
		symbol = PLAYERS[player % 2];
		
		}
		printBoard(board);
	}
}