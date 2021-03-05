package com.artisan.chaos.struct;

import java.util.Random;
import java.util.StringJoiner;

public class SingleLinkStack {

    public static void main(String[] args) {
        Stack stack = new SingleLinkStack().new Stack();
        StackNoSentinel ss = new SingleLinkStack().new StackNoSentinel();
        Random random = new Random();
        StringJoiner joiner = new StringJoiner("->");
        StringJoiner popJoiner = new StringJoiner("<-");
        StringJoiner popJoinerNo = new StringJoiner("<-");

        for (int i = 0; i < 100; i++) {
            int data = random.nextInt(1000);
            joiner.add(String.valueOf(data));
            stack.push(data);
            ss.push(data);
            if (0 == data % 3) {
                popJoiner.add(String.valueOf(stack.pop()));
                popJoinerNo.add(String.valueOf(ss.pop()));
            }
        }
        System.out.println(joiner.toString());

        while (stack.hasNext()) {
            popJoiner.add(String.valueOf(stack.pop()));
        }
        while (ss.hasNext()) {
            popJoinerNo.add(String.valueOf(ss.pop()));
        }
        System.out.println(popJoiner.toString());
        System.out.println(popJoinerNo.toString());
    }

    class Stack {

        Node sentinel;

        public Stack() {
            sentinel = new Node();
        }

        public void push(int value) {
            sentinel.next = new Node(sentinel.next, value);
        }

        public boolean hasNext() {
            return null != sentinel.next;
        }

        public int pop() {
            Node node = sentinel.next;
            if (null != node) {
                sentinel.next = node.next;
                return node.value;
            }
            throw new IndexOutOfBoundsException("empty stack");
        }


        class Node {
            Node next;
            int value;

            public Node() {
            }

            public Node(Node next, int value) {
                this.next = next;
                this.value = value;
            }
        }
    }

    class StackNoSentinel {
        Node head;

        public StackNoSentinel() {
        }

        public void push(int value) {
            head = new Node(head, value);
        }

        public boolean hasNext() {
            return null != head;
        }

        public int pop() {
            if (null != head) {
                int value = head.value;
                head = head.next;
                return value;
            }
            throw new IndexOutOfBoundsException("empty stack");
        }

        class Node {
            Node next;
            int value;

            public Node() {
            }

            public Node(Node next, int value) {
                this.next = next;
                this.value = value;
            }
        }
    }
}
