package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

/**
 * https://www.notion.so/jarvisdev/Valid-Parentheses-70b67d0d438344ce82c441d1eeee1dc8
 */

public class ValidParentheses {

    /**
     * Validate string for parentheses
     * Big O: O(n)
     * Justification: Use a stack as a linkedlist to push the opening valid parentheses and then pop the opening parentheses if closing one is valid
     * @param string string input
     * @return true or false
     */
    public boolean isValid(String string) {
        Stack<Character> stack = new Stack<>();
        for (char c : string.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            else if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else if (c == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
            } else if (c == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            }
            else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
