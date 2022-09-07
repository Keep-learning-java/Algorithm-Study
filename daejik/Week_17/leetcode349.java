class Solution {
    
    HashSet <Integer> set = new HashSet<Integer>();
    
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1.length < nums2.length){
            for(int i=0; i<nums2.length; i++){
                set.add(nums2[i]);
            }
            
            return findInterSection(nums1);
        } else {
            for(int i=0; i<nums1.length; i++){
                set.add(nums1[i]);
            }
            
            return findInterSection(nums2);
        }
    }
    
    public int[] findInterSection(int[] nums){
        int[] arr = new int[nums.length];
        int idx = 0;
        
        for(int i=0; i<nums.length; i++){
            if(set.remove(nums[i])){
                arr[idx++] = nums[i];
            }
        }
        
        return Arrays.copyOf(arr, idx);
    }
}