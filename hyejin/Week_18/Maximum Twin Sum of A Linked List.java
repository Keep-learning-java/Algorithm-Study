/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int pairSum(ListNode head) {
        int index = 0;
		int[] values = new int[100001];
		ListNode curNode = head;

		while (curNode != null) {
			values[index++] = curNode.val;
			curNode = curNode.next;
		}

		// index == size of ListNode
		int maxSum = 0;
		// twin인 두 인덱스의 합은 index-1 일 것이에요
		for (int i = 0; i <= (index - 1) / 2; i++) {
			int twin1 = i;
			int twin2 = index-1-i;
			
			maxSum = Math.max(maxSum, values[twin1]+values[twin2]);
		}
		
		return maxSum; 
    }
}