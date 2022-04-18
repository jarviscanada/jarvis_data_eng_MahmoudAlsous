package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Test;

public class TestValidAnagram {
    @Test
    public void testValidAnagramSort() {
        ValidAnagram VA = new ValidAnagram();
        Assert.assertTrue(VA.validAnagramSort("", ""));
        Assert.assertTrue(VA.validAnagramSort("anagram", "nagaram"));
        Assert.assertFalse(VA.validAnagramSort("", "car"));
        Assert.assertFalse(VA.validAnagramSort("rat", "car"));
    }

    @Test
    public void testValidAnagramSeq() {
        ValidAnagram VA = new ValidAnagram();
        Assert.assertTrue(VA.validAnagramSeq("", ""));
        Assert.assertTrue(VA.validAnagramSeq("anagram", "nagaram"));
        Assert.assertFalse(VA.validAnagramSeq("", "car"));
        Assert.assertFalse(VA.validAnagramSeq("rat", "car"));
    }
}
