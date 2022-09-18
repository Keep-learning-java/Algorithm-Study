class Solution {
    public int waysToSplit(int[] nums) {
		// 부분합
		int[] prefixSums = new int[nums.length];
		prefixSums[0] = nums[0];

		for (int i = 1; i < nums.length; i++) {
			prefixSums[i] = prefixSums[i - 1] + nums[i];
		}
		int count = 0;

        // 1 2 | 2 2 5 0
		// i: left, mid 구분 지점
		for (int i = 0; i < nums.length; i++) {
			int leftSum = prefixSums[i];

			int left = binarySearch(prefixSums, leftSum, i, i + 1, true);
			int right = binarySearch(prefixSums, leftSum, i, i + 1, false);
			if (left == -1 || right == -1)
				continue;

			count = (count % 1000000007) + ((right - left + 1) % 1000000007);
		}

		return count;
    }
    
	public int binarySearch(int[] prefixSums, int leftSum, int left, int point, boolean searchingLeft) {
		int l = left + 1;
		int r = prefixSums.length - 2;
		int res = -1;
		
		while (l <= r) {
			int m = (l + r) / 2;

			// 0 ~ left : left
			// left+1 ~ m : mid
			// m+1 ~ end : right
			int midSum = prefixSums[m] - leftSum;
			int rightSum = prefixSums[prefixSums.length - 1] - prefixSums[m];

			if (leftSum <= midSum && midSum <= rightSum) {
				res = m;
				if (searchingLeft) 
                    r = m -1;
				else 
                    l = m + 1;
			} else if (midSum < leftSum) { //
				// midSum 늘려줌
				l = m + 1;
			} else {
				r = m -1;
			}
		}

		return res;
	}
}