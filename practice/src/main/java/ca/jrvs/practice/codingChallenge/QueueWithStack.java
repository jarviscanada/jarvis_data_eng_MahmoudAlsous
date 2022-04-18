package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

/**
 * Ticket: https://www.notion.so/jarvisdev/Implement-Queue-using-Stacks-6d86ff04161145368b61bd89fcbe1a33
 */

public class QueueWithStack {
    QueueWithTwoStack queue2S = new QueueWithTwoStack();
    QueueWithTwoStackArom queue2SA = new QueueWithTwoStackArom();

    /**
     * Implement Queue with two stacks
     */
    class QueueWithTwoStack{
        Stack<Integer> S1;
        Stack<Integer> S2;
        private int front;

        public QueueWithTwoStack() {
            S1 = new Stack<>();
            S2 = new Stack<>();
        }

        /**
         * Push element onto Queue
         * Big O: O(n)
         * @param x element to be pushed
         */
        public void push(int x) {
            if (S1.empty()) {
                front = x;
            }
            while (!S1.isEmpty()) {
                S2.push(S1.pop());
            }
            S2.push(x);
            while (!S2.isEmpty()) {
                S1.push(S2.pop());
            }
        }

        /**
         * Remove element from queue and return it
         * Big O: O(1)
         * @return element removed
         */
        public int pop(){
            int popped = S1.pop();
            if (!S1.empty()) {
                front = S1.peek();
            }
            return popped;
        }

        public boolean empty() {
            return S1.isEmpty();
        }

        public int peek() {
            return front;
        }
    }

    class QueueWithTwoStackArom{
        Stack<Integer> S1;
        Stack<Integer> S2;
        private int front;

        public QueueWithTwoStackArom(){
            S1 = new Stack<>();
            S2 = new Stack<>();
        }

        /**
         * Add element onto Queue
         * Big O: O(1)
         * @param x element to be pushed
         */
        public void push(int x) {
            if (S1.empty())
                front = x;
            S1.push(x);
        }

        /**
         * Remove the element at the front of the Queue
         * Big O: O(1)
         * @return Removed element
         */
        public int pop() {
            if (S2.isEmpty()) {
                while (!S1.isEmpty())
                    S2.push(S1.pop());
            }
            return S2.pop();
        }

        public boolean empty() {
            return S1.isEmpty() && S2.isEmpty();
        }

        public int peek() {
            if (!S2.isEmpty()) {
                return S2.peek();
            }
            return front;
        }
    }
}
