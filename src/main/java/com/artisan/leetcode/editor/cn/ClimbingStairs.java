package com.artisan.leetcode.editor.cn;
//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划 
// 👍 1346 👎 0

/**
 * [70]爬楼梯
 *
 * @author xzman
 * @since 2020-11-26 11:54:48
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new ClimbingStairs().new Solution();
        int n = 40;
        while (n > 0) {
            System.out.printf("-->more 台阶数量：%d，走法：%d\n", n, solution.climbStairs(n));
            System.out.printf("-->less 台阶数量：%d，走法：%d\n", n, solution.less25StepClimbStairs(n));
            n--;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int climbStairs(int n) {
            if (0 == n || 1 == n || 2 == n) {
                return n;
            }

            int prev = 1;
            int current = 2;
            while (n-- > 2) {
                prev += current;
                current ^= prev;
                prev ^= current;
                current ^= prev;
            }
            return current;
        }

        public int less25StepClimbStairs(int n) {
            if (0 == n || 1 == n) {
                return n;
            }
            int num = 0;
            int k = 0;
            while (n >= k) {
                num += combination(n, k);
                k++;
                n--;
            }
            return num;
        }

        private int combination(int n, int k) {
            if (0 == k || k == n) {
                return 1;
            }
            return (int) (factorial(n) / factorial(n - k) / factorial(k));
        }

        private double factorial(int n) {
            if (0 == n) {
                return 1;
            }
            double x = 1;
            while (n > 0) {
                x *= n;
                n--;
            }
            return x;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}