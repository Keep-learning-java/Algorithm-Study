class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int[][] cache = new int[bricks + 1][ladders + 1];
		for (int[] row : cache) {
			Arrays.fill(row, -1);
		}

		return dp(cache, 0, 0, 0, heights, bricks, ladders);

    }
    
    // cache[usedBricks][usedLadders] : usedBricks개의 벽돌, usedLadders개의 사다리를 이용해서
	// 가장 멀리 갈 수 있는 빌딩의 인덱스 반환
	public int dp(int[][] cache, int cur, int usedBricks, int usedLadders, int[] heights, int remainBricks,
			int remainLadders) {
		// 기저 [더 이상 갈 수 없다]
		// 기저 1. 현재 위치가 마지막 빌딩
		if (cur == heights.length - 1) {
			return cur;
		}
		// 기저 2. 올라가야 하는데 벽돌/사다리 다 씀
		if (heights[cur+1] > heights[cur] && remainBricks == 0 && remainLadders == 0) {
			return cur;
		}
		// 기저 3. 벽돌은 모자라고 사다리는 없음
		int neededBricks = heights[cur + 1] - heights[cur];
		if (neededBricks > remainBricks && remainLadders == 0) {
			return cur;
		}

		// 캐시 있으면 반환
		if (cache[usedBricks][usedLadders] != -1)
			return cache[usedBricks][usedLadders];

		// 캐시 없으면 계산

		// Case 1 : 벽돌, 사다리 안 써도 됨
		if (heights[cur] >= heights[cur + 1]) {
			cache[usedBricks][usedLadders] = dp(cache, cur + 1, usedBricks, usedLadders, heights, remainBricks,
					remainLadders);
			return cache[usedBricks][usedLadders];
		}

		// Case 2 : 벽돌을 쓰는 경우
		int brickCase = Integer.MIN_VALUE;
		int need = heights[cur + 1] - heights[cur];
		if (remainBricks > need) {
			brickCase = dp(cache, cur + 1, usedBricks + need, usedLadders, heights, remainBricks - need, remainLadders);
		}
		// Case 3 : 사다리를 쓰는 경우
		int ladderCase = Integer.MIN_VALUE;
		if (remainLadders > 0)
			ladderCase = dp(cache, cur + 1, usedBricks, usedLadders + 1, heights, remainBricks, remainLadders - 1);

		// Case 2, 3중 큰 값으로 사용
		cache[usedBricks][usedLadders] = Math.max(brickCase, ladderCase);

		return cache[usedBricks][usedLadders];
	}
}