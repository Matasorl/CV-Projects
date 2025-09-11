package assignment2;

//import jdk.incubator.vector.VectorOperators;

public class HeapOfBinaryTries {
    private BinaryTrie[] A;
    private int heapsize;


    private void heapify(int i)
    {
        // TASK 3.A.a
        int left = (2 * i) + 1; // initialise the left child
        int right = (2 * i) + 2; // initialise the right child
        int smallest = i; // assign the current index to the smallest

        if (left < heapsize && A[left].compare(A[smallest])) { // check if the left child exists and checks if the current smaller index is smaller
            smallest = left; // assign left child to be the smallest
        }

        if (right < heapsize && A[right].compare(A[smallest])) { // check if the right child exists and checks if the current smaller index is smaller
            smallest = right; // assign right to be the smallest
        }

        if (smallest != i) { // if the current index isn't the smallest element swap with the smallest child
            BinaryTrie swap = A[i];
            A[i] = A[smallest];
            A[smallest] = swap;
            heapify(smallest); // recursively call heapify to maintain heap property
        }

    }


    public HeapOfBinaryTries(BinaryTrie[] A)
    {
        // TASK 3.A.b
        this.A = A; // initialise the heap with provided array
        this.heapsize = A.length; // set heapsize to the total array length

        for (int i = (heapsize / 2) -1; i >= 0; i--) { // start at index that has children and go towards the root
            heapify(i); // heapify each node
        }

    }

    public BinaryTrie extractMin()
    {
        // TASK 3.A.c
        if (heapsize <= 0) { // check if heap if empty
            System.out.println("Heap is empty");
            return null;
        }

        BinaryTrie minimum = A[0]; // minimum of heap is the first element of the heap
        A[0] = A[heapsize - 1]; // move last element of the heap to the root
        heapsize --; // adjust heap size to remove the moved element
        heapify(0); // heapify newly moved element

        return minimum; // minimum is returned
    }

    public void insert(BinaryTrie x) {
        // TASK 3.A.d
        if (heapsize == A.length){ // check if the heap is full
            System.out.println("Heap is full");
            return; // exit method if the heap is full
        }

        int i = heapsize; // set i to the current size of the heap
        A[i] = x; // add new element to the end of the heap
        heapsize ++; // increment heap to take into account newly added element


        while (i > 0 && A[(i - 1) / 2].compare(A[i])) { // while the current element is smaller than its parent, continue swapping
            BinaryTrie swap = A[i];
            A[i]= A[(i - 1) / 2];
            A[(i - 1) / 2] = swap;

            i = (i - 1) / 2; // adjust i value to follow newly added element
        }
    }

    public int size()
    {
        return heapsize;
    }
}
