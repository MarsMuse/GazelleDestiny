package com.artisan.leetcode.editor.cn;
//给定一个整数 n，返回 n! 结果尾数中零的数量。 
//
// 示例 1: 
//
// 输入: 3
//输出: 0
//解释: 3! = 6, 尾数中没有零。 
//
// 示例 2: 
//
// 输入: 5
//输出: 1
//解释: 5! = 120, 尾数中有 1 个零. 
//
// 说明: 你算法的时间复杂度应为 O(log n) 。 
// Related Topics 数学 
// 👍 384 👎 0

import java.math.BigDecimal;

/**
 * [172]阶乘后的零
 *
 * @author xzman
 * @since 2020-12-02 15:33:15
 */
public class FactorialTrailingZeroes {
    public static void main(String[] args) {
        Solution solution = new FactorialTrailingZeroes().new Solution();
        BigDecimal result = BigDecimal.valueOf(1);
        for (int i = 200; i < 201; i++) {
            result = result.multiply(BigDecimal.valueOf(i));
            System.out.printf("基数：%d，阶乘结果：%s，尾数为0的数量：%d\n", i, result, solution.trailingZeroes(i));
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int trailingZeroes(int n) {
            int zeroCount = 0;
            int checkNumber = 5;
            while (checkNumber <= n) {
                zeroCount += getFactorCount(checkNumber);
                checkNumber += 5;
            }
            return zeroCount;
        }

        private int getFactorCount(int n) {
            int count = 0;
            while (n > 0) {
                if (n % 5 != 0) {
                    return count;
                }
                count++;
                n /= 5;
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}