class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
		boolean[] checkExistingNumber1 = new boolean[1001];
		boolean[] checkExistingNumber2 = new boolean[1001];
		
		for (int i = 0; i < nums1.length; i++) {
			checkExistingNumber1[nums1[i]] = true;
		}

		for (int i = 0; i < nums2.length; i++) {
			checkExistingNumber2[nums2[i]] = true;
		}
		
		HashSet<Integer> set = new HashSet<>();
		for(int i=0; i<checkExistingNumber1.length; i++) {
			if(checkExistingNumber1[i] && checkExistingNumber2[i])  {
				set.add(i);
			}
		}
		return set.stream().mapToInt(i -> i).toArray();
    }
}