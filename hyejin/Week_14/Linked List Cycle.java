/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> history = new HashSet<>();

        if(head == null)
            return false;
        
        ListNode curNode = head;
		
        while(curNode.next != null) {
			ListNode next = curNode.next;

			if(history.contains(next))
				return true;
            else {
				history.add(next);
                curNode = next;                
            }
		}
		return false;   
    }
}