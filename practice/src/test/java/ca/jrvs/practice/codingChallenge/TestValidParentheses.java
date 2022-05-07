package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestValidParentheses {
    @Test
    public void testIsValid(){
        ValidParentheses testObj = new ValidParentheses();
        assertEquals(false, testObj.isValid("("));
        assertEquals(false, testObj.isValid("(}"));
        assertEquals(false, testObj.isValid("(])"));
        assertEquals(true, testObj.isValid("{}"));
        assertEquals(true, testObj.isValid("{[]}()"));
        assertEquals(true, testObj.isValid("{{{}}}"));
        assertEquals(true, testObj.isValid("(){}[]"));
    }
}
