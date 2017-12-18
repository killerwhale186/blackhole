import java.util.Scanner;

public class MirrorGameV3 {

	// initially we use the hidden values in board, so that we know not to display them
	// after a mirror is guessed correctly, we change it to the real value so that it
	// will be displayed properly
	// for the shooting logic, hidden or not behave the same
	public static final char FORWARD_MIRROR_HIDDEN = 'F';
	public static final char BACKWARD_MIRROR_HIDDEN = 'B';
	public static final char FORWARD_MIRROR = '/';
	public static final char BACKWARD_MIRROR = '\\';

	// travel directions. don't have to use 1,2,3,4. As long as they are different, it should be fine.
	public static final int EAST = 1;
	public static final int NORTH = 2;
	public static final int WEST = 3;
	public static final int SOUTH = 4;

	public static void main(String[] args) {

		Scanner cin = new Scanner(System.in);

		boolean stillplaying = true;
		int choice, correctguesses = 0, incorrectguesses = 0, numberOfShots = 0;

		//TODO: change to 10, and change makeBoard method accordingly.
		int totalMirrors = 3;
		char[][] board = makeBoard(totalMirrors);

		displayBoard(board);

		while (stillplaying) {
			System.out.print("\n\nChoose:\n" + "(1) Shoot a Laser\n" + "(2) Guess at a mirror location\n"
					+ "(0) Quit the game\n");
			System.out.print("\nEnter choice: ");
			choice = cin.nextInt();
			System.out.print("\n");
			switch (choice) {
			case 1:
				System.out.println("Please enter a number: ");
				int inputNumber = cin.nextInt();
				numberOfShots++;
				int exitNumber = shoot(board, inputNumber);
				System.out.println("Exited at: " + exitNumber);
				break;
			case 2:
				System.out.println("Please enter two numbers: ");
				int a = cin.nextInt();
				int b = cin.nextInt();
				//TODO: make sure the two numbers are in valid range, and are one for x and one for y.
				boolean foundMirror = findMirror(board, a, b);
				if (foundMirror) {
					correctguesses++;
					if (correctguesses == totalMirrors) {
						System.out.println("Great. You found all the mirrors!");
						// should exit or not?
						// stillplaying = false;
					} else {
						System.out.println("Good job. You found a mirror!");
					}
					displayBoard(board);
				} else {
					incorrectguesses++;
					System.out.println("Good try, but there is no mirror there.");
				}
				break;
			case 0:
				stillplaying = false;
				break;
			}
		}

		cin.close();
		System.out.println("Thanks for playing the game.");
		System.out.println("You made " + numberOfShots + " shots, " + correctguesses + " correct guesses and " + incorrectguesses + " incorrect guesses.");
	}

	//check if user founds a mirror, if so, change the value to the real one, and return true.
	//otherwise, return false
	private static boolean findMirror(char[][] board, int a, int b) {
		int[] coord = getCoordinatesFromNumber(a, b);
		if (board[coord[0]][coord[1]] == FORWARD_MIRROR_HIDDEN) {
			board[coord[0]][coord[1]] = FORWARD_MIRROR;
			return true;
		} else if (board[coord[0]][coord[1]] == BACKWARD_MIRROR_HIDDEN) {
			board[coord[0]][coord[1]] = BACKWARD_MIRROR;
			return true;
		} else {
			return false;
		}
	}

	// converts two numbers into x,y for the board. For example, (5, 18) => (5, 1)
	// TODO: this needs some more work, to handle the cases where a and b are
	// reversed, or a and b are both for x (or y).
	private static int[] getCoordinatesFromNumber(int a, int b) {
		int x = 0, y = 0;
		if (a >= 0 && a <= 9) {
			x = a;
		} else if (a >= 20 && a <= 29) {
			x = a - 20;
		}
		if (b >= 10 && b <= 19) {
			y = 19 - b;
		} else if (b >= 30 && b <= 39) {
			y = b - 30;
		}
		int[] coord = new int[2];
		coord[0] = x;
		coord[1] = y;
		return coord;
	}

