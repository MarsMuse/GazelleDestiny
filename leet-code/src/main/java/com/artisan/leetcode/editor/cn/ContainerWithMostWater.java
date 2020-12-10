package com.artisan.leetcode.editor.cn;

//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, 
//ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 说明：你不能倾斜容器。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
//解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 示例 2： 
//
// 
//输入：height = [1,1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：height = [4,3,2,1,4]
//输出：16
// 
//
// 示例 4： 
//
// 
//输入：height = [1,2,1]
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// n = height.length 
// 2 <= n <= 3 * 104 
// 0 <= height[i] <= 3 * 104 
// 
// Related Topics 数组 双指针 
// 👍 1997 👎 0


/**
 * [11]盛最多水的容器
 *
 * @author xzman
 * @since 2020-11-24 22:41:49
 */
public class ContainerWithMostWater {
    public static void main(String[] args) {
        Solution solution = new ContainerWithMostWater().new Solution();
        System.out.println(solution.maxArea(new int[]{8,3,7,120,120,7,5,4,8,5}));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxArea(int[] height) {
            if (null == height || height.length < 2) {
                return 0;
            }
            int maxFront = 0, frontIndex = 1;
            int maxEnd = height.length - 1, endIndex = height.length - 2;
            int maxValue = getValue(maxFront, maxEnd, height);
            while (frontIndex < endIndex) {
                int frontValue = getValue(frontIndex, maxEnd, height);
                if(frontValue > maxValue){
                    maxValue = frontValue;
                    maxFront = frontIndex;
                }

                int endValue = getValue(maxFront, endIndex, height);
                if(endValue > maxValue){
                    maxValue = endValue;
                    maxEnd = endIndex;
                }
                frontIndex++;
                endIndex--;
            }
            return maxValue;
        }

        private int getValue(int frontIndex, int endIndex, int[] height) {
            return (endIndex - frontIndex) * Math.min(height[frontIndex], height[endIndex]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
