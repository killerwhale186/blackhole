package org.galaxy.labs;

import java.util.Scanner;

public class Lab6 {

	public static void main(String[] args) {
		System.out.print("welcome to the resursion menu\n");
		menu();
	}

	public static void menu() {

		int stillinuse = 1, operation;
		Scanner cin = new Scanner(System.in);

		while (stillinuse == 1) {
			System.out.print("\nWould you like to use the menu? (1 yes || 0 no): ");

			stillinuse = cin.nextInt();
			if (stillinuse == 0) {
				System.exit(0);
			}

			System.out.print("\nletters: 1\nTwos: 2\nPowerOf3: 3\nReverse: 4\nBase5: 5\nPrintWithCommas: 6");
			System.out.print("\n\nChose a recursive operation: ");

			operation = cin.nextInt();
			switch (operation) {
			case 1:
				System.out.print("Enter a letter: ");
				char letter;
				letter = cin.next().charAt(0);
				letters(letter);
				System.out.print("\n");
				break;

			case 2:
				System.out.print("Enter a number: ");
				int x;
				x = cin.nextInt();
				int t = twos(x);
				System.out.print(t + "\n");
				break;
				
			case 3:
				System.out.print("Enter a number: ");
				int a;
				a = cin.nextInt();
				if (powerOf3(a) == true) {
					System.out.print(a + " is a power of 3\n");
				} else {
					System.out.print(a + " is not a power of 3\n");
				}
				break;

			case 4:
				System.out.print("Enter a number: ");
				int c;
				c = cin.nextInt();
				reverse(c);
				System.out.print("\n");
				break;
				
			case 5:
				System.out.print("Enter a number: ");
				int f = cin.nextInt();
				base5(f);
				System.out.print("\n");
				break;

			case 6:
				System.out.print("Enter a number: ");
				int k;
				k = cin.nextInt();
				printWithCommas(k);
				System.out.print("\n");
				break;
			}
		}
	}

	public static void letters(char letter) {
		lettersHelper(letter, 0);
	}
	
	public static void lettersHelper(char letter, int index) {
		String abc = "abcdefghijklmnopqrstuvwxyz";
		System.out.print(abc.charAt(index));
		if (abc.charAt(index) != letter) {
			lettersHelper(letter, index+1);
		}
	}

	public static int twos(int x) {
		if (x % 2 == 1) {
			return 0;
		}
		else {
			return 1 + twos(x / 2);
		}
	}

	public static boolean powerOf3(int x) {
		if (x == 1) {
			return true;
		}

		//really should use cube root of x as upper bound. but not sure if it's allowed
		for (int i = 2; i < x; i++) {
			if (x % (i*i*i) == 0) {
				return powerOf3(x / (i*i*i));
			}
		}

		return false;
	}

	public static int reverse(int x) {
		if (x < 1) {
			return 0;
		}
		else {
			int digit = x % 10;
			x = x / 10;
			System.out.print(digit);
			reverse(x);
		}
		return 0;
	}

	public static void base5(int x) {
		if (x >= 5) {
			int remainder = x % 5;
			base5(x / 5);
			System.out.print(remainder);
		} else {
			System.out.print(x);
		}
	}

	public static void printWithCommas(int x) {
		if (x >= 1000) {
			printWithCommas(x / 1000);
			System.out.print(",");
			if (x % 1000 < 100) {
				System.out.print("0");
			}
			System.out.print(x % 1000);
		}
		else {
			System.out.print(x);
		}
	}

}