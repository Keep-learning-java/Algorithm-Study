class Solution {
    public int integerBreak(int n) {
		int[] dp = new int[n + 1];
		Arrays.fill(dp, -1);
		dp[1] = 1;

		return dfs(dp, n);        
    }

    public int dfs(int[] dp, int cur) {
		// 기저
		if (cur == 1)
			return 1;

		// 저장한 값 있으면 사용
		if (dp[cur] != -1)
			return dp[cur];

		// 저장한 값 없으면 계산해서 저장
		for (int i = 1; i < cur; i++) {
			// 현재 메모값 vs 현재상태에서 만들 수 있는 모든 경우의 수
			dp[cur] = Math.max(dp[cur], i * (cur - i));
			dp[cur] = Math.max(dp[cur], i * dfs(dp, cur - i));
		}
		
		System.out.println("dp[" + cur + "] : " + dp[cur]);
		return dp[cur];
	}
}