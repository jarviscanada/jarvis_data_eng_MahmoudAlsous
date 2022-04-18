package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.LinkedListNode;

/**
 * Ticket: https://www.notion.so/jarvisdev/Reverse-Linked-List-de5162d8f9db43d997f6223cfaa5a615
 */

public class ReverseLinkedList {
    public LinkedListNode reverseIterative(LinkedListNode head) {
        if (head == null) {
            return null;
        }
        LinkedListNode cur = head;
        LinkedListNode next = null;
        LinkedListNode prev = null;

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public LinkedListNode reverseRecursive(LinkedListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedListNode temp = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return temp;
    }
}
