package ca.jrvs.practice.codingChallenge;

/**
 * Ticket: https://www.notion.so/jarvisdev/String-to-Integer-atoi-be3fc528d0484724b4c29d88b11cbaa3
 */

public class Atoi {

    /**
     * Converting a string to an integer with the built in java parse method
     * Big O: O(n)
     * Justification: Using the parseInt() method, it will iterate through each character and convert it to Int.
     * @param string is the string parameter
     * @return signed int
     */
    public int atoiBuiltIn(String string){
        try {
            return Integer.parseInt(string.trim());
        } catch (NumberFormatException e){
            if (string.trim().charAt(0) == '-'){
                return Integer.MIN_VALUE;
            }
            else{
                return Integer.MAX_VALUE;
            }
        }
    }

    /**
     * Converting string to integer without built in parse methods
     * Big O: O(n)
     * Justification: Iterate through each char, remove white spaces, obtain the sign, convert digits to int.
     * @param string
     * @return
     */
    public int atoiImp(String string){
        if (string == null || string.length() < 1)
            return 0;

        // trim white spaces
        string = string.trim();

        char sign = '+';

        // check negative or positive
        int i = 0;
        if (string.charAt(0) == '-') {
            sign = '-';
            i++;
        } else if (string.charAt(0) == '+') {
            i++;
        }
        // Store result
        double result = 0;

        // calculate value
        while (string.length() > i && string.charAt(i) >= '0' && string.charAt(i) <= '9') {
            result = result * 10 + (string.charAt(i) - '0');
            i++;
        }

        if (sign == '-')
            result = -result;

        // handling MAX and MIN values
        if (result > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;

        if (result < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        return (int) result;
    }
}
