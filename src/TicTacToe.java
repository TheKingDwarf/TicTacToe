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
	
	public
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
