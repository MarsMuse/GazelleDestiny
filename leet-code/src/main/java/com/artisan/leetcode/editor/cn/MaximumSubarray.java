package com.artisan.leetcode.editor.cn;

//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 示例: 
//
// 输入: [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
// 
//
// 进阶: 
//
// 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。 
// Related Topics 数组 分治算法 动态规划 
// 👍 2653 👎 0


/**
 * [53]最大子序和
 *
 * @author xzman
 * @since 2020-11-25 21:23:50
 */
public class MaximumSubarray{
    public static void main(String[] args) {
        Solution solution = new MaximumSubarray().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxSubArray(int[] nums) {
        if(null == nums || 0 == nums.length){
            return 0;
        }
        if(1 == nums.length){
            return nums[0];
        }

        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
