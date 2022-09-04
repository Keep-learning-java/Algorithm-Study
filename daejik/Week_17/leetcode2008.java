class Solution {

        
    public long maxTaxiEarnings(int n, int[][] A) {
        long[] dp = new long[n + 1];
        Arrays.sort(A, (a, b) -> a[0] - b[0]);
        int j = 0;
        
        for(int i = 1; i <= n; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]);
            
            while (j < A.length && A[j][0] == i) {
                int startPoint = A[j][0];
                int endPoint = A[j][1];
                int tip = A[j][2];
                
                
                dp[endPoint] = Math.max(dp[endPoint], dp[i] + (endPoint - startPoint + tip));
                j++;
            }
        }
        return dp[n];
    }
}