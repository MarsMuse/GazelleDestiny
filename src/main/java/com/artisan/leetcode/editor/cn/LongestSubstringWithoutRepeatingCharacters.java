package com.artisan.leetcode.editor.cn;
//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 4114 👎 0

import java.util.HashMap;
import java.util.Map;

/**
 * [3]无重复字符的最长子串
 *
 * @author xzman
 * @since 2020-08-07 17:39:24
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        System.out.println(solution.lengthOfLongestSubstring("tmmzuxt"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s.length() == 1) {
                return 1;
            }
            Map<Character, Integer> charBucket = new HashMap<>(s.length());
            int startIndex = 0;
            char[] charArray = s.toCharArray();
            int maxBucketSize = 0;
            for (int i = 0; i < charArray.length; i++) {
                char target = charArray[i];
                if (charBucket.containsKey(target)) {
                    maxBucketSize = Math.max(i - startIndex, maxBucketSize);
                    startIndex = charBucket.get(target) + 1;
                }
                charBucket.put(target, i);

                if (i == charArray.length - 1) {
                    maxBucketSize = Math.max(i - startIndex+1, maxBucketSize);
                }
            }
            return maxBucketSize;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}