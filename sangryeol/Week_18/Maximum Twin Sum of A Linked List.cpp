class Solution {
public:
    int pairSum(ListNode* head) {
        vector<int> v;
        while (head != nullptr) {
            v.push_back(head->val);
            head = head->next;
        }
        int N = v.size();
        int ans = 0;
        for (int i = 0; i < N; i++) {
            int last = v[N - 1 - i];
            ans = max(ans, v[i] + last);
        }
        return ans;
    }
};