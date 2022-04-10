package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestContainsDuplicate {
    @Test
    public void testContainsDupes(){
        ContainsDuplicate CD = new ContainsDuplicate();
        assertTrue(CD.set(new int[] {1,2,3,4,1}));
        assertFalse(CD.set(new int[] {1,2,3}));
        assertTrue(CD.loops(new int[] {1,2,3,4,1}));
        assertFalse(CD.loops(new int[] {1,2,3,4}));
    }
}
