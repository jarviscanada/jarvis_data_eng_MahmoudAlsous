package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Test;

public class TestQueueWithStack {

    QueueWithStack.QueueWithTwoStack queue2S = new QueueWithStack().queue2S;
    QueueWithStack.QueueWithTwoStackArom queue2SA = new QueueWithStack().queue2SA;

    @Test
    public void push() {
        Assert.assertTrue(queue2S.empty());
        queue2S.push(1);
        queue2S.push(2);
        Assert.assertFalse(queue2S.empty());
        Assert.assertEquals(1, queue2S.peek());

        Assert.assertTrue(queue2SA.empty());
        queue2SA.push(1);
        queue2SA.push(2);
        Assert.assertFalse(queue2SA.empty());
        Assert.assertEquals(1, queue2SA.peek());
    }

    @Test
    public void pop() {
        queue2S.push(1);
        queue2S.push(2);
        Assert.assertEquals(1, queue2S.pop());
        Assert.assertEquals(2, queue2S.pop());

        queue2SA.push(1);
        queue2SA.push(2);
        Assert.assertEquals(1, queue2SA.pop());
        Assert.assertEquals(2, queue2SA.pop());
    }

    @Test
    public void peek() {
        queue2S.push(1);
        queue2S.push(2);
        Assert.assertEquals(1, queue2S.peek());
        queue2S.pop();
        Assert.assertEquals(2, queue2S.peek());

        queue2SA.push(1);
        queue2SA.push(2);
        Assert.assertEquals(1, queue2SA.peek());
        queue2SA.pop();
        Assert.assertEquals(2, queue2SA.peek());
    }

    @Test
    public void empty() {
        Assert.assertTrue(queue2S.empty());
        queue2S.push(1);
        Assert.assertFalse(queue2S.empty());

        Assert.assertTrue(queue2SA.empty());
        queue2SA.push(1);
        Assert.assertFalse(queue2SA.empty());
    }

}