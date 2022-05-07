package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TestDuplicateCharacters {
    @Test
    public void testCharDups(){
        DuplicateCharacters dc = new DuplicateCharacters();
        assertArrayEquals(new char[]{'a','c'}, dc.detectDup("a black cat"));
    }
}
