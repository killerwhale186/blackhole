

import java.util.Scanner;

public class MirrorGameV4 {
	
	//using these values, so that we can get new coordinates by simply adding these to the old coordinates
	public static final int[] EAST = new int[]{1, 0};
	public static final int[] NORTH = new int[]{0, -1};
	public static final int[] WEST = new int[]{-1, 0};
	public static final int[] SOUTH = new int[]{0, 1};
	
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);

		int stillplaying = 1, choice, shoots = 0, correctguesses = 0, incorrectguesses = 0;
		int[] mirrorxpos = new int[10], mirrorypos = new int[10];
		char[] mirrortype = new char[10];
		boolean[] mirrorfound = new boolean[10]; //keep track which mirror has been guessed correctly already

		//TODO: there is a possibility that two (or more) points happen to be same, since random is random.
		for (int i = 0; i < 10; i++) { // creates one time, the coordinate pairs
										// of the 10 mirrors
			mirrorxpos[i] = (int) (Math.random() * 10);
			mirrorypos[i] = (int) (Math.random() * 10 + 10);
			System.out.print(mirrorxpos[i] + " ");
			System.out.print(mirrorypos[i] + " ");
		}
		System.out.println();
		for (int i = 0; i < 10; i++) { // creates once, the mirror type whether
										// \ or/ corresponding to the array
										// coordinates
			if (Math.random() < .5) {
				mirrortype[i] = '/';
			} else {
				mirrortype[i] = '\\';
			}
			mirrorfound[i] = false;
			// System.out.print(mirrortype[i] + " ");
		}

		//need to display?
		display(mirrorxpos, mirrorypos, mirrortype, mirrorfound);

		while (stillplaying == 1) {

			System.out.print("\n\nChoose:\n" + "(1) Shoot a Laser\n" + "(2) Guess at a mirror location\n"
					+ "(0) Quit the game\n");
			System.out.print("\nEnter choice: ");
			choice = cin.nextInt();
			System.out.print("\n");

			switch (choice) {
			case 1:
				shoots++;
				shoot(mirrorxpos, mirrorypos, mirrortype);
				break;
			case 2:
				boolean guessResult = guess(mirrorxpos, mirrorypos, mirrortype, mirrorfound);
				if (guessResult) {
					correctguesses++;
				} else {
					incorrectguesses++;
				}
				break;
			case 0:
				stillplaying = 0;
				break;
			}
		}

		System.out.println("You made " + shoots + " shots, " + correctguesses + " correct guesses and " + incorrectguesses + " incorrect guesses.");
	}

	public static void display(int[] mirrorxpos, int[] mirrorypos, char[] mirrortype, boolean[] mirrorfound) {

		System.out.print("  ");
		for (int i = 20; i < 30; i++) {
			System.out.print(" " + i);
		}

		for (int j = 19; j > 9; j--) {
			System.out.print("\n" + j);
			for (int col = 0; col < 10; col++) {
				char c = '.';
				//check if mirror
				for (int m = 0; m < 10; m++) {
					if (mirrorxpos[m] == col && mirrorypos[m] == j && mirrorfound[m] == true) {
						c = mirrortype[m];
						break;
					}
				}
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

	public static void shoot(int[] mirrorxpos, int[] mirrorypos, char[] mirrortype) { 

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
		moveNext(mirrorxpos, mirrorypos, mirrortype, laserxpos, laserypos, direction);
	}

	//figure out what's the new direction of the laser
	//input: mirror info, position, old direction
	//output: new direction
	private static int[] getNewDirection(int[] mirrorxpos, int[] mirrorypos, char[] mirrortype, int laserxpos, int laserypos, int[] oldDirection) {

		//default to same direction
		int[] newDirection = oldDirection;

		char c = '.';
		for (int m = 0; m < 10; m++) {
			if (mirrorxpos[m] == laserxpos && mirrorypos[m] == (19 - laserypos)) {
				c = mirrortype[m];
				break;
			}
		}

		//check if it's a mirror, if so, adjust the direction
		if (c == '/') {
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
		else if (c == '\\') {
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
	public static void moveNext(int[] mirrorxpos, int[] mirrorypos, char[] mirrortype, int laserxpos, int laserypos, int[] direction) {

		int[] newDirection = getNewDirection(mirrorxpos, mirrorypos, mirrortype, laserxpos, laserypos, direction);
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
			} else {
				exit = newxpos;
			}
			System.out.print("Laser exits at: " + exit + "\n\n");
			//display(mirrorxpos, mirrorypos, mirrortype, mirrorfound);
			return;
		}
		
		//not exiting, keep moving
		moveNext(mirrorxpos, mirrorypos, mirrortype, newxpos, newypos, newDirection);
	}

	public static boolean guess(int[] mirrorxpos, int[] mirrorypos, char[] mirrortype, boolean[] mirrorfound) { // takes two points and loops through the
									// array coordinate pairs and checks if they
									// match a mirror location
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

		for (int m = 0; m < 10; m++) {
			if (guessX == mirrorxpos[m] && guessY == mirrorypos[m]) {
				found = true;
				mirrorfound[m] = true;
				break;
			}
		}

		if (found == true) {
			System.out.print("Mirror successfully found at: (" + guessX + "," + guessY + ")\n\n");
			display(mirrorxpos, mirrorypos, mirrortype, mirrorfound);
		} else {
			System.out.print("No mirror found at: (" + guessX + "," + guessY + ")\n\n");
			//need display if guessed wrong??
			//display(mirrorxpos, mirrorypos, mirrortype, mirrorfound);
		}
		
		return found;
	}
}
