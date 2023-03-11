class FindSumPairs {
    
    int[] nums1;
    int[] nums2;
    HashMap<Integer, Integer> map; // nums2의 원소가 각각 몇개 있는지
    
    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        map = new HashMap<>();
        
        for (int num2 : nums2) {
            int cnt = map.getOrDefault(num2, 0);
            map.put(num2, ++cnt);
        }
    }
    
    public void add(int index, int val) {
        // map 갱신
        // oldVal--
        int oldCnt = map.get(nums2[index]);
        map.put(nums2[index], --oldCnt);
        
        nums2[index] += val;
        
        // newVal++
        int newCnt = map.getOrDefault(nums2[index], 0);
        map.put(nums2[index], ++newCnt);
    }
    
    public int count(int tot) {
        int cnt = 0;
        
        for (int num1 : nums1) {
            int need = tot - num1;
            
            // nums2가 need를 가지고 있는지?
            if (map.containsKey(need)) {
                cnt += map.get(need);
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