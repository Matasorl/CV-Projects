public class LongestCommonSubsequence {
    private final String X;
    private final String Y;

    public LongestCommonSubsequence(String X, String Y) {
        this.X = X;
        this.Y = Y;
    }
    // Method to compare the two strings and find the LCS
    public String compare() {
        int m = X.length(); // Length of string X
        int n = Y.length(); // Length of string Y
        int[][] c = new int[m + 1][n + 1]; // DP table to store lengths of LCS

        // Fill the DP table using bottom-up dynamic programming
        for (int i = 1; i <= m; i++) { // Looping through characters of X
            for (int j = 1; j <= n; j++) { // Looping through characters of Y
                if (X.charAt(i - 1) == Y.charAt(j - 1)) { 
                    // If characters match, increment the value from the diagonal
                    c[i][j] = c[i - 1][j - 1] + 1;
                } else {
                    // If not a match, take the max from the left or above
                    c[i][j] = Math.max(c[i - 1][j], c[i][j - 1]);
                }
            }
        }

        // Recover the actual LCS from the DP table by backtracking
        StringBuilder lcs = new StringBuilder(); 
        int i = m, j = n; // Start from the bottom-right of the DP table
        while (i > 0 && j > 0) {
            if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                // If the characters match, add to result and move diagonally
                lcs.append(X.charAt(i - 1));
                i--;
                j--;
            } else if (c[i - 1][j] >= c[i][j - 1]) {
                // If the  top cell is greater or equal, move up 
                i--;
            } else {
                // Otherwise, move left
                j--;
            }
        }

        // Reverse the string since I built it backwards, and return it
        return lcs.reverse().toString();
    }

    public static void main(String[] args) {
        String X = "ABCBDAB";
        String Y = "BDCABA";
        String Z = new LongestCommonSubsequence(X, Y).compare();
        System.out.println("The longest common subsequence of '" + X + "' and '" + Y + "' is '" + Z + "'.");
    }
}
