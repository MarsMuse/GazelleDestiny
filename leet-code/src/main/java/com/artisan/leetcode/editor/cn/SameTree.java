package com.artisan.leetcode.editor.cn;

//给定两个二叉树，编写一个函数来检验它们是否相同。
//
// 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
//
// 示例 1:
//
// 输入:       1         1
//          / \       / \
//         2   3     2   3
//
//        [1,2,3],   [1,2,3]
//
//输出: true
//
// 示例 2:
//
// 输入:      1          1
//          /           \
//         2             2
//
//        [1,2],     [1,null,2]
//
//输出: false
//
//
// 示例 3:
//
// 输入:       1         1
//          / \       / \
//         2   1     1   2
//
//        [1,2,1],   [1,1,2]
//
//输出: false
//
// Related Topics 树 深度优先搜索
// 👍 518 👎 0


import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * [100]相同的树
 *
 * @author xzman
 * @since 2020-11-26 22:49:08
 */
public class SameTree {
    public static void main(String[] args) {
        Solution solution = new SameTree().new Solution();

        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (null == p && null == q) {
                return true;
            } else if (null == p || null == q) {
                return false;
            }
            Deque<TreeNode> oneDeque = new LinkedBlockingDeque<>();
            Deque<TreeNode> twoDeque = new LinkedBlockingDeque<>();
            while (null != p && null != q) {

                if (p.val != q.val) {
                    return false;
                }
                if (existChildrenDiff(p, q)) {
                    return false;
                }
                if (null == p.left && null == p.right) {
                    p = oneDeque.pollLast();
                    q = twoDeque.pollLast();
                } else if (null != p.left) {
                    p = p.left;
                    q = q.left;
                    if (null != p.right) {
                        oneDeque.offer(p.right);
                        twoDeque.offer(q.right);
                    }
                } else {
                    p = p.right;
                    q = q.right;
                }
            }

            return true;
        }

        private boolean existChildrenDiff(TreeNode p, TreeNode q) {
            if (null == p.left && null != q.left) {
                return true;
            }
            if (null != p.left && null == q.left) {
                return true;
            }
            if (null == p.right && null != q.right) {
                return true;
            }
            if (null != p.right && null == q.right) {
                return true;
            }
            return false;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
