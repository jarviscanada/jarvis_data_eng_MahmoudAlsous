package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Ticket: https://www.notion.so/jarvisdev/Duplicate-Characters-5bdcc55bc5674134a23205cdb0838548
 */

public class DuplicateCharacters {
    public char[] detectDup(String str){
        str = str.toLowerCase(Locale.ROOT);
        Set<Character> uniqueChars = new HashSet<>();
        Set<Character> dupeChars = new HashSet<>();

        for (char c:str.toCharArray()) {
            if( !(c == '\n' || c == ' ') && !uniqueChars.add(c)){
                dupeChars.add(c);
            }
        }
        char[] ans = new char[dupeChars.size()];
        int index = 0;
        for (char e:dupeChars) {
            ans[index] = e;
            index++;
        }
        return ans;
    }
}
