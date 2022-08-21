class Solution {
	public int[] arrayChange(int[] nums, int[][] operations) {
		
		HashMap<Integer, Integer> indexMap = saveIndex(nums);
		
		// {1, 3} -> 1을 3으로 변경
		for(int i=0; i<operations.length; i++) {
			int source = operations[i][0];
			int target = operations[i][1];
			int indexOfSource = indexMap.get(source);
			
			nums[indexOfSource] = target;
			
			// update index
			indexMap.put(target, indexOfSource);
		}
		
		return nums;
	}
	
	public HashMap<Integer, Integer> saveIndex(int[] nums) {
		HashMap<Integer, Integer> ret = new HashMap<Integer, Integer>();
		
		for(int index=0; index<nums.length; index++) {
			ret.put(nums[index], index);
		}
		
		return ret;
	}
}