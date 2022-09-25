class Solution {

    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> tmpMemory = new ArrayList<>();
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);

        dfs(0, 0, candidates, target);
        
        return ans;
    }
    
    public void dfs(int idx, int sum, int[] candidates, int target){
        
        if(sum == target){
            
            ans.add(new ArrayList<>(tmpMemory));
            return;
        }
        
        for(int i=idx; i<candidates.length; i++){
            if(sum + candidates[i] > target) continue;
            if(i > idx && candidates[i] == candidates[i-1]) continue;
            tmpMemory.add(candidates[i]);
            dfs(i + 1, sum + candidates[i], candidates, target);
            tmpMemory.remove(tmpMemory.size() - 1);
        }
    }
}