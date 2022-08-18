class Solution {
public:
    vector<int> dailyTemperatures(vector<int>& temperatures) {
        int size = temperatures.size();
        vector<int> answer(size, 0);
        stack<int> stk;
        vector<int>& T = temperatures;
        for (int i = size - 1; i >= 0; i--) {
            while (!stk.empty() && T[stk.top()] <= T[i]) stk.pop();
            if (!stk.empty()) answer[i] = stk.top() - i;
            stk.push(i);
        }
        return answer;
    }
};