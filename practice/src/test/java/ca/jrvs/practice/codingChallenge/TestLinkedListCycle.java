package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.LinkedListNode;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestLinkedListCycle {
    @Test
    public void testListCycle() {
        LinkedListCycle cycle = new LinkedListCycle();
        LinkedListNode head = new LinkedListNode(1);
        LinkedListNode node = new LinkedListNode(2);
        head.next = node;
        node.next = new LinkedListNode(3);
        node.next.next = new LinkedListNode(4);
        node.next.next.next = new LinkedListNode(5);
        node.next.next.next.next = head;
        assertTrue(cycle.listCycle(head));

        node.next.next.next.next = null;
        assertFalse(cycle.listCycle(head));
    }
}
