package com.artisan.leetcode.editor.cn;
//给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。 
//
// 示例 1: 
//
// 输入: 123
//输出: 321
// 
//
// 示例 2: 
//
// 输入: -123
//输出: -321
// 
//
// 示例 3: 
//
// 输入: 120
//输出: 21
// 
//
// 注意: 
//
// 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。 
// Related Topics 数学 
// 👍 2363 👎 0

import java.util.ArrayList;
import java.util.List;

/**
 * [7]整数反转
 *
 * @author xzman
 * @since 2020-11-24 13:19:14
 */
public class ReverseInteger {
    public static void main(String[] args) {
        Solution solution = new ReverseInteger().new Solution();
        System.out.println(solution.reverse(-2147483648));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int reverse(int x) {
            if (0 == x || x == Integer.MIN_VALUE) {
                return 0;
            }
            int temp = x;
            boolean isNegative = false;
            if (x < 0) {
                isNegative = true;
                temp *= -1;
            }
            List<Integer> dataArray = new ArrayList<>();
            while (temp > 0) {
                dataArray.add(temp % 10);
                temp /= 10;
            }
            int result = 0;
            for (int i = 1; i <= dataArray.size(); i++) {
                result += dataArray.get(i - 1) * pow(10, dataArray.size() - i);
            }
            if (result / pow(10, dataArray.size() -1) != dataArray.get(0)) {
                return 0;
            }
            if (isNegative) {
                return -1 * result;
            }
            return result;
        }

        private int pow(int source, int index) {
            return (int) Math.pow(source, index);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}