package com.artisan.leetcode.editor.cn;
//给定一个二叉树的根节点 root ，返回它的 中序 遍历。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[2,1]
// 
//
// 示例 5： 
//
// 
//输入：root = [1,null,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表 
// 👍 882 👎 0


import java.util.ArrayList;
import java.util.List;

/**
 * [94]二叉树的中序遍历
 *
 * @author xzman
 * @since 2021-03-16 14:01:13
 */
public class BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new BinaryTreeInorderTraversal().new Solution();
        System.out.println(solution.inorderTraversal(mockNode()));
    }


    static TreeNode mockNode(){
        TreeNode root = new TreeNode(2,null,null);
        TreeNode l1 =  new TreeNode(3,null,null);
        root.left = l1;
        TreeNode lr2 =  new TreeNode(5,null,null);
        l1.right = lr2;
        TreeNode r1 =  new TreeNode(4,null,null);
        root.right = r1;
        TreeNode rl2 =  new TreeNode(6,null,null);
        r1.left = rl2;
        return root;
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
        public List<Integer> inorderTraversal(TreeNode root) {
            return recursionTraversal(root);
        }

        private List<Integer> recursionTraversal(TreeNode root) {
            if (null == root) {
                return new ArrayList<>();
            }
            List<Integer> nodeList = new ArrayList<>(128);
            inorderLogic(root, nodeList);
            return nodeList;
        }

        private void inorderLogic(TreeNode root, List<Integer> nodeList) {
            if (null == root) {
                return;
            }
            inorderLogic(root.left, nodeList);
            nodeList.add(root.val);
            inorderLogic(root.right, nodeList);
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