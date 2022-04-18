package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Ticket: https://www.notion.so/jarvisdev/Duplicate-LinkedList-Node-6d01598af24e41ee83bfacb4c11ead88
 */

public class DuplicateLinkedListNode {
    /**
     * Remove duplicates from a linked list using a hashset to keep unique node values.
     * Big O: O(n)
     * Justification: Iterate through the given linked list and add to result list if it is unique.
     * @param inputList
     * @return
     */
    public LinkedList<Integer> removeDups(LinkedList<Integer> inputList){
        HashSet<Integer> HS = new HashSet<Integer>();
        LinkedList<Integer> result = new LinkedList<Integer>();

        inputList.forEach(n -> {
            if(!HS.contains(n)){
                HS.add(n);
                result.add(n);
            }
        });
        return result;
    }

}
