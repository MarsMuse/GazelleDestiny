package com.artisan.leetcode.editor.cn;

//给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。 
//
// 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。 
//
// 说明: 
//
// 
// 返回的下标值（index1 和 index2）不是从零开始的。 
// 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。 
// 
//
// 示例: 
//
// 输入: numbers = [2, 7, 11, 15], target = 9
//输出: [1,2]
//解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。 
// Related Topics 数组 双指针 二分查找 
// 👍 435 👎 0


import static com.artisan.leetcode.util.ArrayUtils.*;

/**
 * [167]两数之和 II - 输入有序数组
 *
 * @author xzman
 * @since 2020-11-30 23:11:24
 */
public class TwoSumIiInputArrayIsSorted {
    public static void main(String[] args) {
        Solution solution = new TwoSumIiInputArrayIsSorted().new Solution();
        int[] data = getSortAscArray(10, 20, -3);
        printArray(data);
        // TO TEST
        printArray(solution.twoSum(data, 19));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] twoSum(int[] numbers, int target) {
            int front = 0;
            int end = numbers.length - 1;
            while (front < end) {
                int gap = target - numbers[front];
                int frontBin = front + 1;
                while (frontBin <= end) {
                    int mid = (frontBin + end) / 2;
                    if (gap == numbers[mid]) {
                        return new int[]{front + 1, mid + 1};
                    }
                    if (gap > numbers[mid]) {
                        frontBin = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                }
                front++;
            }
            return null;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
