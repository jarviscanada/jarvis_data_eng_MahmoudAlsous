package ca.jrvs.practice.codingChallenge;

/**
 * Ticket: https://www.notion.so/jarvisdev/Rotate-String-2e1b55ad4d904f4ab77c7888c631c6e2
 */
public class RotateString {
    /**
     * Compare two strings for rotation using String.contains
     * Big O: O(n^2)
     * Justification: Rotations are all included when you concatenate string1 with itself. Therefore, check if the length of both strings are equal and
     * if string2 is a substring of the concatenated string.
     * @param string1 original string
     * @param string2 rotated string
     * @return true or false
     */
    public boolean rotateString(String string1, String string2){
        return (string1.length() == string2.length() && (string1 + string1).contains(string2));
    }
}
