import java.util.List;

class Knapsack {

    int maximumValue(int maximumWeight, List<Item> items) {
        int n = items.size();
        // Create a 2D array to store the maximum value at each n and w
        int[][] dp = new int[n + 1][maximumWeight + 1];

        // Build the dp array
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= maximumWeight; w++) {
                // If the item can be included in the knapsack
                if (items.get(i - 1).weight <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - items.get(i - 1).weight] + items.get(i - 1).value);
                } else {
                    // If the item cannot be included, carry forward the previous maximum value
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // The maximum value will be in the bottom-right cell of the array
        return dp[n][maximumWeight];
    }
}