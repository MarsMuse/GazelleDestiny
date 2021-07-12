package com.artisan.leetcode.model;

/**
 * 树节点
 *
 * @author xz man
 * @since 2021/3/17 18:03
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

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
