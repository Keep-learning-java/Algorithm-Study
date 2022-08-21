class Solution {
    int[] indexArray = new int[1000001];
    
    public int[] arrayChange(int[] nums, int[][] operations) {
        for(int i=0; i<nums.length; i++){
            indexArray[nums[i]] = i;
        }
        
        for(int i=0; i<operations.length; i++){
            int from = operations[i][0];
            int fromIdx = indexArray[from];
            int to = operations[i][1];
            
            nums[fromIdx] = 0;
            nums[fromIdx] = to;
            indexArray[to] = fromIdx;
        }
        
        return nums;
    }
}