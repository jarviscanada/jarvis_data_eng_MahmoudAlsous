package ca.jrvs.practice.codingChallenge;

import java.util.Locale;

/**
 * Ticket: https://www.notion.so/jarvisdev/Valid-Palindrome-814a2eaab4424a7da73d5eada9e97442
 */

public class ValidPalindrome {
    /**
     * Check if string is a palindrome using two pointers
     * Big O: O(n)
     * Justification: Iterate through string by having one pointer at the start of the string and one at the end
     * @param string
     * @return true if palindrome, false otherwise
     */
    public boolean isPalindromeTwoP(String string){
        if (string.equals(null)) {
            return false;
        }
        if (string.length() <= 1) {
            return true;
        }

        string = string.toLowerCase(Locale.ROOT);
        int start = 0;
        int end = string.length() - 1;

        while (start < end){
            while (!Character.isLetterOrDigit(string.charAt(start))){
                start++;
            }
            while (!Character.isLetterOrDigit(string.charAt(end))){
                end--;
            }
            if (string.charAt(start) == string.charAt(end)){
                start++;
                end--;
            }
            else {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if a string is a palindrome using Recursion approach
     * Big O: O(n)
     * Justification: Recursively iteration through the string char by char until the base condition is met
     * @param string
     * @return
     */
    public boolean isPalindromeRec(String string){
        if(string.equals(null)){
            return false;
        }
        if(string.length()<=1){
            return true;
        }
        string = string.toLowerCase(Locale.ROOT);
        return secIsPalindromeRec(string, 0, string.length() - 1);
    }

    private boolean secIsPalindromeRec(String string, int start, int end){
        //Base condition
        if(start >= end){
            return true;
        }
        if(!Character.isLetterOrDigit(string.charAt(start))){
            return secIsPalindromeRec(string, start+1, end);
        }
        if(!Character.isLetterOrDigit(string.charAt(end))){
            return secIsPalindromeRec(string, start, end - 1);
        }

        if(string.charAt(start) == string.charAt(end)){
            return secIsPalindromeRec(string, start+1, end-1);
        }
        else {
            return false;
        }
    }
}
