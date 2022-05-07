package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TestSwapNumbers {
    @Test
    public void testNumberSwap(){
        SwapNumbers SN = new SwapNumbers();
        assertArrayEquals(new int[]{3,2}, SN.swapNumbersArith(new int[]{2,3}) );
        assertArrayEquals(new int[]{3,2}, SN.swapNumbersBits(new int[]{2,3}) );
    }
}
