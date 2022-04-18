package ca.jrvs.practice.codingChallenge;

import java.util.regex.Pattern;

/**
 * Ticket: https://www.notion.so/jarvisdev/Check-if-a-String-contains-only-digits-d8fbefe8470146c08abfcde733a2fd71
 */

public class IsOnlyDigits {
    /**
     * Check if string is only digits or not
     * Big O: O(n)
     * Justification: Check each character in the string to see if it is a digit in ASCII
     * @param str
     * @return
     */
    public boolean ascii(String str){
        char C[]=str.toCharArray();
        for(char c:C){
            int diff=c-0;
            if(c<48||c>57) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if string is digits only
     * Big O: O(1)
     * Justification: Uses Java API method to obtain the the integer value of the string and check for a format exception
     * @param str
     * @return
     */
    public boolean javaAPI(String str){
        try{
            Integer integer=Integer.valueOf(str);
        }
        catch(NumberFormatException e){
            return false;
        }
        return true;
    }

    /**
     * Check if string is digits only
     * Big O: O(1)
     * Justification: Uses regex to match the string with digits
     * @param str
     * @return
     */
    public boolean regex(String str){
        return Pattern.matches("\\d+",str);
    }
}
