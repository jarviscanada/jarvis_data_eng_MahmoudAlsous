package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.LinkedListNode;

/**
 * Ticket: https://www.notion.so/jarvisdev/Middle-of-the-Linked-List-c67a83a68cde425ab910c62abad3f5b0
 */

public class MiddleOfList {
    /**
     * Iterates to the middle of te linked list and returns that middle node
     * Big O: O(n)
     * Justification: Uses two pointers where one reaches the end of the list while the other reaches the middle simultaneously
     * @param head
     * @return
     */
    public LinkedListNode middleNode(LinkedListNode head){
        LinkedListNode slow=head;
        LinkedListNode fast=head;
        while(fast != null && fast.next != null) {
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
}
