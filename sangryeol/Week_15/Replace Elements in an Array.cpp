class Solution {
public:
    vector<int> arrayChange(vector<int>& nums, vector<vector<int>>& operations) {
        const int SZ = 1e6 + 10;
        vector<int> result = nums;
        int mapping[SZ]{};
        for (int i = 0; i < nums.size(); i++) {
            mapping[nums[i]] = i;
        }
        for (auto operation : operations) {
            int oldValue = operation[0];
            int newValue = operation[1];
            result[mapping[oldValue]] = newValue;
            mapping[newValue] = mapping[oldValue];
        }
        return result;
    }
};