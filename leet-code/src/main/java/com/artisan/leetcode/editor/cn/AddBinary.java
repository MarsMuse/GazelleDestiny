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


import java.util.Random;

/**
 * [67]二进制求和
 *
 * @author xzman
 * @since 2020-11-25 22:29:14
 */
public class AddBinary {
    public static void main(String[] args) {
        Solution solution = new AddBinary().new Solution();
        // TO TEST
        String a = getBinaryStr(2);
        String b = getBinaryStr(5);
        System.out.println(a);
        System.out.println(b);
        System.out.println(solution.addBinary(a, b));


    }

    static String getBinaryStr(int length) {
        Random r = new Random();
        StringBuilder builder = new StringBuilder("1");
        while (--length > 0) {
            builder.append(r.nextInt(2));
        }
        return builder.toString();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String addBinary(String a, String b) {

            char zeroChar = '0';
            char oneChar = '1';
            int aLength = a.length();
            int bLength = b.length();
            int length = Math.max(a.length(), b.length());
            char[] result = new char[length];
            int carry = 0;
            while (--length >= 0) {
                aLength--;
                bLength--;
                // 累加
                if (aLength >= 0 && oneChar == a.charAt(aLength)) {
                    carry++;
                }
                if (bLength >= 0 && oneChar == b.charAt(bLength)) {
                    carry++;
                }
                // 赋值
                switch (carry) {
                    case 3:
                        carry = 1;
                        result[length] = oneChar;
                        break;
                    case 2:
                        carry = 1;
                        result[length] = zeroChar;
                        break;
                    case 1:
                        carry = 0;
                        result[length] = oneChar;
                        break;
                    default:
                        carry = 0;
                        result[length] = zeroChar;
                        break;
                }
            }
            if (1 == carry) {
                return 1 + new String(result);
            }
            return new String(result);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
