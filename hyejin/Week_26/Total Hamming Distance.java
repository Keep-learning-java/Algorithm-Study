class Solution {
    public int totalHammingDistance(int[] nums) {
		// 몇 개의 문자를 바꿔야 두 문자열이 같아지느냐?
		int count = 0;
		
		for(int i=0; i<32; i++) {
			int one = 0;
			for (int j=0; j<nums.length; j++) {
				one += ((nums[j] >> i) & 1);
			}

			count += (one * (nums.length - one));
		}
		// 모든 수를 두개씩 짝지어서 XOR한 결과의 비트 개수를 구하는 것이니까
		/*
		 * 0 1 0 0
		 * 1 1 1 0
		 * 0 0 1 0
		 * -------
		 * 2 2 2 0 => 6
		 * 
		 * n개의 정수 중 k개가 1을 가지고 있다면,
		 * n-k개는 0을 가지고 있다.
		 * 그래서 k*(n-k) 개의 XOR 쌍을 지을 수 있다.
		 * 이것들이 더해지면 total XOR의 개수가 되는 것이다.
		 * 그러므로 k개의 비트가 해밍거래에 기여한다.
		 * k * (n - k)
		 * */
		
		return count;

    }
}