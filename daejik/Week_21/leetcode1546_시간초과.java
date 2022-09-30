class Solution {
    int ans = 0;
    int targetMemo = 0;
    
    public int maxNonOverlapping(int[] nums, int target) {
        targetMemo = target;
        
        dfs(nums, 0, 0, 0);
        
        
        
        return ans;
    }
    
    public void dfs(int[] nums, int idx, int sum, int tmpAns){
        
        if(idx > 0 && sum == targetMemo){
            tmpAns++;
            
            if(tmpAns > ans) ans = tmpAns;
            
            sum = 0;
        }
        
        if(idx >= nums.length) return;
        
        
        dfs(nums, idx + 1, sum + nums[idx], tmpAns);
    
        sum = 0;
        dfs(nums, idx + 1, sum, tmpAns);
        
    }
}