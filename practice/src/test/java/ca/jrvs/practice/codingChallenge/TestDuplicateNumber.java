package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDuplicateNumber {
    @Test
    public void testDupNum(){
        DuplicateNumber DN = new DuplicateNumber();
        assertEquals(3, DN.duplicateSet(new int[]{3,1,3,4,2}));
        assertEquals(2, DN.duplicateSet(new int[]{1,3,2,4,2}));
        assertEquals(3, DN.duplicateSort(new int[]{3,1,3,4,2}));
        assertEquals(2, DN.duplicateSort(new int[]{1,3,2,4,2}));
    }
}
