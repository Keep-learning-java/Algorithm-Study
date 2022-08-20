class Solution {
    int[] originArray;
    
    public void rotate(int[] nums, int k) {
        k %= nums.length;   //numsw.length만큼 돌면 1바퀴라 모듈러로 resize
        originArray = nums.clone();
        int fromIndex = 0;
        int toIndex = k;
        
        while(fromIndex < nums.length){
            nums[toIndex++] = originArray[fromIndex++];
            
            if(toIndex >= nums.length){
                toIndex = 0;
            }
        }
    }
}