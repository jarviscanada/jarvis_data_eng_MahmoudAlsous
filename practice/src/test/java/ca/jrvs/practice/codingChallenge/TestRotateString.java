package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestRotateString {
    @Test
    public void testRotateString(){
        RotateString strObj = new RotateString();

        assertEquals(true, strObj.rotateString("ABCD", "CDAB"));
        assertEquals(true, strObj.rotateString("ABAD", "ADAB"));
        assertEquals(false, strObj.rotateString("ABCD", "ABDC"));
    }
}
