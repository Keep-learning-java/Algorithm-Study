package Week9;


class Solution{
    
	public int maxDistance(int[] nums1, int[] nums2) {
    	int maxDist = 0;
    	int nums1Length = nums1.length;
    	int nums2Length = nums2.length;
    	
    	for(int i=0; i<nums1Length && i < nums2Length && maxDist <= (nums2Length - i); i++) {
    		int tmpDist = 0;
    		
    		for(int j=i; j<nums2Length; j++) {
    			if(nums1[i] <= nums2[j]) tmpDist++;
    			else break;
    		}
    		
    		if(tmpDist > maxDist) maxDist = tmpDist;
    	}
        if(maxDist > 0) return maxDist - 1;
        return 0;
    }
}
