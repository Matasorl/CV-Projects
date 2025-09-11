import java.util.LinkedList;

public class RodCutting {
    private final int[] prices;

    public RodCutting(int[] prices) {
        this.prices = prices;
    }

    public LinkedList<Integer> best_cuts() {
        int n = prices.length; //Length of the rod
        int[] dp = new int[n + 1]; // dp[i] stores the maximum revenue for rod length i
        int[] cuts = new int[n + 1]; // cuts[i] stores the length of the first cut to achieve dp[i]

        // Bottom-up DP approach to determine maximum revenue
        for (int length = 1; length <= n; length++) { // Loop through all rod lengths
            for (int cut = 1; cut <= length; cut++) {  // Try every cut size up to the current length
                if (dp[length] < prices[cut - 1] + dp[length - cut]) {
                    // If this cut yields better revenue, update dp and record the cut
                    dp[length] = prices[cut - 1] + dp[length - cut];
                    cuts[length] = cut;
                }
            }
        }

        // Backtrack to determine which cuts were made
        LinkedList<Integer> result = new LinkedList<>(); // Stores the sequence of cuts
        int remainingLength = n; // Start from full rod length

        while (remainingLength > 0) {
            result.add(cuts[remainingLength]); // Add the best cut at this length
            remainingLength -= cuts[remainingLength];  // Reduce the remaining length
        }
        return result; // Return the list of cuts
    }

    public static void main(String[] args) {
        int[] prices = {  1, 5, 8, 9, 12, 14, 17, 19, 20, 21 };
        LinkedList<Integer> cuts = new RodCutting(prices).best_cuts();
        System.out.println("The best cuts for a rod of length " + prices.length + "m are");
        int total_price=0;
        for (Integer cut : cuts) {
            System.out.println(" - " + cut + "m selling at €"+prices[cut-1]);
            total_price += prices[cut-1];
        }
        System.out.println("The overall price is €"+total_price+".");
    }
}