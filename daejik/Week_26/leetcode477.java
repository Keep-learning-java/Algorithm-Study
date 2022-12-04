class Solution {
    public int totalHammingDistance(int[] nums) {
        int n = nums.length;
        int ans = 0;
        
        for(int i=0; i<32; i++){
            int one = 0;
            int zero = 0;
            
            
            for(int num : nums){
                if(((num >> i) & 1) == 1) one++;
                else zero++;
            }
            
            ans += one * zero;
        }
        
        return ans;
    }
}