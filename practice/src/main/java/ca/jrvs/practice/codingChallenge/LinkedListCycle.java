package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.LinkedListNode;

/**
 * Ticket: https://www.notion.so/jarvisdev/LinkedList-Cycle-8d6843de054e495392e85a428f2b1fff
 */

public class LinkedListCycle {
    /**
     * Determine if a linked list contains a loop cycle or not
     * Big O: O(n)
     * Justification: Use two pointers to traverse the list at different speeds and check if they meet on the same node
     * @param head
     * @return
     */
    public boolean listCycle(LinkedListNode head) {
        if (head==null) return false;

        LinkedListNode slow=head;
        LinkedListNode fast=head.next;

        while(slow!=fast){
            if(fast == null || fast.next == null) {
                return false;
            }

            slow=slow.next;
            fast=fast.next.next;
        }
        return true;
    }
}

