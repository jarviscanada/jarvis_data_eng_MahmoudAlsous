package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCountPrimes {
    @Test
    public void testPrimeCount(){
        CountPrimes CP = new CountPrimes();
        assertEquals(4,CP.countPrimes(10));
    }
}
