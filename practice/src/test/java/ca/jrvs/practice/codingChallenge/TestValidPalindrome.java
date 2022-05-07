package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestValidPalindrome {
    @Test
    public void testIsPalindromeTwoP(){
        ValidPalindrome palindrome = new ValidPalindrome();

        assertTrue(palindrome.isPalindromeTwoP("Redivider"));
        assertTrue(palindrome.isPalindromeTwoP(""));
        assertTrue(palindrome.isPalindromeTwoP(" "));
        assertTrue(palindrome.isPalindromeTwoP("red rum, sir, is murder"));
        assertTrue(palindrome.isPalindromeTwoP("Eva, can I see bees in a cave?"));
        assertFalse(palindrome.isPalindromeTwoP("rotater"));
        assertFalse(palindrome.isPalindromeTwoP("ACE_ABE"));
    }

    @Test
    public void testIsPalindromeRec(){
        ValidPalindrome palindrome = new ValidPalindrome();

        assertTrue(palindrome.isPalindromeRec("Redivider"));
        assertTrue(palindrome.isPalindromeRec(""));
        assertTrue(palindrome.isPalindromeRec(" "));
        assertTrue(palindrome.isPalindromeRec("red rum, sir, is murder"));
        assertTrue(palindrome.isPalindromeRec("Eva, can I see bees in a cave?"));
        assertFalse(palindrome.isPalindromeRec("rotater"));
        assertFalse(palindrome.isPalindromeRec("ACE_ABE"));
    }
}
