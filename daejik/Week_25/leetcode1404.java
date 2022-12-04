class Solution {
    public int numSteps(String s) {
        int ans = 0;
        
        while(s.length() > 1){
            
            if(s.charAt(s.length() - 1) == '1') {
                // 다음 '0'을 만나는 idx 찾기
                int idx = s.lastIndexOf('0');
                
                String zeroStr = "";
                
                if(idx < 0){
                    for(int i=0; i<s.length(); i++){
                        zeroStr += '0';
                    }
                    s = '1' + zeroStr;
                } else {
                    // [length - idx - 1]개 만큼 '0'만들기
                    for(int i=idx + 1; i<s.length(); i++){
                        zeroStr += '0';
                    }
                    s = s.substring(0, idx) + Character.toString('1') + zeroStr;
                }
            } else {
                // 뒤에꺼 잘라내기
                s = s.substring(0, s.length() - 1);
            }
            ans++;
        }
        
        return ans;
    }
}