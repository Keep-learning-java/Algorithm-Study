class Solution {
public:
    bool hasCycle(ListNode *head) {
        ListNode* pCurr = head;
        ListNode* pNext = head;
        while (pNext && pNext->next) {
            pCurr = pCurr->next;
            pNext = pNext->next->next;
            if (pCurr == pNext) return true;
        }
        return false;
    }
};