package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

public class TestMergeSortedArray {
    @Test
    public void testMergeSortedArray(){
        MergeSortedArray test = new MergeSortedArray();
        int[] nums1 = {1,2,3,5,5,7,0,0,0};
        int[] nums2 = {2,6,10};
        test.mergeArray(nums1,6,nums2,3);
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i]+" ");
        }
    }
}
