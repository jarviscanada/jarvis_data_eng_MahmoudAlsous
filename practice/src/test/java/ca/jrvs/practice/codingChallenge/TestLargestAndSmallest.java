package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TestLargestAndSmallest {
    @Test
    public void testLargestAndSmallest(){
        LargestAndSmallest LS = new LargestAndSmallest();
        assertArrayEquals(new int[]{15,5}, LS.oneLoop(new int[] {5,6,7,12,9,15}));
        assertArrayEquals(new int[]{15,5}, LS.stream(new int[] {5,6,7,12,9,15}));
        assertArrayEquals(new int[]{15,5}, LS.javaAPI(new int[] {5,6,7,12,9,15}));
    }
}
