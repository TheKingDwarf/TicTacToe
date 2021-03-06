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
	final String[] tokens = {" X ", " O "}; //make a 2d array with our two different tokens, X and O (final so it can't get modified)
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
	//board
	public void initBoard() {
		
		for (int i = 0; i < 3; i++) 
			for (int j = 0; j < 3; j++) {
				setBoard(i , j, "   ");
			}
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
	}//end getters and setters
	
	//methods
	
	// print displays the board
	// returns the board
	public static void print(String[][] m) {
		System.out.println("\n-------------");
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				System.out.print("|" + m[i][j]);
			}
			System.out.println("|\n-------------");
		}
	}//end print
	
	//runs all the appropriate methods at the correct time
	//returns the order of the game
	public void gameLoop() {
				
		//print starting messages
		System.out.print("\nWelcome to Logan's and Travis' TicTacToe game! \n");
		initBoard();

		do {
			if (isPlayerTurn()) { //player turn code
				print(getBoard());//prints the board
				playerTurn(getBoard(), tokens[0]);	//player's turn		
				setWin(checkWin(getBoard(), tokens[0]));//check to see if player won - if so, stop the game
			} else { //pc turn code
				computerTurn(getBoard());		
				setWin(checkWin(getBoard(), tokens[1]));//check to see if computer won - if so, stop the game
			}	
			//check for a win
			setPlayerTurn(isPlayerTurn() ^ true);					
		} while (isWin() == false && checkDraw(getBoard()) == false); //check for win or draw
		//print ending messages
		displayWin(); //calls the display method				
	}//end game loop
	//display win or draw
	//returns whether the player or the computer wins or if it's a draw
	public void displayWin() {
		// Display board
		print(getBoard());
		// Display game results
		System.out.print("\nThe game is over!\n");
		if (checkWin(getBoard(), tokens[0]))
			System.out.println("\n" + tokens[0] + "You won");
		else if (checkWin(getBoard(), tokens[1])) {
			System.out.println("\n" + tokens[1] +"Computer wins!");
		}
		else
			System.out.println("It's a draw");
	}//end display win
	//check draw
	//returns draw
	public boolean checkDraw(String[][] board) {
		
		if (checkWin(getBoard(), tokens[0]) == false) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (board[i][j] == "   ")
							return false;
					}
				}
				return true;		
		}
		return false;
	}//end draw
	
	//check win functions horizontally 
	//if there are 3 of the player's tokens across - it's a win for the player
	public boolean checkHorizontalWin(String[][] board, String token) {
		for (int i= 0; i < 3; i++) {//loop through columns
			int count = 0; //set the count of the token to 0
			for (int j = 0; j < 3; j++) { //loop through rows
				if (board[i][j] == token) //if the string in current position equals the input token
					count++; //add to the count of tokens
			}
			if (count == 3) //check for count inside of the i loop (important that we check here so that the tokens have to be in the same row)
				return true;
		}
		return false;
	}//end check horizontal
	//check win functions vertically 
	//if there are 3 of the player's tokens vertically - it's a win for the player
	public boolean checkVerticalWin(String[][] board, String token) {
		for (int i= 0; i < 3; i++) {
			int count = 0; //set the count of the token to 0
			for (int j = 0; j < 3; j++) { 
				if (board[j][i] == token) //if the string in current position equals the input token (i and j flipped here so that we check the vertical)(
					count++; //add to the count of tokens
			}
			if (count == 3) //check for count inside of the i loop (important that we check here so that the tokens have to be in the same row)
				return true;
		}
		return false; //if none of the above were true, return false
	}//end check vertical
	//check win functions diagonally
	//if there are 3 of the computer's tokens diagonally - it's a win for the computer
	public boolean checkDiagonalWin(String[][] board, String token) {
		//covers left to right diagonal
		int count = 0;
		for (int i = 0; i < 3; i++) {//only need one loop here 
			if (board[i][i] == token) {
				count++;
				}
			if (count == 3) {
				return true;
			}
		}
		count = 0;
        for (int i = 0, j = 2; j >= 0 ; j--, i++) {
            if (board[i][j] == token)
                count++;
            if (count == 3)
                return true;
        }
		return false;
	}//end check diagonal 
	//if the game has been won by any of the 3 checks, stop running
	//returns win
	public boolean checkWin(String[][] board, String token) {
		return checkHorizontalWin(board,token) || checkVerticalWin(board,token) || checkDiagonalWin(board, token);
	}//end check win
	
	//human's turn
	public void playerTurn(String[][] y, String token) {
		int[] cell = new int[2]; // Cell row and column

		// Prompt player to enter an int 0-2 (1-3...x axis, then y axis)
		do {
			System.out.print("Enter a row(0, 1, or 2)" + token + ": ");
			cell[0] = input.nextInt();
			System.out.print("Enter a column(0, 1, or 2)" + token + ": ");
			cell[1] = input.nextInt();

		} while (!checkCell(cell[0], cell[1], getBoard()));
		setBoard(cell[0],cell[1],tokens[0]);
	}//end human's turn
	//computer's turn
	//runs all the code for the AI's turn
	public void computerTurn(String[][] board) {
		ComputerAI MCP = new ComputerAI(board, 0); //make new instance of the computer ai
		MCP.board = getBoard();
		int[] cell = MCP.computerMove(); //set a new array, cell, equal to the result of the computer move
		setBoard(cell[0],cell[1],tokens[1]);
		System.out.println("The MCP moved to: " + cell[0] + " " + cell[1]);//prints the coords of the computer's choice
	}//end computer turn
	
	//checks cells to see if the input is within range of the board and to see if the player entered the right number 0-2.
	//returns messages if the picked spot is out of bounds or has already been chosen 
	public boolean checkCell(int x, int y, String[][] board) {
		if (x >= 0 && x <= 2 && y >= 0 && y <= 2) {
			String[][] temp = board;
			if (temp[x][y] == "   ") {
				return true;
			}
			else {
				System.out.println("\nPick another spot\n"); //if spot is taken
				return false;
			}
		}
			
		else {
			System.out.println("\nKeep x and y within range\n");//if out of range 0-2
			return false;
		}
	}// end check cell
		
	//main method
	public static void main(String[] args) {
		
		TicTacToe run = new TicTacToe(); //makes a new instance of the class
		run.gameLoop();//calls the game loop method

	}//end main method

} //end class
