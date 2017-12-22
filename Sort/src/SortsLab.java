//********************************************************************
//  Sortlab.java       Author:
//
//  Driver to exercise the use of several Sorting Arrays methods.
//********************************************************************

public class SortsLab {
	static final int MAX = 20; // changes the array size

	public SortsLab() {
		int[] list = new int[MAX];
		
		// Random Order List//////////////////
		ListSetup.MakeRandom(list);
		System.out.println("Here are the sorts with arrays in random order:");
		testList(list);
		
		// Ascending order list
		ListSetup.MakeInOrder(list);
		System.out.println("Here are the sorts with arrays in ascending order:");
		testList(list);	
		
		// Descending order list
		ListSetup.MakeReverse(list);
		System.out.println("Here are the sorts with arrays in descending order:");
		testList(list);
	}
	
	public void testList(int[] list) {

		int[] list2 = new int[MAX], list3 = new int[MAX], list4 = new int[MAX], list5 = new int[MAX];
		int qcount = 0, icount = 0, scount = 0, mcount = 0;
		
		ListSetup.Copy(list, list2);
		ListSetup.Copy(list, list3);
		ListSetup.Copy(list, list4);
		ListSetup.Copy(list, list5);

		System.out.println("\nList before sorting:");
		ListSetup.Print(list);

		System.out.println("\nHere is the list after the Insertion Sort. ");
		icount = Sorts.Insertion(list);
		ListSetup.Print(list);
		System.out.println("Insertion Sort compares the pivot variable " + icount + " times");

		System.out.println("\nHere is the list after the Selection Sort. ");
		scount = Sorts.Selection(list2);
		ListSetup.Print(list2);
		System.out.println("selection Sort compares the pivot variable " + scount + " times");

		System.out.println("\nHere is the list after the Quicksort (split first). ");
		qcount = Sorts.QuickSort(list3, 0, list.length - 1);
		ListSetup.Print(list3);
		System.out.println("quick Sort compares the pivot variable " + qcount + " times");

		System.out.println("\nHere is the list after the Merge Sort. ");
		mcount = Sorts.mergeSort(list4, 0, list.length - 1);
		ListSetup.Print(list4);
		System.out.println("merge Sort compares the pivot variable " + mcount + " times");

		System.out.println();
	}

	public static void main(String args[]) {

		new SortsLab();
	}
}
