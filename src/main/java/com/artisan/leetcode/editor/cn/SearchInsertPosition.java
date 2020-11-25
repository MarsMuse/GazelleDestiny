package com.artisan.leetcode.editor.cn;
//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。 
//
// 你可以假设数组中无重复元素。 
//
// 示例 1: 
//
// 输入: [1,3,5,6], 5
//输出: 2
// 
//
// 示例 2: 
//
// 输入: [1,3,5,6], 2
//输出: 1
// 
//
// 示例 3: 
//
// 输入: [1,3,5,6], 7
//输出: 4
// 
//
// 示例 4: 
//
// 输入: [1,3,5,6], 0
//输出: 0
// 
// Related Topics 数组 二分查找 
// 👍 744 👎 0

import java.util.Random;

import static com.artisan.util.ArrayUtils.*;

/**
 * [35]搜索插入位置
 *
 * @author xzman
 * @since 2020-11-25 17:36:24
 */
public class SearchInsertPosition {
    public static void main(String[] args) {
        Solution solution = new SearchInsertPosition().new Solution();
        int[] data = getSortAscArray(20, 30, 10);
        data = new int[]{1,3};
        printArray(data);
        Random r1 =new Random();
        int key = r1.nextInt(100);
        key = 0;
        System.out.println(key);
        System.out.println(solution.searchInsert(data, key));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int searchInsert(int[] nums, int target) {
            if (1 == nums.length) {
                return nums[0] >= target ? 0 : 1;
            }
            int front = 0;
            int end = nums.length - 1;
            while (end > front) {
                int midIndex = (front + end) >>> 1;
                if (target == nums[midIndex]) {
                    return midIndex;
                }
                if (target > nums[midIndex]) {
                    front = midIndex + 1;
                } else {
                    end = midIndex - 1;
                }
            }

            return target > nums[end] ? end + 1 : end;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}