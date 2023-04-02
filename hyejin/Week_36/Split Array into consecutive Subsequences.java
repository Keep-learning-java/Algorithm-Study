class Solution {
    public boolean isPossible(int[] nums) {
        if (nums.length < 3)
			return false;

		HashMap<Integer, Integer> freq = new HashMap<>(); // 숫자 개수
		HashMap<Integer, Integer> need = new HashMap<>(); // 숫자 : 얘가 필요하다고 몇번 적혔는지
		for (int num : nums) {
			freq.put(num, freq.getOrDefault(num, 0) + 1);
		}

		for (int number : nums) {
			// 남아있지 않으면 넘어감
			if (freq.get(number) == 0)
				continue;

			// 남아있으면
			// number가 필요했었는지 일단 확인
			else if (need.getOrDefault(number, 0) > 0) {
				freq.put(number, freq.get(number) - 1);
				need.put(number, need.get(number) - 1);
				
				// 이제 number 다음 숫자가 필요하다고 적어둠
				need.put(number + 1, need.getOrDefault(number + 1, 0) + 1);
			}

			// number는 필요 없었으므로 새로운 그룹을 찾아야 함
			// number, number+1, number+2 묶음 만들 수 있는지 확인해보자..
			else if (freq.getOrDefault(number, 0) > 0 && freq.getOrDefault(number + 1, 0) > 0
					&& freq.getOrDefault(number + 2, 0) > 0) {
				freq.put(number, freq.getOrDefault(number, 0) - 1);
				freq.put(number + 1, freq.getOrDefault(number + 1, 0) - 1);
				freq.put(number + 2, freq.getOrDefault(number + 2, 0) - 1);
				
				// number+3 도 올 수 있으면 좋겠네
				need.put(number + 3, need.getOrDefault(number + 3, 0) + 1);
			}
			// number는 필요하지도 않았고, 새로운 그룹을 만들 수도 없음..
			else
				return false;
		}
		return true;
    }
}