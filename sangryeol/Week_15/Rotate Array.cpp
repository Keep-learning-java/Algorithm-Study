class Solution {
public:
    void rotate(vector<int>& nums, int k) {
        vector<int> copyNums = nums;
        int N = nums.size();
        for (int i = 0; i < N; i++) {
            nums[(i + k) % N] = copyNums[i];
        }
        return;
    }
};