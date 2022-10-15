class Solution {
    static final int TOP = 0;
	static final int BOTTOM = 1;
	static final int LEFT = 2;
	static final int RIGHT = 3;
    
    public boolean makesquare(int[] matchsticks) {
        // 성냥이 4개 미만인 경우
		if (matchsticks.length < 4)
			return false;

		// 정사각형이므로 모든 성냥 길이의 합이 4로 나누어 떨어져야 함
		int sum = 0;
		for (int length : matchsticks) {
			sum += length;
		}
		if (sum % 4 != 0)
			return false;

		Arrays.sort(matchsticks);
		return dfs(matchsticks, new int[4], matchsticks.length-1, sum / 4);  
    }
    
	public boolean dfs(int[] matchsticks, int[] sums, int index, int target) {
		// 정사각형 만들어짐
		if (sums[TOP] == target && sums[BOTTOM] == target && sums[LEFT] == target && sums[RIGHT] == target)
			return true;

		// 한 변이라도 target보다 크면 더이상 진행 X
		if (sums[TOP] > target || sums[BOTTOM] > target || sums[LEFT] > target || sums[RIGHT] > target)
			return false;

		int matchstick = matchsticks[index];

		// 1. top에 놓아보기
		int temp = sums[TOP];
		sums[TOP] += matchstick;
		boolean top = dfs(matchsticks, sums, index - 1, target);
		sums[TOP] = temp;
		if (top)
			return true;

		// 2. bottom에 놓아보기
		temp = sums[BOTTOM];
		sums[BOTTOM] += matchstick;
		boolean bottom = dfs(matchsticks, sums, index - 1, target);
		sums[BOTTOM] = temp;
		if (bottom)
			return true;

		// 3. left에 놓아보기
		temp = sums[LEFT];
		sums[LEFT] += matchstick;
		boolean left = dfs(matchsticks, sums, index - 1, target);
		sums[LEFT] = temp;
		if (left)
			return true;

		// 4. right에 놓아보기
		temp = sums[RIGHT];
		sums[RIGHT] += matchstick;
		boolean right = dfs(matchsticks, sums, index - 1, target);
		sums[RIGHT] = temp;
		if (right)
			return true;

		return false;
	}
}