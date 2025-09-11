package assignment2;

public class DivideAndConquer {

    public static int fibonacci(int n) {
        // TASK 1.A.a
        if (n == 0) { // base case
            return 0;
        }

        if (n == 1) { // base case
            return 1;
        }

        return fibonacci(n - 1) + fibonacci(n - 2); // break the problem into smaller sub problems using recursion
    }

    public static int search(int[] A, int v)
    {
        // TASK 1.A.b
        int n = A.length;

        if (n == 0) { // base case
            return -1;
        }

        int midIndex = n/2;
        int midValue = A[midIndex];

        if (midValue == v) { // return index if value is in the middle
            return midIndex;
        }

        if (midValue < v) { // copy second half the original array into a new array
            int[] secondhalf = new int[n - midIndex - 1];
            for(int i = midIndex + 1; i < n; i++) {
                secondhalf[i - (midIndex + 1)] = A[i];
            }
            return search(secondhalf, v) + midIndex + 1; // search new array for index of the value but take into account the first half of the original array that isn't part of the new array.
        }
        else {
            int[] firsthalf = new int[midIndex]; // copy the first half of the original array into a new array
            for(int i = 0; i < midIndex; i++) {
                firsthalf[i] = A[i];
            }
            return search(firsthalf, v); // search new array for the index of the value using recursion
        }
    }

    public static void hanoi(int n, char A, char B, char C)
    {
        // TASK 1.A.c
        if (n == 1) { // base case: move disk from source to destination
            System.out.println(A + "->" + C);
        }
        else {
            hanoi(n - 1, A, C, B); // move n-1 disks from A to B, using C as an auxiliary rod
            System.out.println(A + "->" + C);
            hanoi(n - 1, B, A, C); // move the n-1 disk from B to C, using A as an auxiliary rod


        }
    }

    public static void main(String[] args) {
        for (int i=0; i<10; i++) {
            System.out.println(fibonacci(i));
        }
        System.out.println();
        for (int i=0; i<10; i++) {
            System.out.println(search(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, i));
        }
        System.out.println();
        hanoi(4, 'A', 'B', 'C');
    }
}
