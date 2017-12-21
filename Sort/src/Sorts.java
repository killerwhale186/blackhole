//********************************************************************
//  Sorts.java       Author:
//
//  A collection of methods for sorting Arrays.
//********************************************************************

public class Sorts {

	private static int[] temp;

	public static int Insertion(int[] list) {
		int icount = 0;

		for (int n = 1; n < list.length; n++) {

			// Save the next element to be inserted:
			int listTemp = list[n];

			// Going backward from list[n-1], shift elements to the
			// right until you find an element list[i] <= aTemp:

			int i = n;
			while (i > 0 && listTemp < list[i - 1]) { // once the numbers
														// compared are in the
														// correct order least
														// to greatest, end loop
				list[i] = list[i - 1]; // moves to the left one if they aren't
										// in the correct order least to
										// greatest
				i--;
				icount++;
			}

			// Insert the saved element after a[i]:
			list[i] = listTemp;

			// Increment n (accomplished by n++ in the for loop).
		}

		return icount;
	}

	public static int Selection(int[] list) {
		int scount = 0;

		for (int n = list.length; n > 1; n--) {
			// Find the index iMax of the largest element
			// among list[0], ..., list[n-1]:

			int iMax = 0;
			for (int i = 1; i < n; i++) {
				if (list[i] > list[iMax]) // acending to decending switch
											// between < and >
					iMax = i;
				scount++;
			}

			swap(list, iMax, n - 1);

			// int listTemp = list[iMax];
			// list[iMax] = list[n-1];
			// list[n-1] = listTemp;
		}

		return scount;
	}

	public static int Merge(int[] list, int from, int middle, int to) {

		int mcount = 0;
		temp = new int[list.length];
		int i = from, j = middle + 1, k = from;

		// While both arrays have elements left unprocessed:
		while (i <= middle && j <= to) {
			mcount++;
			if (list[i] < list[j]) {
				temp[k] = list[i]; // Or simply temp[k] = a[i++];
				i++;
			} else {
				temp[k] = list[j];
				j++;
			}
			k++;
		}

		// Copy the tail of the first half, if any, into temp:
		while (i <= middle) {
			temp[k] = list[i]; // Or simply temp[k++] = a[i++]
			i++;
			k++;
		}

		// Copy the tail of the second half, if any, into temp:
		while (j <= to) {
			temp[k] = list[j]; // Or simply temp[k++] = a[j++]
			j++;
			k++;
		}

		// Copy temp back into a
		for (k = from; k <= to; k++)
			list[k] = temp[k];

		return mcount;
	}

	public static int mergeSort(int[] list, int from, int to) {
		int mcount = 0;

		if (to - from < 2) { // Base case: 1 or 2 elements
			if (to > from && list[to] < list[from]) {
				int listTemp = list[to]; // swap a[to] and a[from]
				list[to] = list[from];
				list[from] = listTemp;
			}

		} else { // recursive case
			int middle = (from + to) / 2;
			mcount += mergeSort(list, from, middle);
			mcount += mergeSort(list, middle + 1, to);
			mcount += Merge(list, from, middle, to);
		}

		return mcount;
	}

	public static int QuickSort(int[] list, int from, int to) {
		int qcount = 0;

		if (from >= to) {
			return 0;
		}
		// Choose pivot list[p]:
		int p = from;
		// The choice of the pivot location may vary:
		// you can also use p = from or p = to or use
		// a fancier method, say, the median of the above three.

		// Partition:

		int i = from;
		int j = to;
		while (i <= j) {
			if (list[i] <= list[p]) {
				qcount++;
				i++;
			} else if (list[j] >= list[p]) {
				//should increase by 2? 
				qcount++;
				j--;
			} else {
				//should increase by 2? 
				qcount++;
				swap(list, i, j);
				i++;
				j--;
			}
		}

		// Finish partitioning:
		if (p < j) { // place the pivot in its correct position
			swap(list, j, p);
			p = j;
		}

		else if (p > i) {
			swap(list, i, p);
			p = i;
		}

		qcount += QuickSort(list, from, p - 1);
		qcount += QuickSort(list, p + 1, to);

		return qcount;
	}

	public static int QuickMid(int[] list) {

		return 0;
	}

	public static int QuickRandom(int[] list) {

		return 0;
	}

	private static void swap(int[] list, int a, int b) {
		int temp = list[a];
		list[a] = list[b];
		list[b] = temp;
	}

}

