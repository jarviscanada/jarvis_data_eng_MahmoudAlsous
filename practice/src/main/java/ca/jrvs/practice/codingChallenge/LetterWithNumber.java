package ca.jrvs.practice.codingChallenge;

/**
 * Ticket: https://www.notion.so/jarvisdev/Print-letter-with-number-3d98b6b7c45f42fc9c246c9ac05cef7d
 */

public class LetterWithNumber {
    /**
     * Print the index of the letter in the string given
     * Big O: O(n)
     * Justification: Iterate through the char array and determine if upper or lower string, concatenate char with its index
     * @param string
     * @return
     */
    public String printLetterNumber(String string){
        String result = "";
        for (char character:string.toCharArray()) {
            int charASCII = character;
            int index = 0;

            if(character > 64 && character < 91){
                index = character - 64 + 26;
            }

            else if (character > 96 && character < 123){
                index = character - 96;
            }

            result += character;
            result += index;
        }
        return result;
    }
}
