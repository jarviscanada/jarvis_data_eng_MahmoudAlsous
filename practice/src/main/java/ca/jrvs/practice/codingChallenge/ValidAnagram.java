package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

/**
 * Ticket: https://www.notion.so/jarvisdev/Valid-Anagram-3c924b4a743b4369a7f7c1e33b1a7bdd
 */

public class ValidAnagram {
    /**
     * Validate if string is an Anagram or not using sorting
     * Justification: Create two new character arrays and sort them, them compare
     * Big o: O(nlogn)
     * @param s1
     * @param s2
     * @return true/false
     */
    public boolean validAnagramSort(String s1, String s2){
        if(s1.equals(null) || s2.equals(null)){
            return false;
        }
        if(s1.length() != s2.length()){
            return false;
        }

        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        Arrays.sort(chars1);
        Arrays.sort(chars2);
        return Arrays.equals(chars1, chars2);
    }

    /**
     * Validate if a string is an Anagram or not using a counting array.
     * Big O: O(n)
     * Justification: Sequentially increment the created array
     * @param s1
     * @param s2
     * @return
     */
    public boolean validAnagramSeq(String s1, String s2){
        if(s1.equals(null) || s2.equals(null)){
            return false;
        }
        if(s1.length() != s2.length()){
            return false;
        }

        int arr[] = new int[26];

        for(int i = 0;i < s1.length();i++){
            arr[s1.charAt(i) - 'a']++;
            arr[s2.charAt(i) - 'a']--;
        }

        for(int i : arr){
            if(i != 0){
                return false;
            }
        }
        return true;
    }
}
