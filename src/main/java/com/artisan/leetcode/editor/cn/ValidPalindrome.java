package com.artisan.leetcode.editor.cn;
//给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。 
//
// 说明：本题中，我们将空字符串定义为有效的回文串。 
//
// 示例 1: 
//
// 输入: "A man, a plan, a canal: Panama"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "race a car"
//输出: false
// 
// Related Topics 双指针 字符串 
// 👍 300 👎 0

/**
 * [125]验证回文串
 *
 * @author xzman
 * @since 2020-11-27 17:47:25
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        Solution solution = new ValidPalindrome().new Solution();
        System.out.println(solution.isPalindrome("A 4man, a plan, a canal: Panam4a"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isPalindrome(String s) {
            if (null == s) {
                return false;
            }
            if (0 == s.trim().length()) {
                return true;
            }
            int frontIndex = 0;
            Character frontChar = null;
            int endIndex = s.length() - 1;
            Character endChar = null;
            while (frontIndex < endIndex) {
                while (frontIndex < endIndex) {
                    if (48 <= s.charAt(frontIndex) && s.charAt(frontIndex) <= 57
                            || 65 <= s.charAt(frontIndex) && s.charAt(frontIndex) <= 90) {
                        frontChar = s.charAt(frontIndex);
                        break;
                    }
                    if (97 <= s.charAt(frontIndex) && s.charAt(frontIndex) <= 122) {
                        frontChar = (char) (s.charAt(frontIndex) - 32);
                        break;
                    }
                    frontIndex++;
                }
                while (frontIndex < endIndex) {
                    if (48 <= s.charAt(endIndex) && s.charAt(endIndex) <= 57
                            || 65 <= s.charAt(endIndex) && s.charAt(endIndex) <= 90) {
                        endChar = s.charAt(endIndex);
                        break;
                    }
                    if (97 <= s.charAt(endIndex) && s.charAt(endIndex) <= 122) {
                        endChar = (char) (s.charAt(endIndex) - 32);
                        break;
                    }
                    endIndex--;
                }

                // 判断不相同
                if (null != frontChar && null != endChar && !frontChar.equals(endChar)) {
                    return false;
                }
                // 清空移动指针
                frontIndex++;
                endIndex--;
                frontChar = null;
                endChar = null;
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}