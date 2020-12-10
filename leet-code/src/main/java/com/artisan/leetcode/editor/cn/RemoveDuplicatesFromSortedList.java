package com.artisan.leetcode.editor.cn;

//给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。 
//
// 示例 1: 
//
// 输入: 1->1->2
//输出: 1->2
// 
//
// 示例 2: 
//
// 输入: 1->1->2->3->3
//输出: 1->2->3 
// Related Topics 链表 
// 👍 430 👎 0


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * [83]删除排序链表中的重复元素
 *
 * @author xzman
 * @since 2020-11-26 21:56:16
 */
public class RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        Solution solution = new RemoveDuplicatesFromSortedList().new Solution();
        // TO TEST
        int n = 30;
        Random r = new Random();
        int base =20;
        ListNode head = new ListNode(base);
        ListNode prev = head;
        while (--n >0){
            if(n%5 !=0){
                base += r.nextInt(5);
            }
            ListNode next = new ListNode(base);
            prev.next = next;
            prev = next;
        }
        System.out.println(head);
        System.out.println(solution.deleteDuplicates(head));
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
        public ListNode deleteDuplicates(ListNode head) {
            if (null == head || null == head.next) {
                return head;
            }
            ListNode prev = head;
            while (null != prev.next) {
                if (prev.val == prev.next.val) {
                    prev.next = prev.next.next;
                } else {
                    prev = prev.next;
                }
            }
            return head;
        }


        public ListNode deleteUnorderedDuplicates(ListNode head) {
            if (null == head || null == head.next) {
                return head;
            }
            ListNode prev = head;
            Set<Integer> nodeValueSet = new HashSet<>();
            nodeValueSet.add(prev.val);
            while (null != prev.next) {
                if (nodeValueSet.contains(prev.next.val)) {
                    prev.next = prev.next.next;
                } else {
                    nodeValueSet.add(prev.next.val);
                    prev = prev.next;
                }
            }
            return head;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
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
