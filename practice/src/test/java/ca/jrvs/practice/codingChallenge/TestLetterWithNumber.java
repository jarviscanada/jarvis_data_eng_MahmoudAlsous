package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestLetterWithNumber {
    @Test
    public void testPrintLetter(){
        LetterWithNumber testObj = new LetterWithNumber();
        assertEquals(true,testObj.printLetterNumber("abcee").equals("a1b2c3e5e5"));
        assertEquals(true,testObj.printLetterNumber("AAA").equals("A27A27A27"));
    }
}
