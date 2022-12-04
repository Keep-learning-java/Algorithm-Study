class Solution {
    public int countCharacters(String[] words, String chars) {
        int[] charsCnt = new int['z' - 'a' + 1];
        int ans = 0;
        
        for(int i=0; i<chars.length(); i++){
            charsCnt[chars.charAt(i) - 'a']++;
        }
        
        for(String nowWord : words){
            int[] tmpCnt = new int['z' - 'a' + 1];
            boolean flag = true;
            for(int j=0; j<nowWord.length(); j++){
                int idx = nowWord.charAt(j) - 'a';
                
                if(tmpCnt[idx] >= charsCnt[idx]){
                    flag = false;
                    break;
                }
                tmpCnt[idx]++;
            }
            
            if(flag == true) ans += nowWord.length();
        }
        
        return ans;
    }
}