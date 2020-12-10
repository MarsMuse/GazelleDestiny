package com.artisan.leetcode.editor.cn;
//实现 strStr() 函数。 
//
// 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如
//果不存在，则返回 -1。 
//
// 示例 1: 
//
// 输入: haystack = "hello", needle = "ll"
//输出: 2
// 
//
// 示例 2: 
//
// 输入: haystack = "aaaaa", needle = "bba"
//输出: -1
// 
//
// 说明: 
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。 
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。 
// Related Topics 双指针 字符串 
// 👍 626 👎 0

/**
 * [28]实现 strStr()
 *
 * @author xzman
 * @since 2020-11-25 17:03:52
 */
public class ImplementStrstr {
    public static void main(String[] args) {
        Solution solution = new ImplementStrstr().new Solution();
        System.out.println(solution.strStr("iii", ""));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int strStr(String haystack, String needle) {
            // 剩余字符串小于目标字符串时直接退出
            if (null == haystack || null == needle) {
                return -1;
            }
            if (haystack.length() < needle.length()) {
                return -1;
            }
            if (haystack.equals(needle)) {
                return 0;
            }
            if (0 == haystack.length()) {
                return -1;
            }
            if (0 == needle.length()) {
                return 0;
            }
            char[] hayArray = haystack.toCharArray();
            char[] needleArray = needle.toCharArray();
            int hayIndex = 0;
            while (hayIndex < hayArray.length) {
                if (hayArray.length - hayIndex < needleArray.length) {
                    return -1;
                }
                if (hayArray[hayIndex] == needleArray[0]) {
                    boolean isExistDiff = false;
                    for (int n = 0; n < needleArray.length; n++) {
                        if (hayArray[hayIndex + n] != needleArray[n]) {
                            isExistDiff = true;
                            break;
                        }
                    }
                    if (!isExistDiff) {
                        return hayIndex;
                    }
                }
                hayIndex++;
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}