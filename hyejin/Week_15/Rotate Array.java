class Solution {
    public void rotate(int[] nums, int k) {
        // 실질적으로 nums.length mod k 만큼만 이동하면 됨
		k %= nums.length;

		int[] copy = nums.clone();
		for (int curIndex = 0; curIndex < nums.length; curIndex++) {
			int newIndex = curIndex + k;
			if (newIndex >= nums.length)
				newIndex -= nums.length;
			nums[newIndex] = copy[curIndex];
		}
    }
}