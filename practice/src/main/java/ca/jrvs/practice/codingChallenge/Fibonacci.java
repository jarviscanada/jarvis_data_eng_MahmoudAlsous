package ca.jrvs.practice.codingChallenge;

/**
 * Ticket: https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-6140befbc4d4453f866ed6544ba5e353
 */
public class Fibonacci {
    /**
     * Fibonacci implemented with recursion
     * Big-O: O(2^n)
     * Justification: Each fibonacci calculation branches into 2 until the base condition is met
     * @param n input number
     * @return result in sequence
     */
    public int fibRec(int n) {
        if ((n == 0) || (n == 1)) {
            return n;
        }
        else {
            return fibRec(n - 1) + fibRec(n - 2);
        }
    }

    /**
     * Fibonacci implemented with dynamic programming (Bottom-Up)
     * Big-O: O(n)
     * Justification: Build an array of fibonacci values as the function calculates the value until the value is reached in the array
     * @param n input number (sequence)
     * @return result in sequence
     */
    public int fiboBottomUp(int n){
        if(n == 1 || n == 2) {
            return 1;
        }
        int fibArr[] = new int[n + 1];
        fibArr[1] = 1;
        fibArr[2] = 1;
        for(int i = 3 ; i <= n; i++){
            fibArr[i] = fibArr[i-1] + fibArr[i-2];
        }
        return fibArr[n];
    }
}
