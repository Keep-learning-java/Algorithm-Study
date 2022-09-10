class Solution {
public:
    int triangleNumber(vector<int>& nums) {
        int N = nums.size();
        sort(nums.begin() , nums.end());
        int ans = 0;
        for(int i = N - 1; i > 0; i--) {
            for (int j = i - 1; j > 0; j--) {
                int diff = nums[i] - nums[j];
                int idx = upper_bound(nums.begin(), nums.end(), diff) - nums.begin();
                ans += max(0, j - idx);
            }
        }      
        return ans;
    }
};