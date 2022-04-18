package ca.jrvs.practice.codingChallenge;

/**
 * Ticket: https://www.notion.so/jarvisdev/Swap-two-numbers-dd8a3941a5ad4cb5a8cd1d5b2b496d51
 */

public class SwapNumbers {
    /**
     * Swap two numbers in an array using bit manipulation
     * Big O: O(1)
     * Justification: Uses XOR operation to operate on bit values and providing original values after two operations
     * @param arr
     * @return
     */
    public int[] swapNumbersBits(int[] arr){
        arr[0] = arr[0]^arr[1];
        arr[1] = arr[0]^arr[1];
        arr[0] = arr[0]^arr[1];
        return arr;
    }

    /**
     * Swap two numbers in an array using arithmetic operation
     * Big O:O(1)
     * Justification: Use the sum of both values to subtract the other value and set it to the opposite variable
     * @param arr
     * @return
     */
    public int[] swapNumbersArith(int[] arr){
        arr[0] = arr[0]+arr[1];
        arr[1] = arr[0]-arr[1];
        arr[0] = arr[0]-arr[1];
        return arr;
    }
}
