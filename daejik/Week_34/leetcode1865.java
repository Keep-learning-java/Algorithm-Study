class FindSumPairs {
    int[] nums1, nums2;
    Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // key: num2원소, value: key값의 개수

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1.clone();
        this.nums2 = nums2.clone();

        for(int num2 : nums2){
            map.put(num2, map.getOrDefault(num2, 0) + 1);
        }
    }
    
    public void add(int index, int val) {
        map.put(nums2[index], map.getOrDefault(nums2[index], 0) - 1);
        nums2[index] += val;
        map.put(nums2[index], map.getOrDefault(nums2[index], 0) + 1);
    }
    
    public int count(int tot) {
        int cnt = 0;
        
        for(int num1 : nums1){
            if(map.containsKey(tot - num1)){
                cnt += map.get(tot - num1);
            }
        }
        return cnt;
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */