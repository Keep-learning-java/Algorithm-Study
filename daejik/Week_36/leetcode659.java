class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> ready = new HashMap<>();
        Map<Integer, Integer> end = new HashMap<>();

        for(int num : nums){
            ready.put(num, ready.getOrDefault(num, 0) + 1);
        }

        for(int num : nums){
            if(ready.get(num) == 0) continue;
            ready.put(num, ready.get(num) - 1);

            if(end.containsKey(num - 1) && end.get(num - 1) > 0){
                end.put(num - 1, end.get(num - 1) - 1);
                end.put(num, end.getOrDefault(num, 0) + 1);
            } else if(ready.containsKey(num + 1) && ready.containsKey(num + 2)
                        && ready.get(num + 1) > 0 && ready.get(num + 2) > 0){
                ready.put(num + 1, ready.get(num + 1) - 1);
                ready.put(num + 2, ready.get(num + 2) - 1);
                end.put(num + 2, end.getOrDefault(num + 2, 0) + 1);
            } else {
                return false;
            }
        }
        return true;
    }
}