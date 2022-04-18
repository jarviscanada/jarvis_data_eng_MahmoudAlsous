package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.LinkedListNode;

/**
 * Ticket: https://www.notion.so/jarvisdev/Nth-Node-From-End-of-LinkedList-32ad2236db3043498d1dbafcb9a25d3f
 */

public class NthNodeFromEnd {
    /**
     * Remove the Nth node from the end of the list
     * Big O: O(n)
     * Justification: Use two pointers to reach the end of the list and the Nth node at the same time and them remove the nth node.
     * @param head
     * @param N
     * @return
     */
    public LinkedListNode removeNthNodeFromEnd(LinkedListNode head, int N){
        LinkedListNode fast = head, slow = head;
        for (int i = 0; i < N; i++) {
            fast = fast.next;
        }
        if(fast == null){
            return head.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