	// make the board, index goes from left to right, top to down. I.e., top
	// left is (0,0), and bottom right is (9,9)
	public static char[][] makeBoard(int totalMirrors) {

		int[] mirrorxpos = new int[totalMirrors], mirrorypos = new int[totalMirrors];
		char[] mirrortype = new char[totalMirrors];

		// TODO: use totalMirrors to generate mirrors
		// make two mirrors as in the example
		mirrorxpos[0] = 0;
		mirrorypos[0] = 15;
		mirrorxpos[1] = 0;
		mirrorypos[1] = 12;
		mirrorxpos[2] = 4;
		mirrorypos[2] = 12;
		mirrortype[0] = BACKWARD_MIRROR_HIDDEN;
		mirrortype[1] = BACKWARD_MIRROR_HIDDEN;
		mirrortype[2] = FORWARD_MIRROR_HIDDEN;

		char[][] board = new char[10][10];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = '.';
				for (int m = 0; m < totalMirrors; m++) {
					if (mirrorxpos[m] == i && (19 - mirrorypos[m]) == j) {
						board[i][j] = mirrortype[m];
						break;
					}
				}
			}
		}

		return board;
	}

	// returns char to display, when a mirror has not been found, we should not display it.
	public static char getDisplayChar(char c) {
		if (c == FORWARD_MIRROR_HIDDEN || c == BACKWARD_MIRROR_HIDDEN) {
			return '.';
		} else {
			return c;
		}
	}

	public static void displayBoard(char[][] board) {

		System.out.print("  ");
		for (int i = 20; i < 30; i++) {
			System.out.print(" " + i);
		}

		for (int j = 19; j > 9; j--) {
			System.out.print("\n" + j);
			for (int i = 0; i < board[0].length; i++) {
				System.out.print(" " + getDisplayChar(board[i][19 - j]) + " ");
			}
			System.out.print(j + 11 + (2 * (19 - j)));
		}

		System.out.print("\n" + " ");
		for (int k = 0; k < 10; k++) {
			System.out.print("  " + k);
		}
		System.out.print("\n");
	}

	// given input number, return (x, y, direction), e.g. 21 should return (1, 0, SOUTH)
	public static int[] getCoordinatesFromNumber(int inputNumber) {
		int[] output = new int[3];
		if (inputNumber >= 0 && inputNumber <= 9) {
			output[0] = inputNumber;
			output[1] = 9;
			output[2] = NORTH;
		} else if (inputNumber >= 10 && inputNumber <= 19) {
			output[0] = 0;
			output[1] = 19 - inputNumber;
			output[2] = EAST;
		} else if (inputNumber >= 20 && inputNumber <= 29) {
			output[0] = inputNumber - 20;
			output[1] = 0;
			output[2] = SOUTH;
		} else if (inputNumber >= 30 && inputNumber <= 39) {
			output[0] = 9;
			output[1] = inputNumber - 30;
			output[2] = WEST;
		}
		return output;
	}

	// given input number, return output number, e.g., 15 ==> 1 for two mirrors as in the example.
	public static int shoot(char[][] board, int inputNumber) {

		int[] input = getCoordinatesFromNumber(inputNumber);

		int[] end = moveNext(board, input);

		int exitNumber = getExitNumber(end);

		//System.out.println(inputNumber + " =====>>>>> " + exitNumber);

		return exitNumber;
	}

	// if we reached an edge and pointing out, then we are existing.
	// return exit number from x,y and direction. -1 means not exiting
	public static int getExitNumber(int[] input) {
		if (input[0] == 0 && input[2] == WEST) {
			return 19 - input[1];
		} else if (input[0] == 9 && input[2] == EAST) {
			return 30 + input[1];
		} else if (input[1] == 0 && input[2] == NORTH) {
			return 20 + input[0];
		} else if (input[1] == 9 && input[2] == SOUTH) {
			return input[0];
		} else {
			return -1;
		}
	}

	// moves from one position to the next until exits
	// input = [x, y, direction]
	// output = [x, y, direction]
	public static int[] moveNext(char[][] board, int[] input) {

		int oldDirection = input[2];
		int newDirection = oldDirection; // may change this later

		// check if its a mirror, adjust direction if so
		char c = board[input[0]][input[1]];
		if (c == BACKWARD_MIRROR || c == BACKWARD_MIRROR_HIDDEN) {
			switch (oldDirection) {
			case EAST:
				newDirection = SOUTH;
				break;
			case NORTH:
				newDirection = WEST;
				break;
			case WEST:
				newDirection = NORTH;
				break;
			case SOUTH:
				newDirection = EAST;
				break;
			}
		} else if (c == FORWARD_MIRROR || c == FORWARD_MIRROR_HIDDEN) {
			switch (oldDirection) {
			case EAST:
				newDirection = NORTH;
				break;
			case NORTH:
				newDirection = EAST;
				break;
			case WEST:
				newDirection = SOUTH;
				break;
			case SOUTH:
				newDirection = WEST;
				break;
			}
		}

		input[2] = newDirection;

		//check if we are at an edge and pointing outward, if so, then it should end
		int exitNum = getExitNumber(input);
		if (exitNum >= 0) {
			// exit, this will stop the recursive call
			return input;
		}

		int[] output = new int[3];
		output[2] = newDirection;

		// move one step according to the direction
		switch (newDirection) {
		case EAST:
			output[0] = input[0] + 1;
			output[1] = input[1];
			break;
		case NORTH:
			output[0] = input[0];
			output[1] = input[1] - 1;
			break;
		case WEST:
			output[0] = input[0] - 1;
			output[1] = input[1];
			break;
		case SOUTH:
			output[0] = input[0];
			output[1] = input[1] + 1;
			break;
		}

		// System.out.println(output[0] + " " + output[1] + " " + output[2]);
		return moveNext(board, output);
	}
	
	/*
	private static int[] numberToCoordinate(int number) {
		if (number >= 0 && number <= 9) {
			return new int[] {number, 9};
		} else if (number >= 10 && number <= 19) {
			return new int[] {0, 19 - number};
		} else if (number >= 20 && number <= 29) {
			return new int[] {number - 20, 0};
		} else if (number >= 30 && number <= 39) {
			return new int[] {9, number - 30};
		} else {
			//throw exception?
			return new int[] {-1, -1};
		}
	}
	*/
}
