class Solution {
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount+1];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}
		
		return dfs(coins, 0, amount, dp);
    }
    
    /*
	 * dp[i][j]
	 * 0~i번째 코인까지를 사용해서 */
	public int dfs(int[] coins, int index, int amount, int[][] dp) {
		// 남은 금액이 없는 경우
		if (amount == 0)
			return 1;
		
		// 마지막 코인
		if (index == coins.length) {
			return 0;			
		}
		
		// 저장했던 값이 있다면 그걸 쓴다
		if (dp[index][amount] != -1)
			return dp[index][amount];
		
		// 이번 코인 스킵
		int answer = dfs(coins, index+1, amount, dp);
		
		// 아직 합계 금액 모자란다면
		if (amount >= coins[index])
			answer += dfs(coins, index, amount - coins[index], dp); // 이번 코인 사용
		
		// 값 저장
		dp[index][amount] = answer;
		return answer;
	}
}