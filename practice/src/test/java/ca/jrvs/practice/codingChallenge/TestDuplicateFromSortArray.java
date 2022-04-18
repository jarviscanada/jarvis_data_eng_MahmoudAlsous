package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDuplicateFromSortArray {
    @Test
    public void testDuplicateFromSorted(){
        DuplicateFromSortArray test =new DuplicateFromSortArray();
        int[] nums = {2, 3, 3, 4};
        assertEquals(3, test.removeDuplicates(nums));
        for (int i = 0; i < 3; i++) {
            System.out.print(nums[i]+ " ");
        }

        nums = new int[] {1,2,2,3,3};
        assertEquals(3, test.removeDuplicates(nums));
        for (int i = 0; i < 3; i++) {
            System.out.print(nums[i]+ " ");
        }

        nums = new int[] {1,6,9};
        assertEquals(3, test.removeDuplicates(nums));
        for (int i = 0; i < 3; i++) {
            System.out.print(nums[i]+ " ");
        }
    }
}
