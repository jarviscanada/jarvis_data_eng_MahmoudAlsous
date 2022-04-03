package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Ticket: https://www.notion.so/jarvisdev/Missing-Number-23dfca4779364b6eaf3d06fd1a8d9864
 */

public class MissingNumber {
    /**
     * Find missing number by summing all the elements and iteratively subtracting from the sum
     * Big O: O(n)
     * Justification: Use sum and subtract from it each element
     * @param input
     * @return
     */
    public int sum(int[] input){
        int sum = (input.length * (input.length+1)) / 2;
        for(int num : input) {
            sum -= num;
        }
        return sum;
    }

    /**
     * Find missing number using a set
     * Big O: O(n)
     * Justification: Use a set to iterate through the set and only add the number that is not already in the set
     * @param input
     * @return
     */
    public int set(int[] input){
        Set setStream = Arrays.stream(input).boxed().collect(Collectors.toSet());
        for(int i =0; i < input.length;i++){
            if(setStream.add(i)){
                return i;
            }
        }
        return 0;
    }

    /**
     * Find missing number by calculating the gauss sum and subtracting it from the sum of the array given
     * Big O: O(1)
     * Justification: Uses gauss sum to subtract the array sum from it
     * @param input
     * @return
     */
    public int gauss(int[] input){
        int gaussSum = (input.length * (input.length+1)) / 2;
        int arraySum = Arrays.stream(input).sum();
        return gaussSum-arraySum;
    }
}
