package ca.jrvs.practice.codingChallenge;
import ca.jrvs.practice.dataStructure.HashJMap;

import java.util.Map;

/**
 * Ticket: https://www.notion.so/jarvisdev/How-to-compare-two-maps-31bb20e4d9be479eb7ba5f1b19d74be0
 */
public class compareMaps {

    /**
     * Use .equals API
     * Big-O: O(n)
     * Justification: comparison of hashes in each entry
     * @param m1 Map 1
     * @param m2 Map 2
     * @param <K> key
     * @param <V> value
     * @return boolean True/False
     */
    public <K,V> boolean compareMapsEquals(Map<K,V> m1, Map<K,V> m2){
        return m1.equals(m2);
    }

    /**
     * Implement equals in HashJMap
     * Big-O: O(n)
     * Justification: comparison of hashes in each entry
     * @param m1 Map 1
     * @param m2 Map 2
     * @param <K> key
     * @param <V> value
     * @return boolean True/False
     */
    public <K,V> boolean compareHashJMaps(HashJMap<K,V> m1, HashJMap<K,V> m2){
        return m1.equals(m2);
    }
}
