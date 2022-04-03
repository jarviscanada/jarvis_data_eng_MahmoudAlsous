package ca.jrvs.practice.codingChallenge;

/**
 * Ticket: https://www.notion.so/jarvisdev/Count-Primes-b3c0541dd0bb46d1af85072a165ca604
 */

public class CountPrimes {
    public boolean isPrime(int N) {
        for(int i = 2 ; i * i <= N ; i++) {
            if (N % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Counts the prime numbers less than a value
     * Big O: O(n)
     * Justification: Iterates through the numbers from 3 to N and checks if each number is prime or not, then increments the counter
     * @param N
     * @return
     */
    public int countPrimes(int N) {
        if(N < 3) {
            return 0;
        }
        int count = 1;
        for(int i = 3 ; i < N ; i += 2) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }
}
