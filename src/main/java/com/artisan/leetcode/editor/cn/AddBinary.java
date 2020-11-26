package com.artisan.leetcode.editor.cn;

//给你两个二进制字符串，返回它们的和（用二进制表示）。 
//
// 输入为 非空 字符串且只包含数字 1 和 0。 
//
// 
//
// 示例 1: 
//
// 输入: a = "11", b = "1"
//输出: "100" 
//
// 示例 2: 
//
// 输入: a = "1010", b = "1011"
//输出: "10101" 
//
// 
//
// 提示： 
//
// 
// 每个字符串仅由字符 '0' 或 '1' 组成。 
// 1 <= a.length, b.length <= 10^4 
// 字符串如果不是 "0" ，就都不含前导零。 
// 
// Related Topics 数学 字符串 
// 👍 522 👎 0


/**
 * [67]二进制求和
 *
 * @author xzman
 * @since 2020-11-25 22:29:14
 */
public class AddBinary{
    public static void main(String[] args) {
        Solution solution = new AddBinary().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String addBinary(String a, String b) {
        int length = Math.max(a.length(), b.length());
        int carry = 0;
        while (--length >= 0){

        }
        return "";
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
