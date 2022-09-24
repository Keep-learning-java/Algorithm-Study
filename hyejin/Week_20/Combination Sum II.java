class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
		backTracking(result, new ArrayList<>(), candidates, 0, target, 0);
		return result;
    }
    
    public void backTracking(List<List<Integer>> result, List<Integer> combination, int[] candidates, int index,
			int target, int sum) {
		// 종료 1. 합계 target과 일치
		if (sum == target) {
			// 중복 방지
			if (!result.contains(combination))	
				result.add(new ArrayList<Integer>(combination));
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


        combination.add(candidates[index]);
        backTracking(result, combination, candidates, index + 1, target, sum + candidates[index]);
        combination.remove(combination.size() - 1);
        
        while (index + 1 < candidates.length && candidates[index] == candidates[index+1])
			index++; 
        
        backTracking(result, combination, candidates, index + 1, target, sum);

	}
}