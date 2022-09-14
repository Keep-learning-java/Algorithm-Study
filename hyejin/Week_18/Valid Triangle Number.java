class Solution {
    public int triangleNumber(int[] nums) {
        /*
		 * 삼각형 변이 a, b, c 일 때 c가 가장 긴 변이라면 a + b > c 가 성립한다.
		 */
		int count = 0;

		// 오름차순 정렬
		Arrays.sort(nums);

		// 가장 긴 변 고정으로 두고
		for (int maxIndex = nums.length - 1; maxIndex > 1; maxIndex--) {
			int left = 0;
			int right = maxIndex - 1;

			while (left < right) {
				// left, right가 조건을 만족한다면
				// left는 left ~ right 사이의 값을 가질 수 있다.
				if (nums[left] + nums[right] > nums[maxIndex]) {
					count += right - left; // 경우의 수
					right--;
				} 
				// 만족하지 않는다면 늘려봐야지
				else {
					left++;
				}
			}
		}
		
		return count;
    }
}