class Solution {
    public long maxTaxiEarnings(int n, int[][] rides) {
		Arrays.sort(rides, (r1, r2) -> r1[0] - r2[0]);

		long[] dp = new long[n + 1]; // dp[n] : n까지 운행했을 때 최대 수입

		int j = 0;
		for (int i = 1; i <= n; i++) {
		//	dp[i] = Math.max(dp[i - 1], dp[i]);
            dp[i] = dp[i-1];
			while (j < rides.length) {
				int start = rides[j][0];
				int end = rides[j][1];
				int tip = rides[j][2];
				int profit = end - start + tip;

				if (start != i)
					break;

				dp[end] = Math.max(dp[end], dp[start] + profit);
				j++;
			}
		}

		return dp[n];

    }
}
