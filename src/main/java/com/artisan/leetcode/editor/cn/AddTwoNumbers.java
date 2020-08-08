package com.artisan.leetcode.editor.cn;
//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。 
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 示例： 
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
// 
// Related Topics 链表 数学 
// 👍 4714 👎 0

import java.util.Random;

/**
 * [2]两数相加
 *
 * @author xzman
 * @since 2020-08-07 15:35:01
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        Random random = new Random();
        int addValueSeed = 1024 * 1024 * 1024;
        int loop = 1024;
        while (loop-- > 0) {
            int front = random.nextInt(addValueSeed);
            int end = random.nextInt(addValueSeed);
            test(front, end);
        }
    }

    public static void test(int front, int end) {
        Solution solution = new AddTwoNumbers().new Solution();
        ListNode frontNode = convertListNode(front);
        ListNode endNode = convertListNode(end);
        ListNode result = solution.addTwoNumbers(frontNode, endNode);
        int resultInt = listNodeToList(result);
        System.out.printf("加数1：%d，加数2：%d，结果为NODE转化为数字：%d，是否匹配：%s, node: %s\n",
                front, end, resultInt, resultInt == (front + end), result);
    }

    public static ListNode convertListNode(int value) {

        ListNode result = new ListNode(-1);
        ListNode prev = result;
        while (true) {
            if (value > 9) {
                prev.next = new ListNode(value % 10);
                value /= 10;
                prev = prev.next;
            } else {
                prev.next = new ListNode(value);
                break;
            }
        }
        return result.next;
    }


    public static int listNodeToList(ListNode value) {
        int result = 0;
        int addFactor = 1;
        while (true) {
            result += value.val * addFactor;
            addFactor *= 10;
            value = value.next;
            if (null == value) {
                break;
            }
        }
        return result;
    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode resultNode = new ListNode(-1);
            ListNode prevNode = resultNode;
            int addOne = 0;
            while (true) {

                int l1Value = 0;
                if (null != l1) {
                    l1Value = l1.val;
                }
                int l2Value = 0;
                if (null != l2) {
                    l2Value = l2.val;
                }
                int currentValue = l1Value + l2Value + addOne;
                addOne = 0;
                if (currentValue > 9) {
                    addOne = 1;
                    currentValue %= 10;
                }
                prevNode.next = new ListNode(currentValue);
                prevNode = prevNode.next;
                if (null != l1) {
                    l1 = l1.next;
                }
                if (null != l2) {
                    l2 = l2.next;
                }
                if (null == l1 && null == l2 && addOne == 0) {
                    break;
                }
            }
            return resultNode.next;
        }
    }


    //leetcode submit region end(Prohibit modification and deletion)
    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            if (null != next) {
                return String.format("%d -> %s", val, next.toString());
            } else {
                return String.format("%d", val);
            }
        }

    }
}