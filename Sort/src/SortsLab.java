//********************************************************************
//  Sortlab.java       Author:
//
//  Driver to exercise the use of several Sorting Arrays methods.
//********************************************************************

public class SortsLab {
	static final int MAX = 1000; // changes the array size

	public SortsLab() {

		int[] list = new int[MAX], list2 = new int[MAX], list3 = new int[MAX], list4 = new int[MAX],
				list5 = new int[MAX];
		int qcount = 0, icount = 0, scount = 0, mcount = 0;

		// Random Order List//////////////////

		System.out.println("Here are the sorts with arrays in random order:\n");
		ListSetup.MakeRandom(list);
		ListSetup.Copy(list, list2);
		ListSetup.Copy(list, list3);
		ListSetup.Copy(list, list4);
		ListSetup.Copy(list, list5);

		System.out.println("\nList before sorting:");
		ListSetup.Print(list);

		System.out.println("\nHere is the list after the Insertion Sort. ");
		icount = Sorts.Insertion(list);
		ListSetup.Print(list);
		System.out.print("Insertion Sort compares the pivot variable " + icount + " times\n");

		System.out.println("\nHere is the list after the Selection Sort. ");
		scount = Sorts.Selection(list2);
		ListSetup.Print(list2);
		System.out.print("selection Sort compares the pivot variable " + scount + " times\n");

		System.out.println("\nHere is the list after the Quicksort (split first). ");
		qcount = Sorts.QuickSort(list3, 0, list.length - 1);
		ListSetup.Print(list3);
		System.out.print("quick Sort compares the pivot variable " + qcount + " times\n");

		System.out.println("\nHere is the list after the Merge Sort. ");
		mcount = Sorts.mergeSort(list4, 0, list.length - 1);
		ListSetup.Print(list4);
		System.out.print("merge Sort compares the pivot variable " + mcount + " times\n");

		// Ascending Order List//////////////////
		/*
		System.out.print("\n\n\nHere are the sorts with arrays in ascending order:\n\n\n");
		for (int n = list.length; n > 1; n--) {
			int iMax = 0;
			for (int i = 1; i < n; i++) {
				if (list[i] > list[iMax])
					iMax = i;
			}
			int listTemp = list[iMax];
			list[iMax] = list[n - 1];
			list[n - 1] = listTemp;
		}
		System.out.println("\nList before sorting (ascending):");
		ListSetup.Print(list);

		System.out.println("\nHere is the list after the Insertion Sort. ");
		icount = Sorts.Insertion(list);
		ListSetup.Print(list);
		System.out.print("Insertion Sort compares the pivot variable " + icount + " times\n");

		System.out.println("\nHere is the list after the Selection Sort. ");
		scount = Sorts.Selection(list2);
		ListSetup.Print(list2);
		System.out.print("selection Sort compares the pivot variable " + scount + " times\n");

		System.out.println("\nHere is the list after the Quicksort (split first). ");
		qcount = Sorts.QuickSort(list3, 0, list.length - 1);
		ListSetup.Print(list3);
		System.out.print("quick Sort compares the pivot variable " + qcount + " times\n");

		System.out.println("\nHere is the list after the Merge Sort. ");
		mcount = Sorts.mergeSort(list4, 0, list.length - 1);
		ListSetup.Print(list4);
		System.out.print("merge Sort compares the pivot variable " + mcount + " times\n");

		// Descending Order List//////////////////

		System.out.print("\n\n\nHere are the sorts with arrays in decending order:\n\n\n");
		for (int n = list.length; n > 1; n--) {
			int iMax = 0;
			for (int i = 1; i < n; i++) {
				if (list[i] < list[iMax])
					iMax = i;
			}
			int listTemp = list[iMax];
			list[iMax] = list[n - 1];
			list[n - 1] = listTemp;
		}
		System.out.println("\nList before sorting (decending):");
		ListSetup.Print(list);

		System.out.println("\nHere is the list after the Insertion Sort. ");
		icount = Sorts.Insertion(list);
		ListSetup.Print(list);
		System.out.print("Insertion Sort compares the pivot variable " + icount + " times\n");

		System.out.println("\nHere is the list after the Selection Sort. ");
		scount = Sorts.Selection(list2);
		ListSetup.Print(list2);
		System.out.print("selection Sort compares the pivot variable " + scount + " times\n");

		System.out.println("\nHere is the list after the Quicksort (split first). ");
		qcount = Sorts.QuickSort(list3, 0, list.length - 1);
		ListSetup.Print(list3);
		System.out.print("quick Sort compares the pivot variable " + qcount + " times\n");

		System.out.println("\nHere is the list after the Merge Sort. ");
		mcount = Sorts.mergeSort(list4, 0, list.length - 1);
		ListSetup.Print(list4);
		System.out.print("merge Sort compares the pivot variable " + mcount + " times\n");
		*/
	}

	public static void main(String args[]) {

		new SortsLab();
	}
}
