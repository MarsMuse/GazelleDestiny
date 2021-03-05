package com.artisan.chaos.struct;

public class SentinelStack {

    Node sentinel;

    public SentinelStack() {
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
