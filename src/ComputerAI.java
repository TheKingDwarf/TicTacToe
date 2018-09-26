
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
				

				break;
			case 2: // hard difficulty, same as medium but computer seeks out corners and the middle
				

				break;
				
		
		}
		return getCellMaster(); //set the board in TicTacToe
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
	//the following methods check to see if there are 2 tokens in a row, if there are, it tries to place a cell between them
	public int[] checkHorizontal(String[][] board) {
		int[] emptyCell = new int[2];
		for (int i= 0; i < board.length - 1; i++) {//loop through columns
			int count = 0; //set the count of the token to 0
			for (int j = 0; j < board[i].length - 1; j++) { //loop through rows
				if (board[i][j] == "   ") {//if the string in current position equals the input token
					count++; //add to the count of tokens
					emptyCell[0] = i;
					emptyCell[1] = j;
				}
			}
			if (count == 2) //check for count inside of the i loop (important that we check here so that the tokens have to be in the same row)
				return null; //there are not two cells that are full
		}
		
		return emptyCell;
	}
	public int[] checkVertical(String[][] board, String token) {
		int[] emptyCell = new int[2];
		for (int i= 0; i < board.length - 1; i++) {//loop through columns
			int count = 0; //set the count of the token to 0
			for (int j = 0; j < board[i].length - 1; j++) { //loop through rows
				if (board[j][i] == "   ") {//if the string in current position equals the input token
					count++; //add to the count of tokens
					emptyCell[0] = j;
					emptyCell[1] = i;
				}
			}
			if (count == 2) //check for count inside of the i loop (important that we check here so that the tokens have to be in the same row)
				return null; //there are not two cells that are full
		}
		
		return emptyCell;
	}
	public int[] checkDiagonal(String[][] board, String token) {
		//covers left to right diagonal
		int[] emptyCell = new int[2];
		int count = 0;
		for (int i = 0; i < board.length - 1; i++) {//only need one loop here 
			if (board[i][i] == "   ") {
				count++;
				emptyCell[0] = i;
				emptyCell[1] = i;
			}
		}
		if (count < 2) {
			return emptyCell;
		}
		int j = 0; //init j at 0
		count = 0; //reset count
		for (int i = board.length - 1; i > 0; i--) {//this loop is reversed, check right to left
			if (board[i][j] == token) {
				count++;
				emptyCell[0] = i;
				emptyCell[1] = j;
			}
			
			if (count == 2) 
				return null;
			j++; //add to j while subtracting from i, this gives us the diagonal movement
		}
		
		
		return emptyCell;
}
}
