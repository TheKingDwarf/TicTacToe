
/*
 * 
 * Logan Forman and Travis Dutton
 * started:	 9/20/2018
 * finished:
 * Simple computer AI 
 * We pledge that this program represents our own code. We received help from no-one with coding this project
 * 
 */
import java.util.Random;

public class ComputerAI {
	//vars
	String[][] board = new String[2][2];
	private int difficulty = 0;
	private int[] cell = new int[2];
	
	//getters and setters
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	public int getCell(int position) {
		return cell[position];
	}
	public int[] getCellMaster() {
		return cell;
	}
	public void setCell(int[] cell) {
		this.cell = cell;
	}
	//constructors
	ComputerAI(String[][] board) {
		this.board = board;
	}
	ComputerAI(String[][] board, int difficulty) {
		this.board = board;
		this.difficulty = difficulty;
	}
	
	//methods
	public int[] computerMove() {
		//make an object of TicTacToe

		switch (getDifficulty()) {
			case 0: //easy difficulty, computer makes random moves
				setCell(findRandCell());//generate a random cell

				break;
			case 1: // medium difficulty, computer tries to block and also win, but otherwise makes random moves
				setCell(findGoodCell());
				break;
		
		}
		return getCellMaster(); //set the board in TicTacToe
	}
	public int[] findGoodCell() {
		int[][] emptyCells = findEmptyCells();
		int emptyAmount = emptyCells.length; //amount of empty cells
	
		int[] cell = new int[2];
		cell[0] = -1;
		cell[1] = -1;
		
		for (int i = 0; i < emptyAmount; i++) {
			String[][] tempBoard = new String[3][3];
			System.arraycopy(board, 0, tempBoard, 0, board.length);;
			tempBoard[emptyCells[i][0]][emptyCells[i][1]] = " O "; //make a temporary board, with one of the possible cells filled in
			
			if(checkWinAI(tempBoard, " O ")) { //check win on our imaginary board
				
				cell =  emptyCells[i]; //if there is a win, return the current cell
			}
			
			//if we can't win with one move, check to see if the player can, and block them
			
			tempBoard[emptyCells[i][0]][emptyCells[i][1]] = " X "; //make a temporary board, with one of the possible cells filled in
																   //but this time with it marked as the players token
			if(checkWinAI(tempBoard, " X ")) { //check if the player would have a win
				cell =  emptyCells[i]; //if the player would win, return the current cell so we block them instead
			}
		
		}
		

		//if we don't have a good move, do a random one
		cell = findRandCell();
		System.out.println(cell[0] + " " + cell[1]);
		return cell;

		
	}
	//check win functions
	public boolean checkHorizontalWinAI(String[][] board, String token) {
		for (int i= 0; i < board.length - 1; i++) {//loop through columns
			int count = 0; //set the count of the token to 0
			for (int j = 0; j < board[i].length - 1; j++) { //loop through rows
				if (board[i][j] == token) //if the string in current position equals the input token
					count++; //add to the count of tokens
			}
			if (count == 3) //check for count inside of the i loop (important that we check here so that the tokens have to be in the same row)
				return true;
		}
		
		return false;
	}//checks vertical win
	public boolean checkVerticalWinAI(String[][] board, String token) {
		for (int i= 0; i < board.length - 1; i++) {
			int count = 0; //set the count of the token to 0
			for (int j = 0; j < board[i].length - 1; j++) { 
				if (board[j][i] == token) //if the string in current position equals the input token (i and j flipped here so that we check the vertical)(
					count++; //add to the count of tokens
			}
			if (count == 3) //check for count inside of the i loop (important that we check here so that the tokens have to be in the same row)
				return true;
		}
		
		return false; //if none of the above were true, return false
	}//checks diagonal win
	public boolean checkDiagonalWinAI(String[][] board, String token) {
		//covers left to right diagonal
		int count = 0;
		for (int i = 0; i < board.length - 1; i++) {//only need one loop here 
			if (board[i][i] == token) {
				count++;
			}
			if (count == 3) {
				return true;
			}
		}
		int j = 0; //init j at 0
		count = 0; //reset count
		for (int i = board.length - 1; i > 0; i--) {//this loop is reversed, check right to left
			if (board[i][j] == token) {
				count++;
			}
			
			if (count == 3) 
				return true;
			j++; //add to j while subtracting from i, this gives us the diagonal movement
		}
		
		
		return false;
	}
	//if the game has been won by any of the 3 checks, stop running??
	public boolean checkWinAI(String[][] board, String token) {
		return checkHorizontalWinAI(board,token) || checkVerticalWinAI(board,token) || checkDiagonalWinAI(board, token);
	}
	public int[][] findEmptyCells() {
		
		int count = 0;
		for (int i = 0; i < 3; i++) { //find amount of empty cells
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == "   ")
					count++;
				
			}
		}

		int[][] emptyCells = new int[count][2]; //populate 2d array with empty cell positions
		for (int i = 0; i < 3; i++) { //add the positions of empty cells to array
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == "   ") {
					int[] cell = { i, j};
					count--;
				
					emptyCells[count] = cell;
					
				}
			}
		}

		return emptyCells;
	}
	
	public int[] findRandCell() {
		Random rand = new Random();

		int i = 1;
		int j = 1;
		int[] cell = new int[2];
		cell[0] = -1;
		cell[1] = -1;
		do {
			i = rand.nextInt(3);
			j = rand.nextInt(3);
			if(checkCell(i, j, board) == true) {
				cell[0] = i;
				cell[1] = j;
				board[i][j] = " O ";
			} 
			
		} while (cell[0] == -1);
		
		
		return cell;
	}
	
	
	public boolean checkCell(int x, int y, String[][] board) {
		if (x >= 0 && x <= 2 && y >= 0 && y <= 2) {
			String[][] temp = board;
			if (temp[x][y] == "   ") {
				return true;
			}

		}
		return false;
	}// end check cell

}
