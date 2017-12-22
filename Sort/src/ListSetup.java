
//********************************************************************
//  ListSetup.java       Author:
//
//  Driver to initialize and print lists of Arrays.
//********************************************************************

import java.util.Random;

public class ListSetup {
	public static Random rand = new Random();

	public static void MakeInOrder(int[] list) {
		for (int j = 0; j < list.length; j++)
			list[j] = j;
	}

	public static void MakeRandom(int[] list) {
		for (int j = 0; j < list.length; j++)
			// times 10, to reduce the chance of getting too many duplicates
			list[j] = rand.nextInt(list.length * 10);
	}

	public static void MakeReverse(int[] list) {
		for (int j = 0; j < list.length; j++)
			list[j] = list.length - j - 1;
	}

	public static void Print(int[] list) {
		for (int j = 0; j < list.length; j++)
			System.out.print(list[j] + "  ");
		System.out.print("\n");
	}

	public static void Copy(int[] list, int[] list2) {
		for (int j = 0; j < list.length; j++)
			list2[j] = list[j];
	}
}
