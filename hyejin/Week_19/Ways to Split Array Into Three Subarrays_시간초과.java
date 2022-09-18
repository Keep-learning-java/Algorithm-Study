class Solution {
    public int waysToSplit(int[] nums) {
        // 부분합
		int[] prefixSums = new int[nums.length];
		prefixSums[0] = nums[0];
	
		for (int i = 1; i < nums.length; i++) {
			prefixSums[i] = prefixSums[i - 1] + nums[i];
		}

		long count = 0;

		// i: left, mid 구분 지점
		for (int i = 0; i < nums.length; i++) {
			int leftSum = prefixSums[i];
			
			int point = i + 1; // mid, right 구분 지점
			while(point < nums.length-1) {
				int midSum = prefixSums[point] - prefixSums[i];
				int rightSum = prefixSums[prefixSums.length-1] - prefixSums[point];

				// 조건 만족
				if(leftSum <= midSum && midSum <= rightSum) {
					count++;
				}
				
				// 한칸씩 앞으로 가본다
				point++;
			}
		}
		
		return (int) count % 1000000007;
    }
}