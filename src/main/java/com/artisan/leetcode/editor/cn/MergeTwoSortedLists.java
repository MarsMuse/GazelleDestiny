package com.artisan.leetcode.editor.cn;
//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
// 
// Related Topics 链表 
// 👍 1395 👎 0

import java.util.Random;

/**
 * [21]合并两个有序链表
 *
 * @author xzman
 * @since 2020-11-25 14:09:11
 */
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        Solution solution = new MergeTwoSortedLists().new Solution();
        int baseValue1 = 7;
        int baseValue2 = 11;
        ListNode l1 = new ListNode(baseValue1);
        ListNode l2 = new ListNode(baseValue1);
        ListNode l1M = l1;
        ListNode l2M = l2;
        Random r1 = new Random();
        int l1Length = 3 + r1.nextInt(100);
        int l2Length = 5 + r1.nextInt(100);
        while (l1Length-- > 0) {
            baseValue1 += r1.nextInt(20);
            l1.next = new ListNode(baseValue1);
            l1 = l1.next;
        }

        while (l2Length-- > 0) {
            baseValue2 += r1.nextInt(20);
            l2.next = new ListNode(baseValue2);
            l2 = l2.next;
        }
        System.out.println(l1M);
        System.out.println(l2M);
        System.out.println(solution.mergeTwoLists(l1M, l2M));
    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (null == l1) {
                return l2;
            }
            if (null == l2) {
                return l1;
            }
            ListNode result;
            ListNode prevNode;
            ListNode nextNode;
            if (l1.val < l2.val) {
                result = l1;
                prevNode = l1;
                nextNode = l2;
            } else {
                result = l2;
                prevNode = l2;
                nextNode = l1;
            }

            while (true) {
                if (null == prevNode.next) {
                    prevNode.next = nextNode;
                    break;
                }
                if (prevNode.next.val > nextNode.val) {
                    // 从大于副链的地方断开
                    ListNode temp = prevNode.next;
                    // 把副链接到主链上
                    prevNode.next = nextNode;
                    // 把断开的链作为副链继续
                    nextNode = temp;

                } else {
                    prevNode = prevNode.next;
                }
            }
            return result;
        }
    }


    //leetcode submit region end(Prohibit modification and deletion)
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
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