class Solution {
    public int maxNonOverlapping(int[] nums, int target) {
        // make prefix
		int[] prefix = new int[nums.length];
		prefix[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			prefix[i] = prefix[i - 1] + nums[i];
		}

		HashMap<Integer, Integer> map = new HashMap<>(); // <startIndex, endIndex>

		for (int start = 0; start < nums.length; start++) {
			for (int i = 0; start + i < nums.length; i++) {
				int end = start + i;

				int sum;
				if (start == 0)
					sum = prefix[end];
				else
					sum = prefix[end] - prefix[start - 1]; // [start]~[end] 사이 합

				// 합이 같은 경우
				if (sum == target) {
					map.put(start, end);
					check(map, start, end);
					break;
				}
			}

		}

		return map.size(); 
    }
    public void check(HashMap<Integer, Integer> map, int startIndex, int endIndex) {
		// 현재 얻은 startIndex ~ endIndex 의 범위가
		// map에 이미 저장되어있는 범위와 겹치는지 확인
		

		ArrayList<Integer> removeList = new ArrayList<>();
		for (int s : map.keySet()) {
			if (s == startIndex)
				continue;
			
			int e = map.get(s);
			// 범위 겹침
			if ((s <= startIndex && e >= endIndex) || e >= startIndex) {
				// 둘중에, end 가 더 작은 쪽을 살림 (greedy)

				if (e > endIndex) {
					removeList.add(s);
				}
				else {
					removeList.add(startIndex);
				}
			}
		}
		
		for (int remove : removeList) {
			map.remove(remove);
		}
	}
}