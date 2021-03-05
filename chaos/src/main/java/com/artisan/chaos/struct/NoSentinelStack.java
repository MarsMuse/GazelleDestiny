package com.artisan.chaos.struct;

public class NoSentinelStack {
    Node head;

    public NoSentinelStack() {
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
