package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.LinkedListNode;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TestReverseLinkedList {
    @Test
    public void testReverseLinkedList(){
        ReverseLinkedList test = new ReverseLinkedList();
        LinkedListNode head = new LinkedListNode(1);
        head.next = new LinkedListNode(2);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(4);

        LinkedListNode ans = test.reverseIterative(head);
        assertNotNull(ans);

        while(ans != null){
            System.out.print(ans.value + "->");
            ans = ans.next;
        }
        System.out.println();

        head = new LinkedListNode(1);
        head.next = new LinkedListNode(2);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(4);

        ans = test.reverseRecursive(head);
        assertNotNull(ans);

        while(ans != null){
            System.out.print(ans.value + "->");
            ans = ans.next;
        }
    }
}
