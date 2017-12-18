import java.util.Scanner;

public class MirrorGame {

	//don't have to use 1,2,3,4. As long as they are different, it should be fine.
	public static final int EAST = 1;
	public static final int NORTH = 2;
	public static final int WEST = 3;
	public static final int SOUTH = 4;
	
	public static void main(String[] args) {

		Scanner cin = new Scanner(System.in);

		int stillplaying = 1, choice, correctguesses = 0, incorrectguesses = 0;
		int mirrorCount = 2;
		int[] mirrorxpos = new int[mirrorCount], mirrorypos = new int[mirrorCount];
		char[] mirrortype = new char[mirrorCount];

		//make two mirrors as in the example
		mirrorxpos[0] = 1;
		mirrorypos[0] = 15;
		mirrorxpos[1] = 4;
		mirrorypos[1] = 15;
		mirrortype[0] = '\\';
		mirrortype[1] = '/';

		char[][] board = makeBoard(mirrorCount, mirrorxpos, mirrorypos, mirrortype);

		displayBoard(mirrorCount, mirrorxpos, mirrorypos, mirrortype);

		//test several cases
		//probably should do more tests and with more mirrors
		shoot(board, 1);
		shoot(board, 4);
		shoot(board, 6);
		shoot(board, 12);
		shoot(board, 15);
		shoot(board, 19);
		shoot(board, 21);
		shoot(board, 24);
		shoot(board, 28);
		shoot(board, 34);
		shoot(board, 37);
	}

	//make the board, index goes from left to right, top to down. I.e., top left is (0,0), and bottom right is (9,9)
	public static char[][] makeBoard(int mirrorCount, int[] mirrorxpos, int[] mirrorypos, char[] mirrortype) {
		char[][] board = new char[10][10]; 
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = '.';
				for (int m = 0; m < mirrorCount; m++) {
					if (mirrorxpos[m] == i && (19 - mirrorypos[m]) == j) {
						board[i][j] = mirrortype[m];
						break;
					}
				}
			}
		}
		
		return board;
	}

	public static void displayBoard(int mirrorCount, int[] mirrorxpos, int[] mirrorypos, char[] mirrortype) {
		
		char[][] board = makeBoard(mirrorCount, mirrorxpos, mirrorypos, mirrortype);

		System.out.print("  ");
		for (int i = 20; i < 30; i++) {
			System.out.print(" " + i);
		}

		for (int j = 19; j > 9; j--) {
			System.out.print("\n" + j);
			for (int i = 0; i < board[0].length; i++) {
				System.out.print(" " + board[i][19-j] + " ");
			}
			System.out.print(j + 11 + (2 * (19 - j)));
		}

		System.out.print("\n" + " ");
		for (int k = 0; k < 10; k++) {
			System.out.print("  " + k);
		}
		System.out.print("\n");
	}

	//given input number, return x,y cordinates and direction 
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
	
	//given input number, return output number
	public static int shoot(char[][] board, int inputNumber) {
		
		int[] input = getCoordinatesFromNumber(inputNumber);

		int[] end = moveNext(board, input);
		
		int exitNumber = getExitNumber(end);
		
		System.out.println(inputNumber + " =====>>>>> " + exitNumber);
		
		return exitNumber;
	}
	
	//return exit number from x,y and direction. -1 means not exiting
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
	
	//moves from one position to the next until exits
	//input = [x, y, direction]
	public static int[] moveNext(char[][] board, int[] input) {
		
		int exitNum = getExitNumber(input);
		if (exitNum >= 0) {
			//existing, this will stop the recursive call
			return input;
		}

		int[] output = new int[3];

		int oldDirection = input[2];
		int newDirection = oldDirection; //may change this later

		//move one step
		switch (oldDirection) {
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

		//check if its a mirror, adjust direction if so
		char c = board[output[0]][output[1]];
		if (c == '\\') {
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
		} else if (c == '/') {
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
		output[2] = newDirection;
		
		//System.out.println(output[0] + " " + output[1] + " " + output[2]);
		return moveNext(board, output);
	}
}
