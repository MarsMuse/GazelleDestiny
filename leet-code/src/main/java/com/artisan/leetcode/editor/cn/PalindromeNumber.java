package com.artisan.leetcode.editor.cn;

//判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。 
//
// 示例 1: 
//
// 输入: 121
//输出: true
// 
//
// 示例 2: 
//
// 输入: -121
//输出: false
//解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
// 
//
// 示例 3: 
//
// 输入: 10
//输出: false
//解释: 从右向左读, 为 01 。因此它不是一个回文数。
// 
//
// 进阶: 
//
// 你能不将整数转为字符串来解决这个问题吗？ 
// Related Topics 数学 
// 👍 1320 👎 0


/**
 * [9]回文数
 *
 * @author xzman
 * @since 2020-11-24 22:30:19
 */
public class PalindromeNumber{
    public static void main(String[] args) {
        Solution solution = new PalindromeNumber().new Solution();
        // TO TEST
        System.out.println(solution.isPalindrome(-121));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isPalindrome(int x) {
        if(0 > x){
            return false;
        }
        if(0 == x){
            return true;
        }
        int temp = x;
        int reverse = 0;
        while (temp > 0){
            reverse = reverse*10+ temp%10;
            temp /=10;
        }
        return x == reverse;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
