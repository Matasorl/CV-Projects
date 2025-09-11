import java.util.Arrays;



public class HDDAllocation {
    private final int[] hdds; // Array storing the capacities of hard drives
    private final int[] files; // Array storing the sizes of files to be allocated
    private final int[] allocation; // Stores the assigned HDD for each file
    private final int[] usedSpace; // Tracks used space on each HDD

    // Constructor to initialize HDD capacities and file sizes
    public HDDAllocation(int[] hdds, int[] files) {
        this.hdds = hdds;
        this.files = files;
        this.allocation = new int[files.length];
        this.usedSpace = new int[hdds.length];
        Arrays.fill(allocation, -1); // -1 means unallocated
    }

    // Method to generate a valid file allocation using backtracking
    public int[] generate_allocation() {
        if (makeNextDecision(0)) {
            return allocation;
        }
        return new int[0]; // Return empty array if no valid allocation
    }

    // Recursive backtracking function to allocate files
    private boolean makeNextDecision(int fileIndex) {
        if (fileIndex == files.length) {
            return true; // All files have been allocated successfully
        }

        for (int i = 0; i < hdds.length; i++) { // Try placing file on each HDD
            if (isConsistent(fileIndex, i)) { // Check if placement is valid
                allocation[fileIndex] = i;
                usedSpace[i] += files[fileIndex];

                if (makeNextDecision(fileIndex + 1)) { // Recurse to next file
                    return true;
                }

                // Backtrack: Undo the decision if it leads to failure
                usedSpace[i] -= files[fileIndex];
                allocation[fileIndex] = -1;
            }
        }

        return false; // No valid allocation found for this configuration
    }

    // Checks if a file can be placed on a given HDD without exceeding capacity
    private boolean isConsistent(int fileIndex, int hddIndex) {
        return usedSpace[hddIndex] + files[fileIndex] <= hdds[hddIndex];
    }


    public static void main(String[] args) {
        int[] hdds = {1000, 1000, 2000};
        int[] files = {300, 200, 300, 1200, 400, 700, 700 };
        int[] allocation = new HDDAllocation(hdds, files).generate_allocation();
        for (int i=0; i<allocation.length; i++) {
            System.out.println("File "+i+" has size " + files[i] + "MB and goes on HDD"+allocation[i] + ".");
        }
        for (int j=0; j<hdds.length; j++)
        {
            int space_used = 0;
            for (int i=0; i<allocation.length; i++) {
                if (allocation[i]==j) {
                    space_used += files[i];
                }
            }
            System.out.println("HDD"+ j + " space used " + space_used + "MB / " + hdds[j] + "MB.");
        }
    }
}

