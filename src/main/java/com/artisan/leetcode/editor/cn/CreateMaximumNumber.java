package com.artisan.leetcode.editor.cn;

//给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接
//成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
//
// 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
//
// 说明: 请尽可能地优化你算法的时间和空间复杂度。
//
// 示例 1:
//
// 输入:
//nums1 = [3, 4, 6, 5]
//nums2 = [9, 1, 2, 5, 8, 3]
//k = 5
//输出:
//[9, 8, 6, 5, 3]
//
// 示例 2:
//
// 输入:
//nums1 = [6, 7]
//nums2 = [6, 0, 4]
//k = 5
//输出:
//[6, 7, 6, 0, 4]
//
// 示例 3:
//
// 输入:
//nums1 = [3, 9]
//nums2 = [8, 9]
//k = 3
//输出:
//[9, 8, 9]
// Related Topics 贪心算法 动态规划
// 👍 288 👎 0


import static com.artisan.util.ArrayUtils.*;

/**
 * [321]拼接最大数
 *
 * @author xzman
 * @since 2020-12-02 21:23:40
 */
public class CreateMaximumNumber {
    public static void main(String[] args) {
        Solution solution = new CreateMaximumNumber().new Solution();
        int[] firstArray = new int[]{6,7};
        printArray(firstArray);
        int[] secondArray = new int[]{6,0,4};
        printArray(secondArray);
        printArray(solution.maxNumber(firstArray, secondArray, 5));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] maxNumber(int[] nums1, int[] nums2, int k) {
            if (0 == k) {
                return new int[0];
            }
            int fStart = 0;
            int sStart = 0;
            int fMax = -1;
            int sMax = -1;
            int[] result = new int[k];
            while (k > 0) {
                // 可选择空间
                int choiceRange = (null == nums1 ? 0 : nums1.length) + (null == nums2 ? 0 : nums2.length) -
                        fStart - sStart - k + 1;
                int fMaxIndex = getMaxIndexByChoiceRange(nums1, choiceRange, fStart);
                int sMaxIndex = getMaxIndexByChoiceRange(nums2, choiceRange, sStart);
                if (fMaxIndex != -1 && null != nums1) {
                    fMax = nums1[fMaxIndex];
                }
                if (sMaxIndex != -1 && null != nums2) {
                    sMax = nums2[sMaxIndex];
                }
                if (fMax > sMax) {
                    result[result.length - k] = fMax;
                    fStart = fMaxIndex + 1;
                } else if (fMax == sMax && fMax > -1) {
                    int fsMaxIndex = getMaxIndexByChoiceRange(nums1, fMaxIndex - fStart, fStart);
                    int ssMaxIndex = getMaxIndexByChoiceRange(nums2, sMaxIndex - sStart, sStart);
                    if (fsMaxIndex == -1) {
                        result[result.length - k] = sMax;
                        sStart = sMaxIndex + 1;
                    } else if (ssMaxIndex == -1) {
                        result[result.length - k] = fMax;
                        fStart = fMaxIndex + 1;
                    } else if (nums1[fsMaxIndex] > nums2[ssMaxIndex]) {
                        result[result.length - k] = sMax;
                        sStart = sMaxIndex + 1;
                    } else {
                        result[result.length - k] = fMax;
                        fStart = fMaxIndex + 1;
                    }
                } else if (sMax > 0) {
                    result[result.length - k] = sMax;
                    sStart = sMaxIndex + 1;
                }
                fMax = -1;
                sMax = -1;
                k--;
            }
            return result;
        }

        private int getMaxIndexByChoiceRange(int[] numberArray, int choiceRange, int startIndex) {
            if (null == numberArray || startIndex >= numberArray.length || choiceRange < 1) {
                return -1;
            }
            int maxValue = -1;
            int maxIndex = -1;
            choiceRange = Math.min(startIndex + choiceRange, numberArray.length);
            for (int i = startIndex; i < choiceRange; i++) {
                if (numberArray[i] > maxValue) {
                    maxIndex = i;
                    maxValue = numberArray[i];
                }
            }
            return maxIndex;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
