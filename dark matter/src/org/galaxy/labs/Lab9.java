package org.galaxy.labs;

import java.util.Arrays;

import java.util.Scanner;

public class Lab9 {

	public static void main(String[] args) {

		int size = (int) (Math.random() * 30) + 20, entry, myentry;
		int[] outputs = new int[] {-1, 0};

		Scanner cin = new Scanner(System.in);

		int[] array = new int[size];

		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * 99);
		}

		System.out.print("Original array of " + size + " elements:\n");
		for (int j = 0; j < array.length; j++) {
			if ((j + 1) % 10 == 0) {
				System.out.print(array[j] + " ");
				System.out.print("\n");
			}
			else {
				System.out.print(array[j] + " ");
			}
		}

		System.out.print("\n\nSorted array of " + size + " elements:\n");
		Arrays.sort(array);

		for (int i = 0; i < array.length; i++) {
			if ((i + 1) % 10 == 0) {
				System.out.print(array[i] + " ");
				System.out.print("\n");
			}
			else {
				System.out.print(array[i] + " ");
			}
		}

		System.out.print("\n\nBuilt in binary search: \n\n");
		for (int i = 0; i <= 1; i++) {
			System.out.print("Which entry: ");
			entry = cin.nextInt();
			if (Arrays.binarySearch(array, entry) < 0) {
				System.out.print("Status: not found\n");
			}
			else {
				System.out.print("Status: found at index " + Arrays.binarySearch(array, entry) + "\n");
			}
		}

		System.out.print("\nMy binary search: \n\n");
		for (int i = 0; i <= 1; i++) {
			System.out.print("Which entry: ");
			myentry = cin.nextInt();
			search(array, myentry, 0, array.length - 1, outputs);
			// if entry is found or not
			if (outputs[0] >= 0) {
				System.out.print("Status: found at index " + outputs[0] + " after " + outputs[1] + " probes\n");
			}
			else {
				System.out.print("Status: not found after " + outputs[1] + " probes\n");
			}
		}
	}

	// outputs[] {index, probes} //probes are number of comparisons made between
	public static void search(int array[], int myentry, int from, int to, int[] outputs) {

		//when writing recursive methods, always check special case that would end the recursive calls first.
		if (from == to) {
			if (myentry == array[from]) {
				outputs[0] = from;
			}
			return;
		}
		
		int temp = from + (to - from) / 2;
		if (myentry < array[temp]) {
			outputs[1]++;
			search(array, myentry, from, temp, outputs);
		}
		else if (myentry > array[temp]) {
			outputs[1]++;
			search(array, myentry, temp + 1, to, outputs);
		}
		else {
			outputs[0] = temp;
		}
	}

}
