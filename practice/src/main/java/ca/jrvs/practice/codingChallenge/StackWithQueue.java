package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Ticket: https://www.notion.so/jarvisdev/Implement-Stack-using-Queue-770825240d5e4cb2acc4667bda7ab3ee
 */

public class StackWithQueue {
    StackWithTwoQ stack2Q = new StackWithTwoQ();
    StackWithOneQ stack1Q = new StackWithOneQ();

    /**
     * Implements a stack using two Queues
     */
    class StackWithTwoQ{
        private Queue<Integer> Q1;
        private Queue<Integer> Q2;
        private int top;

        public StackWithTwoQ(){
            Q1 = new LinkedList<>();
            Q2 = new LinkedList<>();
        }


        /**
         * Add new elemnt onto Q2 and maintain that new element as the top of the stack
         * Big O: O(n)
         * @param x element to be pushed
         */
        public void push(int x){
            Q2.add(x);
            top = x;
            while(!Q1.isEmpty()){
                Q2.add(Q1.remove());
            }
            Queue<Integer> temp = Q1;
            Q1 = Q2;
            Q2 = temp;
        }

        /**
         * Remove element from stack and return it
         * Big o: O(1)
         */
        public int pop(){
            int popped = Q1.remove();
            if (!Q1.isEmpty()) {
                top = Q1.peek();
            }
            return popped;
        }

        /**
         * Big o: O(1)
         */
        public boolean empty(){
            return Q1.isEmpty();
        }

        /**
         * Big o: O(1)
         */
        public int top(){
            return top;
        }
    }

    /**
     * Implements a stack using one Queue
     */
    class StackWithOneQ{
        private Queue<Integer> Q1;

        public StackWithOneQ(){
            Q1 = new LinkedList<>();
        }

        /**
         * Add element to the queue and move it to the front
         * Big O: O(n)
         * @param x element to be added
         */
        public void push(int x){
            Q1.add(x);
            for(int i = Q1.size();i > 1;i--){
                Q1.add(Q1.remove());
            }
        }

        /**
         * Remove element from queue and return it
         * Big O: O(1)
         * @return Element removed
         */
        public int pop(){
            return Q1.remove();
        }

        /**
         * Check if the queue is empty
         * Big O: O(1)
         * @return true or false
         */
        public boolean empty(){
            return Q1.isEmpty();
        }

        /**
         * Return the top of the stack
         * Big O: O(n)
         * @return front of the queue
         */
        public int top(){
            return Q1.peek();
        }
    }
}
