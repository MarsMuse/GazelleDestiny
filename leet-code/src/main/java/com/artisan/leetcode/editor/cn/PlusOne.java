package com.artisan.leetcode.editor.cn;

//给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。 
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。 
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = [1,2,3]
//输出：[1,2,4]
//解释：输入数组表示数字 123。
// 
//
// 示例 2： 
//
// 
//输入：digits = [4,3,2,1]
//输出：[4,3,2,2]
//解释：输入数组表示数字 4321。
// 
//
// 示例 3： 
//
// 
//输入：digits = [0]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= digits.length <= 100 
// 0 <= digits[i] <= 9 
// 
// Related Topics 数组 
// 👍 584 👎 0


import static com.artisan.leetcode.util.ArrayUtils.*;

/**
 * [66]加一
 *
 * @author xzman
 * @since 2020-11-25 22:06:08
 */
public class PlusOne {
    public static void main(String[] args) {
        Solution solution = new PlusOne().new Solution();
        // TO TEST
        int[] data = new int[]{9, 9, 9};
        printArray(data);
        printArray(solution.plusOne(data));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] plusOne(int[] digits) {
            int index = digits.length;
            boolean isPush = false;
            while (--index >= 0) {
                if (9 != digits[index]) {
                    digits[index] += 1;
                    isPush = true;
                    break;
                }
                digits[index] = 0;
            }
            if (!isPush) {
                int[] tempArray = new int[digits.length + 1];
                tempArray[0] = 1;
                return tempArray;
            }
            return digits;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
