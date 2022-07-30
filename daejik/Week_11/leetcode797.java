import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    List<Integer> visitInform = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<>();
    int[][] graphInform;
    
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        visitInform.add(0);
        graphInform = graph;
        dfs(0);    
        
        return ans;
    }
    
    public void dfs(int idx) {
        
        if(idx == graphInform.length - 1){
            // 정답 담기, 깊은 복사
        	List<Integer> tmp = new ArrayList<>();
        	tmp.addAll(visitInform);
            ans.add(tmp);
            
            return;
        }
        
        if(graphInform[idx].length > 0){
            // 다음 방문
            for(int i=0; i<graphInform[idx].length; i++){
                int nextIdx = graphInform[idx][i];

                visitInform.add(nextIdx);
                dfs(nextIdx);
                visitInform.remove(visitInform.size() - 1);
            }            
        }
    }
}