class Solution {
    // d -> l -> r -> u ¼ø
    int[] dr = {1, 0, 0, -1};
    int[] dc = {0, -1, 1, 0};
    String[] word = {"d", "l", "r", "u"};
    int maxR = 0, maxC = 0;
    int goalR = 0, goalC = 0;
    String tmpAnswer = "";
    
    public boolean dfs(int r, int c, int k, String str, int diff){
        
        if(diff > k) return false;
        if(k == 0 && diff == 0){
            tmpAnswer = str;
            return true;
        }
        for(int d=0; d<4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];
            
            if(nr >= 0 && nc >= 0 && nr < maxR && nc < maxC){
                if((diff % 2 == 0 && k % 2 == 0) || (diff % 2 == 1 && k % 2 == 1)){
                    
                    if(dfs(nr, nc, k - 1, str + word[d], Math.abs(nr - goalR) + Math.abs(nc - goalC))){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer;
        maxR = n;
        maxC = m;
        goalR = r - 1;
        goalC = c - 1;
        
        int diff = Math.abs((r - 1) - (x - 1)) + Math.abs((c - 1) - (y - 1));
               
        dfs(x - 1, y - 1, k, "", diff);
        
        answer = tmpAnswer;

        if(answer.equals("")){
            answer = "impossible";
        }
        
        return answer;
    }
}