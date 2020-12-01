package com.artisan.leetcode.editor.cn;
//给定一个正整数，返回它在 Excel 表中相对应的列名称。 
//
// 例如， 
//
//     1 -> A
//    2 -> B
//    3 -> C
//    ...
//    26 -> Z
//    27 -> AA
//    28 -> AB 
//    ...
// 
//
// 示例 1: 
//
// 输入: 1
//输出: "A"
// 
//
// 示例 2: 
//
// 输入: 28
//输出: "AB"
// 
//
// 示例 3: 
//
// 输入: 701
//输出: "ZY"
// 
// Related Topics 数学 
// 👍 289 👎 0

/**
 * [168]Excel表列名称
 *
 * @author xzman
 * @since 2020-12-01 18:25:32
 */
public class ExcelSheetColumnTitle {
    public static void main(String[] args) {
        Solution solution = new ExcelSheetColumnTitle().new Solution();
        for (int i = 1; i < 10000; i++) {
            System.out.printf("%d-->%s\n", i, solution.convertToTitle(i));
        }
        System.out.println(solution.convertToTitle(26));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String convertToTitle(int n) {
            if (n < 1) {
                return null;
            }
            StringBuilder result = new StringBuilder();
            while (n > 0) {
                result.insert(0, (char) ((n - 1) % 26 + 65));
                n = (n - 1) / 26;
            }
            return result.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}