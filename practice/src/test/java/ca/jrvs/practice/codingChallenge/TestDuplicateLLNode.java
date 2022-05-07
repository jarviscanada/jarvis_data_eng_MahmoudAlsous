package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertTrue;

public class TestDuplicateLLNode {
    @Test
    public void testRemoveDups(){
        DuplicateLinkedListNode testObj = new DuplicateLinkedListNode();
        LinkedList<Integer> testList1 = new LinkedList<Integer>();
        LinkedList<Integer> testList2 = new LinkedList<Integer>();

        testList1.add(1);
        testList1.add(2);
        testList1.add(1);
        testList1.add(3);
        testList1.add(4);
        testList1.add(5);

        testList2.add(1);
        testList2.add(2);
        testList2.add(3);
        testList2.add(4);
        testList2.add(5);

        assertTrue(testList2.equals(testObj.removeDups(testList1)));

    }
}
