package com.artisan.leetcode.editor.cn;
//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 示例 1: 
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
// 
//
// 示例 2: 
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
// 
//
// 说明: 
//
// 所有输入只包含小写字母 a-z 。 
// Related Topics 字符串 
// 👍 1357 👎 0

/**
 * [14]最长公共前缀
 *
 * @author xzman
 * @since 2020-11-25 11:06:50
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        Solution solution = new LongestCommonPrefix().new Solution();
        System.out.println(solution.longestCommonPrefix(new String[]{"dog","racecar","car"}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (null == strs || 0 == strs.length) {
                return "";
            }
            if(1 == strs.length){
                return strs[0];
            }
            char[] prefixArray = strs[0].toCharArray();
            int maxLength = prefixArray.length;
            for(int i = 1; i< strs.length ;i++){
                if(null == strs[i] || 0 == strs[i].length()){
                    return "";
                }
                char[] currentChar = strs[i].toCharArray();
                maxLength = Math.min(maxLength, currentChar.length);
                for(int j =0; j< maxLength ; j++){
                    if(prefixArray[j] != currentChar[j]){
                        maxLength = j;
                        break;
                    }
                }
                if( 0 == maxLength){
                    return "";
                }
            }
            return new String(prefixArray,0,maxLength);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}