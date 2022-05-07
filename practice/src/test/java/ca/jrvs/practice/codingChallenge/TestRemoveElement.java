package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static java.util.Arrays.sort;

public class TestRemoveElement {
    @Test
    public void removeElement() {
        RemoveElement RE = new RemoveElement();
        int[] nums = {1, 2, 2, 1};
        int val = 1;
        int[] expectedNums = {2, 2};
        int k = RE.twoPointerRemove(nums, val);
        System.out.println(k);
        assert k == expectedNums.length;
        sort(nums, 0, k);
        for (int i = 0; i < expectedNums.length; i++) {
            assert nums[i] == expectedNums[i];
        }
    }
}
