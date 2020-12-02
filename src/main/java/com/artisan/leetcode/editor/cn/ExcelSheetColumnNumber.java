package com.artisan.leetcode.editor.cn;
//给定一个Excel表格中的列名称，返回其相应的列序号。 
//
// 例如， 
//
//     A -> 1
//    B -> 2
//    C -> 3
//    ...
//    Z -> 26
//    AA -> 27
//    AB -> 28 
//    ...
// 
//
// 示例 1: 
//
// 输入: "A"
//输出: 1
// 
//
// 示例 2: 
//
// 输入: "AB"
//输出: 28
// 
//
// 示例 3: 
//
// 输入: "ZY"
//输出: 701 
//
// 致谢： 
//特别感谢 @ts 添加此问题并创建所有测试用例。 
// Related Topics 数学 
// 👍 188 👎 0

/**
 * [171]Excel表列序号
 *
 * @author xzman
 * @since 2020-12-02 15:26:01
 */
public class ExcelSheetColumnNumber {
    public static void main(String[] args) {
        Solution solution = new ExcelSheetColumnNumber().new Solution();
        System.out.println(solution.titleToNumber("ZY"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int titleToNumber(String s) {
            if (null == s || s.length() == 0) {
                return 0;
            }
            int result = 0;
            for (int i = 0; i < s.length(); i++) {
                result = result * 26 + s.charAt(i) - 64;
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}