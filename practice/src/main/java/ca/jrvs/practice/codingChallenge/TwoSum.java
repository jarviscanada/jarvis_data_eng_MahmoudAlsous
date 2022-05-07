package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;

/**
 * Ticket: https://www.notion.so/jarvisdev/Two-Sum-01efd3ef7e044b8d9b83240642fb5bec
 */
public class TwoSum {
    /**
     * Brute Force Implementation for TwoSum
     * Big-O: O(n^2)
     * Justification: Iterate through the array and add each element with every other element in the array
     * @param nums Searched array
     * @param target Sum of the target pair
     * @return Array of the indices of the two values that's sum equals to target
     */
    public int[] twoSum(int[] nums, int target) {
        int result[] = new int[2];
        for(int i = 0; i < nums.length - 1; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * Implementing TwoSum using a Hashmap
     * Big-O: O(n)
     * Justification: Iterate through the array once and store the elements of that array into the hashmap
     * @param nums Searched array
     * @param target Sum of the target pair
     * @return Array of the indices of the two values that's sum equals to target
     */
    public int[] twoSumHash(int[] nums, int target) {
        int result[] = new int[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(nums[0], 0);
        for(int i = 1; i < nums.length; i++) {
            int val = target - nums[i];
            if(map.containsKey(val)) {
                result[0] = map.get(val);
                result[1] = i;
                return result;
            } else {
                map.put(nums[i], i);
            }
        }
        return result;
    }
}
