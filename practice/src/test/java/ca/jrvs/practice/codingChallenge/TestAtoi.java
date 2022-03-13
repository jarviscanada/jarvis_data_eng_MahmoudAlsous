package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAtoi {
    @Test
    public void testAtoiBuiltIn(){
        Atoi atoi = new Atoi();
        assertEquals(atoi.atoiBuiltIn("10"),10);
        assertEquals(atoi.atoiBuiltIn("   -50 "),-50);
        assertEquals(atoi.atoiBuiltIn("0011233"),11233);
        assertEquals(atoi.atoiBuiltIn("-17700"),-17700);
        assertEquals(atoi.atoiBuiltIn("2147483648"),Integer.MAX_VALUE);
        assertEquals(atoi.atoiBuiltIn("-2147483648"),Integer.MIN_VALUE);
    }

    @Test
    public void testAtoiImp(){
        Atoi atoi = new Atoi();
        assertEquals(atoi.atoiImp("10"),10);
        assertEquals(atoi.atoiImp("   -50 "),-50);
        assertEquals(atoi.atoiImp("0011233"),11233);
        assertEquals(atoi.atoiImp("-17700"),-17700);
        assertEquals(atoi.atoiImp("2147483648"),Integer.MAX_VALUE);
        assertEquals(atoi.atoiImp("-2147483648"),Integer.MIN_VALUE);
    }
}