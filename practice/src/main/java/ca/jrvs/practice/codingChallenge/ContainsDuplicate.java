package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;
import java.util.Set;

/**
 * Ticket: https://www.notion.so/jarvisdev/Contains-Duplicate-e011925b89bf438b94e6373075e18044
 */

public class ContainsDuplicate {
    public boolean loops(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            for(int j = i+1; j<nums.length; j++){
                if(nums[i] == nums[j])
                    return true;
            }
        }
        return false;
    }

    public boolean set(int[] nums) {
        Set uniques = new HashSet<>();
        for (int num:nums) {
            if(!uniques.add(num)){
                return true;
            }
        }
        return false;
    }
}
