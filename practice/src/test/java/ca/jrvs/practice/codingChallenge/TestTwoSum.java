package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TestTwoSum {
    @Test
    public void twoSumTest(){
        TwoSum tS = new TwoSum();
        assertArrayEquals(new int[] {1,3}, (tS.twoSum(new int[] {4,6,2,7}, 13)));
        assertArrayEquals(new int[] {2,3}, (tS.twoSum(new int[] {4,6,2,7}, 9)));
    }

    @Test
    public void twoSumHashTest(){
        TwoSum tS = new TwoSum();
        assertArrayEquals(new int[] {1,3}, (tS.twoSumHash(new int[] {4,6,2,7}, 13)));
        assertArrayEquals(new int[] {2,3}, (tS.twoSumHash(new int[] {4,6,2,7}, 9)));
    }
}
