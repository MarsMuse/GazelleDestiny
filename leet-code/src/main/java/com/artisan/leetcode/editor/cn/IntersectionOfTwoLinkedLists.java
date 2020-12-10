package com.artisan.leetcode.editor.cn;
//编写一个程序，找到两个单链表相交的起始节点。 
//
// 如下面的两个链表： 
//
// 
//
// 在节点 c1 开始相交。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, s
//kipB = 3
//输出：Reference of the node with value = 8
//输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1
//,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
// 
//
// 
//
// 示例 2： 
//
// 
//
// 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB =
// 1
//输出：Reference of the node with value = 2
//输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4
//]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
// 
//
// 
//
// 示例 3： 
//
// 
//
// 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
//输出：null
//输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而
// skipA 和 skipB 可以是任意值。
//解释：这两个链表不相交，因此返回 null。
// 
//
// 
//
// 注意： 
//
// 
// 如果两个链表没有交点，返回 null. 
// 在返回结果后，两个链表仍须保持原有的结构。 
// 可假定整个链表结构中没有循环。 
// 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。 
// 
// Related Topics 链表 
// 👍 895 👎 0

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * [160]相交链表
 *
 * @author xzman
 * @since 2020-11-30 17:46:11
 */
public class IntersectionOfTwoLinkedLists {
    public final Random tr;

    public IntersectionOfTwoLinkedLists() {
        tr = ThreadLocalRandom.current();
    }

    public static void main(String[] args) {
        IntersectionOfTwoLinkedLists im = new IntersectionOfTwoLinkedLists();
        Solution solution = im.new Solution();
        ListNode append = im.generateLink(1,null);
        System.out.println(append);
        ListNode linkA = im.generateLink(1, append);
        System.out.println(linkA);
        ListNode linkB = im.generateLink(1, append);
        System.out.println(linkB);
        System.out.println(solution.getIntersectionNode(linkA, linkB));
        System.out.println(linkA);
        System.out.println(linkB);
    }

    ListNode generateLink(int length, ListNode append) {
        ListNode head = new ListNode(tr.nextInt(20 + length));
        ListNode current = head;
        while (--length > 0) {
            current.next = new ListNode(tr.nextInt(20 + length));
            current = current.next;
        }
        if (null != append) {
            current.next = append;
        }
        return head;
    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */
    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (null == headA || null == headB) {
                return null;
            }
            if (headA == headB) {
                return headA;
            }
            ListNode tryA = headA;
            ListNode tryB = headB;
            boolean exist = false;
            int gapLength = 0;
            ListNode moreLink = null;
            ListNode lessLink = null;
            // 都到了最后一个节点跳出循环
            while (true) {
                if (tryA == tryB) {
                    // 链长度不同，只有在最后一个节点确认是否相交，此时得出链长差距
                    if (gapLength == 0) {
                        return tryA;
                    } else {
                        // 链长度一致，遇到的第一个相同点即为相交节点
                        exist = true;
                        break;
                    }
                }
                if (null == tryA.next && null == tryB.next) {
                    break;
                }
                if (null != tryA.next) {
                    tryA = tryA.next;
                } else if (null == moreLink) {
                    moreLink = headB;
                    lessLink = headA;
                }

                if (null != tryB.next) {
                    tryB = tryB.next;
                } else if (null == moreLink) {
                    moreLink = headA;
                    lessLink = headB;
                }

                if (null != moreLink) {
                    gapLength++;
                }
            }
            if (exist) {
                while (--gapLength >= 0) {
                    moreLink = moreLink.next;
                }
                while (null != moreLink && null != lessLink) {
                    if (moreLink == lessLink) {
                        return moreLink;
                    }
                    moreLink = moreLink.next;
                    lessLink = lessLink.next;
                }
            }
            return null;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    static class ListNode {
        int val;
        ListNode next;

        @Override
        public String toString() {
            return null == next ? String.valueOf(val) : String.valueOf(val) + "->" + next.toString();
        }

        ListNode(int x) {
            val = x;
            next = null;


        }
    }
}