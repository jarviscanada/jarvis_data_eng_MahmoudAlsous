package ca.jrvs.practice.codingChallenge;

/**
 * Ticket: https://www.notion.so/jarvisdev/Duplicates-from-Sorted-Array-2c9eeda5dc1746fb8f8da2560243cf3e
 */

public class DuplicateFromSortArray {
    /**
     * Removes duplicates from a sorted array and returns the new size of the array
     * Big O: O(n)
     * Justification: Use two pointers to traverse through the array and update the array after a duplicate is encountered
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums){
        if(nums.length == 1)
            return 1;

        int j = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                nums[j++] = nums[i];
            }
        }
        nums[j++] = nums[nums.length - 1];
        return j;
    }
}
