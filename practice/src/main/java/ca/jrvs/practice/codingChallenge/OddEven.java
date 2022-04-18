package ca.jrvs.practice.codingChallenge;

/**
 * Ticket: https://www.notion.so/jarvisdev/Sample-Check-if-a-number-is-even-or-odd-23aefee52c2b43d8afdd53f9205fd88f
 */
public class OddEven {

    /**
     * Using modulo operation
     * Big-O: O(1)
     * Justification: it's an arithmetic operation
     */
    public String oddEvenMod(int i){
        return i % 2 == 0 ? "even" : "odd";
    }

    /**
     * With bit operation
     * Big-O: O(1)
     * Justification: Comparison of rightmost bit
     */
    public String oddEvenBit(int i){
        return (i & 1) == 0 ? "even" : "odd";
    }
}