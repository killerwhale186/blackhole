package org.galaxy.game;

import java.util.Scanner;

public class MirrorGame {
	
	// initially we use the hidden values in board, so that we know not to display them
	// after a mirror is guessed correctly, we change it to the real value so that it
	// will be displayed properly
	// for the shooting logic, hidden or not behave the same
	public static final char FORWARD_MIRROR_HIDDEN = 'F';
	public static final char BACKWARD_MIRROR_HIDDEN = 'B';
	public static final char FORWARD_MIRROR = '/';
	public static final char BACKWARD_MIRROR = '\\';
	
	public static final int TOTAL_MIRRORS = 10;
	
	//using these values, so that we can get new coordinates by simply adding these to the old coordinates
	public static final int[] EAST = new int[]{1, 0};
	public static final int[] NORTH = new int[]{0, -1};
	public static final int[] WEST = new int[]{-1, 0};
	public static final int[] SOUTH = new int[]{0, 1};
	
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);

		int stillplaying = 1, choice, shoots = 0, correctguesses = 0, incorrectguesses = 0;

		char[][] board = makeBoard();
		
		//need to display?
		display(board);

		while (stillplaying == 1) {

			System.out.print("\n\nChoose:\n" + "(1) Shoot a Laser\n" + "(2) Guess at a mirror location\n"
					+ "(0) Quit the game\n");
			System.out.print("\nEnter choice: ");
			choice = cin.nextInt();
			System.out.print("\n");

			switch (choice) {
			case 1:
				shoots++;
				shoot(board);
				break;
			case 2:
				boolean guessResult = guess(board);
				if (guessResult) {
					correctguesses++;
				} else {
					incorrectguesses++;
				}
				if (correctguesses == TOTAL_MIRRORS) {
					System.out.println("You have found all the mirrors!");
				}
				break;
			case 0:
				stillplaying = 0;
				break;
			}
		}

		cin.close();
		System.out.println("You made " + shoots + " shots, " + correctguesses + " correct guesses and " + incorrectguesses + " incorrect guesses.");
	}
	
	private static char[][] makeBoard() {

		char[][] board = new char[10][10];

		//initialize to dots
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = '.';
			}
		}
		
		//put mirrors in board randomly
		int mirrorCount = 0;
		//use count and while loop to handle the case where a mirror position is generated more than once
		while (mirrorCount < TOTAL_MIRRORS) { 
			int xpos = (int) (Math.random() * 10);
			int ypos = (int) (Math.random() * 10);
			if (board[xpos][ypos] == '.') { //if no mirror there yet, put one
				if (Math.random() < .5) {
					board[xpos][ypos] = FORWARD_MIRROR_HIDDEN;
				} else {
					board[xpos][ypos] = BACKWARD_MIRROR_HIDDEN;
				}
				mirrorCount++;
			}
		}
		
		return board;
	}


	// returns char to display, when a mirror has not been found, we should not display it.
	private static char getDisplayChar(char c) {
		if (c == FORWARD_MIRROR_HIDDEN || c == BACKWARD_MIRROR_HIDDEN) {
			return '.';
		} else {
			return c;
		}
	}
	
	private static void display(char[][] board) {

		System.out.print("  ");
		for (int i = 20; i < 30; i++) {
			System.out.print(" " + i);
		}

		for (int j = 19; j > 9; j--) {
			System.out.print("\n" + j);
			for (int col = 0; col < 10; col++) {
				//hide mirror if not found yet
				char c = getDisplayChar(board[col][19-j]);
				System.out.print(" " + c + " ");
			}
			System.out.print(j + 11 + (2 * (19 - j)));
		}

		System.out.print("\n" + " ");
		for (int k = 0; k < 10; k++) {
			System.out.print("  " + k);
		}
		System.out.print("\n");
	}

	private static void shoot(char[][] board) { 

		Scanner cin = new Scanner(System.in);
		int entry, laserxpos = 0, laserypos = 0;
		int[] direction;

		System.out.print("Enter an entry point from 0 to 39\n");
		System.out.print("Point of entry: ");
		entry = cin.nextInt();

		while (entry < 0 || entry > 39) {
			System.out.print("[Entry invalid] Re enter entry point: ");
			entry = cin.nextInt();
		}

		//prepare the initial coordinates and direction
		if (entry >= 0 && entry <= 9) {
			laserxpos = entry;
			laserypos = 9;
			direction = NORTH;
		} else if (entry >= 10 && entry <= 19) {
			laserxpos = 0;
			laserypos = 19 - entry;
			direction = EAST;
		} else if (entry >= 20 && entry <= 29) {
			laserxpos = entry - 20;
			laserypos = 0;
			direction = SOUTH;
		} else {
			laserxpos = 9;
			laserypos = entry - 30;
			direction = WEST;
		}
		
		System.out.print("\n");
		moveNext(board, laserxpos, laserypos, direction);
		
		cin.close();
	}

	//figure out what's the new direction of the laser
	//input: board, position (x,y), old direction
	//output: new direction
	private static int[] getNewDirection(char[][] board, int laserxpos, int laserypos, int[] oldDirection) {

		//default to same direction
		int[] newDirection = oldDirection;

		char c = board[laserxpos][laserypos];

		//check if it's a mirror, if so, adjust the direction
		if (c == FORWARD_MIRROR || c == FORWARD_MIRROR_HIDDEN) {
			if (oldDirection == EAST) {
				newDirection = NORTH;
			} else if (oldDirection == NORTH) {
				newDirection = EAST;
			} else if (oldDirection == WEST) {
				newDirection = SOUTH;
			} else if (oldDirection == SOUTH) {
				newDirection = WEST;
			}
		}
		else if (c == BACKWARD_MIRROR || c == BACKWARD_MIRROR_HIDDEN) {
			if (oldDirection == EAST) {
				newDirection = SOUTH;
			} else if (oldDirection == NORTH) {
				newDirection = WEST;
			} else if (oldDirection == WEST) {
				newDirection = NORTH;
			} else if (oldDirection == SOUTH) {
				newDirection = EAST;
			}
		}
		
		return newDirection;
	}
	
	//move one block
	private static void moveNext(char[][]board, int laserxpos, int laserypos, int[] direction) {

		//move one block
		int[] newDirection = getNewDirection(board, laserxpos, laserypos, direction);
		int newxpos = laserxpos + newDirection[0];
		int newypos = laserypos + newDirection[1];
		
		//check if exiting
		if (newxpos < 0 || newxpos > 9 || newypos < 0 || newypos > 9) {
			int exit;
			if (newxpos < 0) {
				exit = 19 - newypos;
			} else if (newxpos > 9) {
				exit = newypos + 30;
			} else if (newypos < 0) {
				exit = newxpos + 20;
			} else { // must be (newypos > 9)
				exit = newxpos;
			}
			System.out.print("Laser exits at: " + exit + "\n\n");
			return;
		}
		
		//not exiting, keep moving
		moveNext(board, newxpos, newypos, newDirection);
	}

	// ask use to guess a mirror location, return true if guessed correctly, false otherwise
	private static boolean guess(char[][] board) {
		Scanner cin = new Scanner(System.in);
		int guessX, guessY;
		boolean found = false;

		System.out.print("Enter an x and y value to guess a mirror location: \n");
		System.out.print("X [0 - 9]: ");
		guessX = cin.nextInt();

		while (guessX < 0 || guessX > 9) {
			System.out.print("[Entry invalid] Re enter X value: ");
			guessX = cin.nextInt();
		}

		System.out.print("Y [10 - 19]: ");
		guessY = cin.nextInt();

		while (guessY < 10 || guessY > 19) { // x inputs are top and bottom #'s,
												// and y inputs are side #'s
			System.out.print("[Entry invalid] Re enter Y value: ");
			guessY = cin.nextInt();
		}

		if (board[guessX][19 - guessY] == FORWARD_MIRROR_HIDDEN) {
			board[guessX][19 - guessY] = FORWARD_MIRROR;
			found = true;
		} else if (board[guessX][19 - guessY] == BACKWARD_MIRROR_HIDDEN) {
			board[guessX][19 - guessY] = BACKWARD_MIRROR;
			found = true;
		}

		if (found == true) {
			System.out.print("Mirror successfully found at: (" + guessX + "," + guessY + ")\n\n");
			display(board);
		} else {
			System.out.print("No mirror found at: (" + guessX + "," + guessY + ")\n\n");
			//need to display if guessed wrong??
			//display(board);
		}
		
		cin.close();
		return found;
	}
}
