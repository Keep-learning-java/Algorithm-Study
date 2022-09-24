class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
		backTracking(result, new ArrayList<>(), candidates, 0, target, 0);
		return result;
    }
    
    public void backTracking(List<List<Integer>> result, List<Integer> combination, int[] candidates, int index,
			int target, int sum) {
		// 종료 1. 합계 target과 일치
		if (sum == target) {
			// 중복 방지
			ArrayList<Integer> temp = new ArrayList<>(combination);
			Collections.sort(temp);
			if (!result.contains(temp))	
				result.add(new ArrayList<Integer>(temp));
			return;
		}
		// 종료 2. 배열 끝까지 옴
		else if (index >= candidates.length) {
			return;
		}
		// 종료 3. 합계 초과
		else if (sum > target) {
			return;
		}

		for (int i = index; i < candidates.length; i++) {
			int cur = candidates[i];
			// 새로운 합계가 target을 넘지 않을 경우 진행
			if (target >= sum + candidates[i]) {
				combination.add(candidates[i]);
				backTracking(result, combination, candidates, i + 1, target, sum + candidates[i]);
				combination.remove(combination.size() - 1);
			}
		}
	}
}