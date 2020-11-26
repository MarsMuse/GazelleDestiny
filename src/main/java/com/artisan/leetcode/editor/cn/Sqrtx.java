package com.artisan.leetcode.editor.cn;
//实现 int sqrt(int x) 函数。 
//
// 计算并返回 x 的平方根，其中 x 是非负整数。 
//
// 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。 
//
// 示例 1: 
//
// 输入: 4
//输出: 2
// 
//
// 示例 2: 
//
// 输入: 8
//输出: 2
//说明: 8 的平方根是 2.82842..., 
//     由于返回类型是整数，小数部分将被舍去。
// 
// Related Topics 数学 二分查找 
// 👍 547 👎 0

/**
 * [69]x 的平方根
 *
 * @author xzman
 * @since 2020-11-26 10:21:25
 */
public class Sqrtx {
    public static void main(String[] args) {
        Solution solution = new Sqrtx().new Solution();
        int num = 1;
        while (--num >= 0) {
            System.out.printf("原始数：%d，开方后结果为%d\n", num, solution.mySqrt(num));
        }

        System.out.printf("原始数：%d，开方后结果为%d\n", 2147483647, solution.mySqrt(2147483647));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int mySqrt(int x) {
            if (0 == x || 1 == x) {
                return x;
            }
            int front = 0;
            int end = Math.min(1 << 16, x);
            int mid = 0;
            while (end >= front) {
                mid = (front + end) >>> 1;
                if (mid * mid == x) {
                    return mid;
                }
                // 溢出或者平方大于入参
                if (mid * mid > x || mid * mid < 0) {
                    end = mid - 1;
                } else {
                    front = mid + 1;
                }
            }
            return mid * mid > x || mid * mid < 0 ? mid - 1 : mid;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}