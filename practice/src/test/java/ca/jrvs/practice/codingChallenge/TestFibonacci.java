package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestFibonacci {
    @Test
    public void FibRecTest(){
        Fibonacci fib = new Fibonacci();
        assertEquals(fib.fibRec(2),1);
        assertEquals(fib.fibRec(10),55);
    }

    @Test
    public void FibBottomUpTest(){
        Fibonacci fib = new Fibonacci();
        assertEquals(fib.fiboBottomUp(2),1);
        assertEquals(fib.fiboBottomUp(10),55);
    }
}