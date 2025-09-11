package assignment2;

public class MergeSort {

    private static int[] merge(int[] A1, int[] A2)
    {
        // TASK 2.A.a
        // Create new array the size of the two arrays combined
        int[] solved = new int[A1.length + A2.length];
        int i = 0; // Array pointer for A1
        int j = 0; // Array pointer for A2

        // Go through both arrays and merge them into the solved array
        for (int k = 0; k < solved.length; k++) {
            // if all elements from the first array have been added, add the remaining from the second array
            if (i >= A1.length) {
                solved[k] = A2[j];
                j++;

            }
            // if all elements from the second array have been added, add the remaining from the first array
            else if (j >= A2.length) {
                solved[k] = A1[i];
                i++;
            }

            // if the current element from the first array is smaller or equal add it to the solved array and move pointer for A1
            else if (A1[i] <= A2[j]){
                solved[k] = A1[i];
                i++;
            }

            // else if the element is smaller in the second array add it to the solved array and move pointer for A2
            else {
                solved[k] = A2[j];
                j++;
            }

        }
        return solved;
    }

    public static int[] mergesort(int[] A)
    {
        // TASK 2.A.b
        if (A.length <= 1) { // base case : array is solved already if the array has 1 or 0 elements
            return A;
        }
        // Get the mid-point of the array to divide it into two halves
        int mid = A.length / 2;
        // Create two new arrays to hold the first and second halves of the original array
        int[] first = new int[mid];
        int[] second = new int[A.length - mid];

        // Copy the first half of the original array
        for(int i = 0; i < mid; i++ ) {
            first[i] = A[i];
        }

        // Copy the second half of the original array
        for(int i = mid; i < A.length; i++) {
            second[i - mid] = A[i];
        }

        // Recursively split and sort both halves
        first = mergesort(first);
        second = mergesort(second);

        // Merge the two sorted arrays into one
        return merge(first, second);


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
        print(merge(new int[] {1,3,5,7,9}, new int[] {2,4,6,8}));
        print(mergesort(new int[] {5,2,8,1,3,9,7,4,6} ));
    }

}
