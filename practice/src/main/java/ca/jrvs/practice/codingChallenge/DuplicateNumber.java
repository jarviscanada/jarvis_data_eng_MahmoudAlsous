package ca.jrvs.practice.codingChallenge;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Ticket: https://www.notion.so/jarvisdev/Find-the-Duplicate-Number-b04a4cf294f14b96a75478d67eb63cc2
 */

public class DuplicateNumber {
    /**
     * Use a counter array that holds the count of every element, where the duplicate number will have a count value of more than 1.
     */
    public int duplicateSort(int[] nums){
        int[] count = new int[nums.length + 1];
        for(int i = 0; i < count.length;i++){
            count[i] = 0;
        }
        for(int i = 0; i < nums.length; i++){
            count[nums[i]]++;
        }
        for(int i = 1; i < count.length; i++){
            if(count[i] > 1){
                return i;
            }
        }
        return -1;
    }

    public int duplicateSet(int[] nums){
        Set<Integer> uniqueInts = new LinkedHashSet<>();
        for(int num:nums){
            if(!uniqueInts.add(num))
                return num;
        }
        return 0;
    }
}
