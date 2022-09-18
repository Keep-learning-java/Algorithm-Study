class Solution {
    List<List<Integer>> ans;
    List<Integer> tmp = new ArrayList<>();
    
    public List<List<Integer>> combine(int n, int k) {
        ans = new ArrayList<>();
        
        dfs(1, n, 0, k);
            
        return ans;
    }
    
    public void dfs(int pickedIdx, int limitIdx, int pickedNum, int limitNum){
        
        if(pickedNum == limitNum){
            ans.add(new ArrayList<>(tmp));
            return;
        }
        
        for(int i=pickedIdx; i<=limitIdx; i++){
            tmp.add(i);
            dfs(i + 1, limitIdx, pickedNum + 1, limitNum);
            tmp.remove(tmp.size() - 1);
        }
    }
}