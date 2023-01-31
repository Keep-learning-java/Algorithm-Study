class Solution {
    final static int MOD = 1000000007;
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
		// hCuts, vCuts 오름차순 정렬
		Arrays.sort(horizontalCuts);
		Arrays.sort(verticalCuts);

		// h, v로 잘렸을 때 각각 최댓값 구하기
		int maxHorizon = horizontalCuts[0];
		int maxVertical = verticalCuts[0];

		for (int i = 1; i < horizontalCuts.length; i++) {
			int width = horizontalCuts[i] - horizontalCuts[i - 1];
			maxHorizon = Math.max(maxHorizon, width);
		}
		maxHorizon = Math.max(maxHorizon, h - horizontalCuts[horizontalCuts.length - 1]);

		for (int i = 1; i < verticalCuts.length; i++) {
			int width = verticalCuts[i] - verticalCuts[i - 1];
			maxVertical = Math.max(maxVertical, width);
		}
		maxVertical = Math.max(maxVertical, w - verticalCuts[verticalCuts.length - 1]);

		// 넓이
		long maxArea = (long) maxHorizon * (long) maxVertical;
		return (int) (maxArea % MOD);
    }
}