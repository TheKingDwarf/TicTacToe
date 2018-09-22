/*
 * 
 * Logan Forman and Travis Dutton
 * started:	 9/20/2018
 * finished:
 * Simple tic tac toe game
 * We pledge that this program represents our own code. We received help from no-one with coding this project
 * 
 */

//import 
import java.util.Scanner;
//class TicTacToe
public class TicTacToe {
	//init variables
	private String[][] board =  new String[3][3]; //initialize a 2d array for our board, with 3 columns and rows
	private final String[] tokens = {" X ", " O "}; //make a 2d array with our two different tokens, X and O (final so it can't get modified)
	private boolean isWin = false; // boolean variable that says whether the game is over
	private boolean playerTurn = true; //boolean variable that says who's turn it is, true if it is player's turn, false if its pc's turn
	Scanner input = new Scanner(System.in); //initialize an input object
	
	//getters and setters
	public String[][] getBoard() {
		return board;
	}
	//setBoard modified from default setter function
	//takes X coordinate to modify, Y coordinate to modify and what token to put in that section
	public void setBoard(int xCoord, int yCoord, String token) { 
		this.board[xCoord][yCoord] = token;
	}
	
	public void initBoard() {
		
		for (int i = 0; i < 3; i++) 
			for (int j = 0; j < 3; j++) {
				setBoard(i , j, "   ");
			}
	}
	public String[] getTokens() {
		return tokens;
	}
	// no setter because tokens is final

	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}

	public boolean isPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(boolean playerTurn) {
		this.playerTurn = playerTurn;
	}
	
	
	//methods
	
	/** print displays the board */
	public static void print(String[][] m) {
		System.out.println("\n-------------");
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				System.out.print("|" + m[i][j]);
			}
			System.out.println("|\n-------------");
		}
	}//end print
	
	//Game loop
	public void gameLoop() {
		
		
		//print starting messages
		initBoard();
		
		do {
			if (isPlayerTurn()) { //player turn code
				print(getBoard());
			
			} else { //pc turn code
				print(getBoard());
				
			}
			
			//check for a win
			
			setPlayerTurn(isPlayerTurn() ^ true);
			
			
		} while (isWin() == false);
		
		//print ending messages
		
	}//end game loop
	
	public boolean checkHorizontalWin(String[][] board, String token) {
		for (int i= 0; i < board.length; i++) {//loop through columns
			int count = 0; //set the count of the token to 0
			for (int j = 0; j < board[i].length; j++) { //loop through rows
				if (board[i][j] == token) //if the string in current position equals the input token
					count++; //add to the count of tokens
			}
			if (count == 3) //check for count inside of the i loop (important that we check here so that the tokens have to be in the same row)
				return true;
		}
		
		return false;
	}
	public boolean checkVerticalWin(String[][] board, String token) {
		for (int i= 0; i < board.length; i++) {
			int count = 0; //set the count of the token to 0
			for (int j = 0; j < board[i].length; j++) { 
				if (board[j][i] == token) //if the string in current position equals the input token (i and j flipped here so that we check the vertical)(
					count++; //add to the count of tokens
			}
			if (count == 3) //check for count inside of the i loop (important that we check here so that the tokens have to be in the same row)
				return true;
		}
		
		return false; //if none of the above were true, return false
	}
	public boolean checkDiagonalWin(String[][] board, String token) {
		//covers left to right diagonal
		int count = 0;
		for (int i = 0; i < board.length; i++) {//only need one loop here 
			if (board[i][i] == token) {
				count++;
			}
			if (count == 3) {
				return true;
			}
		}
		int j = 0; //init j at 0
		for (int i = board.length; i > 0; i--) {//this loop is reversed, check right to left
			if (board[i][j] == token) {
				count++;
			}
			
			if (count == 3) 
				return true;
			j++; //add to j while subtracting from i, this gives us the diagonal movement
		}
		
		
		return false;
	}
	
	public boolean checkWin(String[][] board, String token) {
		return checkHorizontalWin(board,token) || checkVerticalWin(board,token) || checkDiagonalWin(board, token);
	}
	//checks cells to see if the input is within range of the board and to see if the player entered the right number 0-2.
		public boolean checkCell(int x, int y) {
			if (x < 0 && x > 2 && y < 0 && y > 2) {
				String[][] temp = getBoard();
				if (temp[x][y] == "  ") {
					return true;
				}
				else 
					System.out.print("Pick another spot");
					return false;
			}
			else 
				System.out.print("Keep x and y within range");
				return false;
		}// end check cell
		
	//main method
	public static void main(String[] args) {
		
		TicTacToe run = new TicTacToe();
		run.gameLoop();

	}//end main method

} //end class
