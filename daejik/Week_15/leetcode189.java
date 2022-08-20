class Solution {
    int[] originArray;
    
    public void rotate(int[] nums, int k) {
        k %= nums.length;   //numsw.length��ŭ ���� 1������ ��ⷯ�� resize
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