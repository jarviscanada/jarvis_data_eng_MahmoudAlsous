package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Ticket: https://www.notion.so/jarvisdev/Missing-Number-23dfca4779364b6eaf3d06fd1a8d9864
 */

public class TestMissingNumber {
    @Test
    public void testMissingNumbers(){
        MissingNumber MN = new MissingNumber();
        assertEquals(2, MN.sum(new int[]{3,0,1}));
        assertEquals(2, MN.set(new int[]{3,0,1}));
        assertEquals(2, MN.gauss(new int[]{3,0,1}));
        assertEquals(8, MN.sum(new int[]{9,6,4,2,3,5,7,0,1}));
        assertEquals(8, MN.set(new int[]{9,6,4,2,3,5,7,0,1}));
        assertEquals(8, MN.gauss(new int[]{9,6,4,2,3,5,7,0,1}));
    }
}
