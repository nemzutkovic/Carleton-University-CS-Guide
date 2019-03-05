import java.util.Arrays;

public class Board{
	private char BLANK = '_';
	private	char[][] board;
  	private int rows;
	private int columns;
	
	public Board(int boardlength){
		this(boardlength, boardlength);					
	}

	public Board(int rows, int cols){
		// signature is Board:int, int
		board = new char[rows][cols];
		for(int i=0; i<board.length; i+=1){
			for(int j=0; j<board[0].length; j+=1){
				board[i][j] = BLANK;
			}
		}
		
		this.rows = rows;
		this.columns = cols;
		
		System.out.println("Created " + rows + "x" + cols + " Game:");
	}
	
	public String display(){
		return Arrays.deepToString(board) ;
	}
	
	public String play(int row, int col, char symbol){
		while(board[row][col] != BLANK){
			System.out.println("Invalid move. Please try again.");
			Player.userInput();
			row = Player.rowmove;
			col = Player.columnmove;
		}
		board[row][col] = symbol;
		return display();
	}

	// Checks win condition for 3X3 grid. Ran out of time to figure out double for loop algorithm.	
	public boolean win3x3(){
		// checks first row
		if(board[0][0] != BLANK && board[0][0] == board[0][1] && board[0][1] == board[0][2]){
			return true;
		}
		// checks second row
		if(board[1][0] != BLANK && board[1][0] == board[1][1] && board[1][1] == board[1][2]){
			return true;
		}		
		// checks third row
		if(board[2][0] != BLANK && board[2][0] == board[2][1] && board[2][1] == board[2][2]){
			return true;
		}
		// checks first column
		if(board[0][0] != BLANK && board[0][0] == board[1][0] && board[1][0] == board[2][0]){
			return true;
		}
		// checks second column
		if(board[0][1] != BLANK && board[0][1] == board[1][1] && board[1][1] == board[2][1]){
			return true;
		}
		// checks third column
		if(board[0][2] != BLANK && board[0][2] == board[1][2] && board[1][2] == board[2][2]){
			return true;
		}
		// checks left to right diagonal
		if(board[0][0] != BLANK && board[0][0] == board[1][1] && board[1][1] == board[2][2]){
			return true;
		}
		// checks right to left diagonal
		if(board[0][2] != BLANK && board[0][2] == board[1][1] && board[1][1] == board[2][0]){
			return true;
		}		
		return false;
	}

	// Checks win condition for 5X5 grid. Ran out of time to figure out double for loop algorithm.
	public boolean win5x5(){
		// checks first row
		if(board[0][0] != BLANK && board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][2] == board[0][3] && board[0][3] == board[0][4]){
			return true;
		}
		// checks second row
		if(board[1][0] != BLANK && board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][2] == board[1][3] && board[1][3] == board[1][4]){
			return true;
		}
		// checks third row
		if(board[2][0] != BLANK && board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][2] == board[2][3] && board[2][3] == board[2][4]){
			return true;
		}
		// checks fourth row
		if(board[3][0] != BLANK && board[3][0] == board[3][1] && board[3][1] == board[3][2] && board[3][2] == board[3][3] && board[3][3] == board[3][4]){
			return true;
		}
		// checks fifth row
		if(board[4][0] != BLANK && board[4][0] == board[4][1] && board[4][1] == board[4][2] && board[4][2] == board[4][3] && board[4][3] == board[4][4]){
			return true;
		}
		// checks first column
		if(board[0][0] != BLANK && board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[2][0] == board[3][0] && board[3][0] == board [4][0]){
			return true;
		}
		// checks second column
		if(board[0][1] != BLANK && board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[2][1] == board[3][1] && board[3][1] == board [4][1]){
			return true;
		}
		// checks third column
		if(board[0][2] != BLANK && board[0][2] == board[1][2] && board[1][2] == board[2][2] && board[2][2] == board[3][2] && board[3][2] == board [4][2]){
			return true;
		}
		// checks fourth column
		if(board[0][3] != BLANK && board[0][3] == board[1][3] && board[1][3] == board[2][3] && board[2][3] == board[3][3] && board[3][3] == board [4][3]){
			return true;
		}
		// checks fifth column
		if(board[0][4] != BLANK && board[0][4] == board[1][4] && board[1][4] == board[2][4] && board[2][4] == board[3][4] && board[3][4] == board [4][4]){
			return true;
		}
		// checks left to right diagonal
		if(board[0][0] != BLANK && board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[2][2] == board[3][3] && board[3][3] == board [4][4]){
			return true;
		}
		// checks right to left diagonal
		if(board[0][4] != BLANK && board[0][4] == board[1][3] && board[1][3] == board[2][2] && board[2][2] == board[3][1] && board[3][1] == board [4][0]){
			return true;
		}
		return false;
	}

		public boolean checkTie(){
			for(int row = 0; row < board.length; row += 1){
				for (int column = 0; column < board.length; column += 1){
					if(board[row][column] == BLANK){
						return false;
					}
				}
			}
			return true;
		}
}