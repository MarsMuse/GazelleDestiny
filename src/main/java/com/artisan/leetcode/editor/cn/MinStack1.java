package com.artisan.leetcode.editor.cn;
//设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。 
//
// 
// push(x) —— 将元素 x 推入栈中。 
// pop() —— 删除栈顶的元素。 
// top() —— 获取栈顶元素。 
// getMin() —— 检索栈中的最小元素。 
// 
//
// 
//
// 示例: 
//
// 输入：
//["MinStack","push","push","push","getMin","pop","top","getMin"]
//[[],[-2],[0],[-3],[],[],[],[]]
//
//输出：
//[null,null,null,null,-3,null,0,-2]
//
//解释：
//MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.getMin();   --> 返回 -2.
// 
//
// 
//
// 提示： 
//
// 
// pop、top 和 getMin 操作总是在 非空栈 上调用。 
// 
// Related Topics 栈 设计 
// 👍 741 👎 0

import java.util.Random;

/**
 * [155]最小栈
 *
 * @author xzman
 * @since 2020-11-30 16:09:03
 */
public class MinStack1 {
    public static void main(String[] args) {
        MinStack minStack = new MinStack1().new MinStack();
        Random r = new Random();
        int length = 30;
        while (--length >= 0) {
            minStack.push(r.nextInt(30 + length));
        }
        System.out.println(minStack);
        System.out.println(minStack.minValue);
        System.out.println(minStack.top());
        int popCount = 25;
        while (--popCount >= 0) {
            minStack.pop();
            System.out.println(minStack);
            System.out.println(minStack.minValue);
            System.out.println(minStack.top());
        }

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MinStack {

        private Node top;
        private int minNodeCount;
        private int minValue;

        class Node {
            private int val;
            private Node next;

            public Node(int val, Node next) {
                this.val = val;
                this.next = next;
            }

            @Override
            public String toString() {
                return null == next ? val + "" : val + "->" + next.toString();
            }
        }

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            top = null;
        }

        public void push(int x) {
            if (null == top) {
                initStack(x);
            } else {
                top = new Node(x, top);
                dealMinValue(x);
            }
        }

        private void dealMinValue(int x) {
            if (x == minValue) {
                minNodeCount++;
            } else if (x < minValue) {
                initMinValue(x);
            }
        }

        private void initMinValue(int x) {
            minValue = x;
            minNodeCount = 1;
        }

        private void initStack(int x) {
            top = new Node(x, null);
            initMinValue(x);
        }

        public void pop() {
            Node deleteNode = top;
            top = deleteNode.next;
            if (null == top) {
                minNodeCount = 0;
                return;
            }
            int topValue = deleteNode.val;
            if (topValue == minValue) {
                deleteMinValue();
            }
        }

        private void deleteMinValue() {
            if (minNodeCount > 1) {
                minNodeCount--;
                return;
            }
            Node head = top;
            minValue = top.val;
            while (null != head.next) {
                head = head.next;
                if (minValue == head.val) {
                    minNodeCount++;
                    continue;
                }
                if (minValue > head.val) {
                    initMinValue(head.val);
                }
            }
        }


        public int top() {
            return top.val;
        }

        public int getMin() {
            return minValue;
        }

        @Override
        public String toString() {
            return top.toString();
        }
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
//leetcode submit region end(Prohibit modification and deletion)

}