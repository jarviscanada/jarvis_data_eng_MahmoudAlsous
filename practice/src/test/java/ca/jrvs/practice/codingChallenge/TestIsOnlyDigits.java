package ca.jrvs.practice.codingChallenge;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestIsOnlyDigits {
    @Test
    public void testStringNumerics(){
        String num = "12345";
        String notNum = "143Afsa";
        IsOnlyDigits OD = new IsOnlyDigits();
        assertTrue(OD.ascii(num));
        assertTrue(OD.javaAPI(num));
        assertTrue(OD.regex(num));

        assertFalse(OD.ascii(notNum));
        assertFalse(OD.javaAPI(notNum));
        assertFalse(OD.regex(notNum));
    }
}
