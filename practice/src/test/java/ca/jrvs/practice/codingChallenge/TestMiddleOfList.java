package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.LinkedListNode;
import org.junit.Test;

public class TestMiddleOfList {
    @Test
    public void testMiddleOfList() {
        MiddleOfList mid = new MiddleOfList();
        LinkedListNode headNode = new LinkedListNode(1);
        headNode.next = new LinkedListNode(2);
        headNode.next.next = new LinkedListNode(3);
        headNode.next.next.next = new LinkedListNode(4);
        headNode.next.next.next.next = new LinkedListNode(5);
        headNode.next.next.next.next.next = new LinkedListNode(6);
        LinkedListNode MN = mid.middleNode(headNode);
        System.out.println(MN.value);
    }
}
