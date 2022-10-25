import java.util.*;

class Solution {
    int[] sum = new int[5];
    boolean flag = false;
    int lengthSide = 0;
    
    public boolean makesquare(int[] matchsticks) {
        if(matchsticks == null || matchsticks.length < 4) return false;
                
        for(int i=0; i<matchsticks.length; i++) lengthSide += matchsticks[i];
        
        if(lengthSide % 4 != 0) return false;
        lengthSide /= 4;

        Arrays.sort(matchsticks);
        dfs(matchsticks.length - 1, matchsticks);
        
        return flag;
    }
    
    public void dfs(int idx, int[] matchsticks){
        if(flag) return;
        if(idx < 0){
            flag = (sum[1] == sum[2] && sum[2] == sum[3] && sum[3] == sum[4]);
            return;
        }
        
        for(int i=1; i<=4 && flag == false; i++){
            if(sum[i] + matchsticks[idx] <= lengthSide){
                sum[i] += matchsticks[idx];
                dfs(idx - 1, matchsticks);
                sum[i] -= matchsticks[idx];
            }
        }
    }
}