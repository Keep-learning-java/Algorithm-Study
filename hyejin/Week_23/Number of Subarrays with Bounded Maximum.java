class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
		int sum = 0;
		int plus = 0;
		int minus = 0;
		
		for(int i=0; i<nums.length; i++) {
			int num = nums[i];
			
			if (num < left) {
				sum -= (++minus);
                sum += (++plus);
			}
			else if (num >= left && num <= right) {
				sum += (++plus);
                minus = 0;
			}
			else if (num > right) {
				plus = 0;
				minus = 0;
			}
		}
		
		return sum;
    }
}