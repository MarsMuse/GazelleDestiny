package com.artisan.leetcode.editor.cn;
//给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。 
//
// 说明： 
//
// 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？ 
//
// 示例 1: 
//
// 输入: [2,2,1]
//输出: 1
// 
//
// 示例 2: 
//
// 输入: [4,1,2,1,2]
//输出: 4 
// Related Topics 位运算 哈希表 
// 👍 1595 👎 0

import java.util.HashSet;
import java.util.Set;

/**
 * [136]只出现一次的数字
 *
 * @author xzman
 * @since 2020-11-27 18:28:10
 */
public class SingleNumber {
    public static void main(String[] args) {
        Solution solution = new SingleNumber().new Solution();
        System.out.println(solution.singleNumber(new int[]{-1, -1, -2}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int singleNumber(int[] nums) {
            if (1 == nums.length) {
                return nums[0];
            }
            Set<Integer> numberSet = new HashSet<>(nums.length / 2 + 1);
            int front = 0;
            int end = nums.length - 1;

            while (front <= end) {
                if (front == end) {
                    if (numberSet.contains(nums[front])) {
                        numberSet.remove(nums[front]);
                    } else {
                        numberSet.add(nums[front]);
                    }
                    break;
                }

                if (nums[front] == nums[end]) {
                    front++;
                    end--;
                    continue;
                }

                if (numberSet.contains(nums[front])) {
                    numberSet.remove(nums[front]);
                } else {
                    numberSet.add(nums[front]);
                }
                if (numberSet.contains(nums[end])) {
                    numberSet.remove(nums[end]);
                } else {
                    numberSet.add(nums[end]);
                }
                front++;
                end--;
            }
            for (int info : numberSet) {
                return info;
            }
            return 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}