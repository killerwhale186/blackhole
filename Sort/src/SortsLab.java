
public class SortsLab
{
    static final int MAX=20;
    
    public SortsLab()
    {
        int[] list = new int[MAX], list2 = new int[MAX], list3 = new int[MAX], list4 = new int[MAX], list5 = new int[MAX];
        int qcount=0, icount = 0, scount = 0, mcount = 0;
            
            
        //Random Order List//////////////////
        
            System.out.println("Random order lists ");
            ListSetup.MakeRandom (list);
            ListSetup.Copy (list,list2);
            ListSetup.Copy (list,list3);
            ListSetup.Copy (list,list4);
            ListSetup.Copy (list,list5);
            
        
            System.out.println("\nList before sorting:");
            ListSetup.Print (list);
            
            System.out.println("\nHere is the list after the Insertion Sort. ");
            icount = Sorts.Insertion(list);
            ListSetup.Print (list);        
        
            System.out.println("\nHere is the list after the Selection Sort. ");
            scount = Sorts.Selection(list2);
            ListSetup.Print (list2);
            
            System.out.println("\nHere is the list after the Quicksort (split first). ");
            //qcount = Sorts.QuickSort(list3, 0, list.length - 1, 0);
            qcount = Sorts.QuickMid(list3);
            ListSetup.Print (list3);
            
            System.out.println("\nHere is the list after the Merge Sort. ");
            mcount = Sorts.mergeSort(list4, 0, list.length - 1);
            ListSetup.Print (list4);
            
            
        //Ascending Order List//////////////////
    
            
            
            
            
    
        //Descending Order List//////////////////
    
            
            
    }    
    
    public static void main(String args[])
    {
        
        new SortsLab();
    }
}
