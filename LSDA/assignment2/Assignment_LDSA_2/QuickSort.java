package assignment2;

public class QuickSort {

    private static int partition(int[] A, int p, int r) {
        // TASK 2.B.a
        // Choose first element as the pivot
        int x = A[p];
        // Element moves right in the array from start
        int i = p - 1;
        // Element move left in the array from end
        int j = r + 1;

        while (true) {
            // Decrement j until an element <= pivot is found
            do {
                j--;
            }
            while (A[j] > x);

            // Increment i until an element >= pivot is found
            do {
                i++;
            }
            while (A[i] < x);

            // If i and j haven't crossed, swap elements at i and j
            if (i < j) {
                // Swap A[i] and A[j]
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
            else {
                // if i and j have crossed, return j as the partition index
                return j;
            }
        }

    }

    private static void quicksort(int[] A, int p, int r) {
        // TASK 2.B.b
        if (p < r) {
            // Partition the array and get the index of the pivot element
            int q = partition(A, p ,r);

            // Recursively sort the left sub array (elements before pivot)
            quicksort(A, p, q);
            // Recursively sort the right sub array (element after pivot)
            quicksort(A, q + 1, r);
        }
    }

    public static void quicksort(int[] A)
    {
        quicksort(A, 0, A.length-1);
    }

    private static void print(int[] A)
    {
        for (int i=0; i<A.length; i++)
        {
            System.out.print(A[i] + ((i<A.length-1)?", ":""));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] A = new int[] {5,2,8,1,3,9,7,4,6};
        quicksort(A);
        print(A);
    }

}
