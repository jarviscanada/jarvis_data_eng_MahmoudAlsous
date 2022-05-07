package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

/**
 * Ticket: https://www.notion.so/jarvisdev/Find-Largest-Smallest-06259141b52342148131955fad68bb5b
 */

public class LargestAndSmallest {
    /**
     * Find largest and smallest value with ine for loop
     * Big O: O(n)
     * Justification: Use one for loop to iterate through the array and compare each element with the current smalles and largest value
     * @param array
     * @return
     */
    public int[] oneLoop(int array[]){
        int smallest=array[0];
        int largest=array[0];
        int[] result = new int[2];
        for(int num:array){
            if(num<smallest) {
                smallest=num;
            }
            if(num>largest)  {
                largest=num;
            }
        }
        result[0] = largest;
        result[1] = smallest;
        return result;
    }

    /**
     * Find largest and smallest value using Stream API
     * Big O: O(1)
     * Justification: Use stream api to determine the max() and min() values as ints.
     * @param array
     */
    public int[] stream(int array[]){
        int largest= Arrays.stream(array).max().getAsInt();
        int smallest=Arrays.stream(array).min().getAsInt();

        int[] result = {largest, smallest};
        return result;
    }

    /**
     * Find largest and smallest value using JAVA API
     * Big O: O(1)
     * Justification: Uses sort() method to sort array elements from smallest to largest
     * @param array
     */
    public int[] javaAPI(int array[]){
        Arrays.sort(array);
        int largest=array[array.length-1];
        int smallest=array[0];

        int[] result = {largest, smallest};
        return result;
    }
}
