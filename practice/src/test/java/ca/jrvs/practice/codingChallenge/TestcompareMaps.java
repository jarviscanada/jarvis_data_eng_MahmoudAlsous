package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.HashJMap;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestcompareMaps {

    @Test
    public void compareMapsTest(){
        Map<Integer,String> Map1 = new HashMap<>();
        Map1.put(1,"C");
        Map1.put(2,"A");
        Map1.put(3,"B");
        Map<Integer,String> Map2 = new HashMap<>();
        Map2.put(1,"C");
        Map2.put(2,"A");
        Map2.put(3,"B");
        Map<Integer,String> Map3 = new HashMap<>();
        Map3.put(1,"C");
        Map3.put(2,"A");
        Map3.put(3,"B");
        Map<Integer,String> Map4 = new HashMap<>();
        Map4.put(1,"d");
        Map4.put(2,"a");
        Map4.put(3,"b");

        compareMaps testObj = new compareMaps();
        assertTrue(testObj.compareMapsEquals(Map1,Map2));
        assertFalse(testObj.compareMapsEquals(Map3,Map4));
    }

    @Test
    public void compareHashJMapsTest(){
        HashJMap<Integer,String> Map1 = new HashJMap<>();
        Map1.put(1,"C");
        Map1.put(2,"A");
        Map1.put(3,"B");
        HashJMap<Integer,String> Map2 = new HashJMap<>();
        Map2.put(1,"C");
        Map2.put(2,"A");
        Map2.put(3,"B");
        HashJMap<Integer,String> Map3 = new HashJMap<>();
        Map3.put(1,"C");
        Map3.put(2,"A");
        Map3.put(3,"B");
        HashJMap<Integer,String> Map4 = new HashJMap<>();
        Map4.put(1,"d");
        Map4.put(2,"a");
        Map4.put(3,"b");

        compareMaps testObj = new compareMaps();
        assertFalse(testObj.compareHashJMaps(Map1,Map2));
        assertFalse(testObj.compareHashJMaps(Map3,Map4));
    }
}