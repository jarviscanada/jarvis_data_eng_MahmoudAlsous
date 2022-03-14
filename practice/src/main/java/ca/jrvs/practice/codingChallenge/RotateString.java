package ca.jrvs.practice.codingChallenge;

public class RotateString {
    public boolean rotateString(String string1, String string2){
        return (string1.length() == string2.length() && (string1 + string1).contains(string2));
    }
}
