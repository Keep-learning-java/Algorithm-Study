class Solution {
    public int numSteps(String s) {
        int ans = 0;
        
        while(s.length() > 1){
            
            if(s.charAt(s.length() - 1) == '1') {
                // ���� '0'�� ������ idx ã��
                int idx = s.lastIndexOf('0');
                
                String zeroStr = "";
                
                if(idx < 0){
                    for(int i=0; i<s.length(); i++){
                        zeroStr += '0';
                    }
                    s = '1' + zeroStr;
                } else {
                    // [length - idx - 1]�� ��ŭ '0'�����
                    for(int i=idx + 1; i<s.length(); i++){
                        zeroStr += '0';
                    }
                    s = s.substring(0, idx) + Character.toString('1') + zeroStr;
                }
            } else {
                // �ڿ��� �߶󳻱�
                s = s.substring(0, s.length() - 1);
            }
            ans++;
        }
        
        return ans;
    }
}