package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Test;

public class TestStackWithQueue {

    StackWithQueue.StackWithTwoQ stack2Q = new StackWithQueue().stack2Q;
    StackWithQueue.StackWithOneQ stack1Q = new StackWithQueue().stack1Q;

    @Test
    public void push() {
        Assert.assertTrue(stack2Q.empty());
        stack2Q.push(1);
        Assert.assertFalse(stack2Q.empty());
        Assert.assertEquals(1, stack2Q.top());

        Assert.assertTrue(stack1Q.empty());
        stack1Q.push(1);
        Assert.assertFalse(stack1Q.empty());
        Assert.assertEquals(1, stack1Q.top());
    }

    @Test
    public void pop() {
        stack2Q.push(1);
        stack2Q.push(2);
        Assert.assertEquals(2, stack2Q.pop());
        Assert.assertEquals(1, stack2Q.pop());

        stack1Q.push(1);
        stack1Q.push(2);
        Assert.assertEquals(2, stack1Q.pop());
        Assert.assertEquals(1, stack1Q.pop());
    }

    @Test
    public void top() {
        stack2Q.push(1);
        stack2Q.push(2);
        Assert.assertEquals(2, stack2Q.top());
        stack2Q.push(3);
        Assert.assertEquals(3, stack2Q.top());

        stack1Q.push(1);
        stack1Q.push(2);
        Assert.assertEquals(2, stack1Q.top());
        stack1Q.push(3);
        Assert.assertEquals(3, stack1Q.top());
    }

    @Test
    public void empty() {
        Assert.assertTrue(stack2Q.empty());
        stack2Q.push(1);
        Assert.assertFalse(stack2Q.empty());

        Assert.assertTrue(stack1Q.empty());
        stack1Q.push(1);
        Assert.assertFalse(stack1Q.empty());
    }

}