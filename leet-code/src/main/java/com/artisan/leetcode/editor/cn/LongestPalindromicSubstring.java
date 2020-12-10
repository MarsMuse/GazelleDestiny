package com.artisan.leetcode.editor.cn;
//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。 
//
// 示例 1： 
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
// 
//
// 示例 2： 
//
// 输入: "cbbd"
//输出: "bb"
// 
// Related Topics 字符串 动态规划 
// 👍 2927 👎 0

import java.util.HashMap;
import java.util.Map;

/**
 * [5]最长回文子串
 *
 * @author xzman
 * @since 2020-11-23 21:02:29
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubstring().new Solution();
        System.out.println(solution.longestPalindrome("aacabdkacaa"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestPalindrome(String s) {
            if (null == s || 0 == s.length()) {
                return s;
            }
            char[] sourceArray = s.toCharArray();
            int maxLength = 0;
            Character targetChar = null;
            int targetCharEndIndex = -1;
            Map<Character, Integer> charMap = new HashMap<>(sourceArray.length);
            for (int i = 0; i < sourceArray.length; i++) {
                Character info = sourceArray[i];
                if (charMap.containsKey(info)) {
                    int tempLength = i - charMap.get(info);
                    if (tempLength > maxLength) {
                        targetChar = info;
                        targetCharEndIndex = i;
                        maxLength = tempLength;
                    }
                    continue;
                }
                charMap.put(info, i);
            }
            if (null != targetChar) {
                return new String(sourceArray, charMap.get(targetChar), targetCharEndIndex - charMap.get(targetChar) + 1);
            }
            return String.valueOf(sourceArray[0]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}