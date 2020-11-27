package com.artisan.leetcode.editor.cn;

//给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。 
//
// 
//
// 在杨辉三角中，每个数是它左上方和右上方的数的和。 
//
// 示例: 
//
// 输入: 5
//输出:
//[
//     [1],
//    [1,1],
//   [1,2,1],
//  [1,3,3,1],
// [1,4,6,4,1]
//] 
// Related Topics 数组 
// 👍 377 👎 0


import java.util.ArrayList;
import java.util.List;

/**
 * [118]杨辉三角
 *
 * @author xzman
 * @since 2020-11-27 07:23:16
 */
public class PascalsTriangle {
    public static void main(String[] args) {
        Solution solution = new PascalsTriangle().new Solution();
        List<List<Integer>> result = solution.generate(40);
        result.forEach(System.out::println);
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> generate(int numRows) {
            if (numRows < 1) {
                return new ArrayList<>();
            }
            List<List<Integer>> container = new ArrayList<>(numRows);
            List<Integer> firstRow = new ArrayList<>(1);
            firstRow.add(1);
            container.add(firstRow);
            if (1 == numRows) {
                return container;
            }

            for (int row = 2; row <= numRows; row++) {
                List<Integer> currentRow = new ArrayList<>(row);
                container.add(currentRow);
                List<Integer> referenceRow = container.get(row - 2);
                for (int index = 0; index < row; index++) {
                    if (0 == index || (row - 1) == index) {
                        currentRow.add(1);
                        continue;
                    }
                    currentRow.add(referenceRow.get(index) + referenceRow.get(index - 1));
                }
            }
            return container;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
