class Solution {
    int N;
    int count;
    vector<int> v;
    vector<vector<int>> combinations;
public:
    void makeCombination(int start, int depth) {
        if (depth == count) {
            combinations.push_back(v);
            return;
        }
        for (int i = start; i <= N; i++) {
            v.push_back(i);
            makeCombination(i + 1, depth + 1);
            v.pop_back();
        }
    }
    vector<vector<int>> combine(int n, int k) {
        N = n;
        count = k;
        makeCombination(1, 0);
        return combinations;
    }
};