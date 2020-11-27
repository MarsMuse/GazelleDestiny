package com.artisan.leetcode.editor.cn;

//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。 
//
// 
//
// 说明： 
//
// 
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。 
// 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//nums1 = [1,2,3,0,0,0], m = 3
//nums2 = [2,5,6],       n = 3
//
//输出：[1,2,2,3,5,6] 
//
// 
//
// 提示： 
//
// 
// -10^9 <= nums1[i], nums2[i] <= 10^9 
// nums1.length == m + n 
// nums2.length == n 
// 
// Related Topics 数组 双指针 
// 👍 695 👎 0


import static com.artisan.util.ArrayUtils.*;

/**
 * [88]合并两个有序数组
 *
 * @author xzman
 * @since 2020-11-26 22:13:59
 */
public class MergeSortedArray {
    public static void main(String[] args) {
        Solution solution = new MergeSortedArray().new Solution();
        int[] num1 = getSortAscArray(12, 20, 5);
        int[] num2 = getSortAscArray(3, 20, 0);
        printArray(num1);
        printArray(num2);
        solution.merge(num1, num1.length-num2.length, num2, num2.length);
        printArray(num1);
        printArray(num2);
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            if( nums1 == nums2 || 0 == nums2.length){
                return;
            }
            int index = 0;
            while (index < m + n) {
                // swap stage --num1
                if (index < m) {
                    if (nums2[0] < nums1[index]) {
                        int tempMore = nums1[index];
                        nums1[index] = nums2[0];
                        // swap stage -- num2
                        int swapIndex = 1;
                        while (true) {
                            if (swapIndex == n || tempMore < nums2[swapIndex]) {
                                nums2[swapIndex - 1] = tempMore;
                                break;
                            } else {
                                nums2[swapIndex - 1] = nums2[swapIndex];
                                swapIndex++;
                            }
                        }
                    }
                } else {
                    // merge stage
                    nums1[index] = nums2[index - m];
                }
                index++;
            }
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
