package com.artisan.leetcode.editor.cn;

//给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。 
//
// 
//
// 在杨辉三角中，每个数是它左上方和右上方的数的和。 
//
// 示例: 
//
// 输入: 3
//输出: [1,3,3,1]
// 
//
// 进阶： 
//
// 你可以优化你的算法到 O(k) 空间复杂度吗？ 
// Related Topics 数组 
// 👍 199 👎 0


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * [119]杨辉三角 II
 *
 * @author xzman
 * @since 2020-11-27 08:04:31
 */
public class PascalsTriangleIi {
    public static void main(String[] args) {
        Solution solution = new PascalsTriangleIi().new Solution();
        // TO TEST
        for (int i = 0; i < 10; i++) {
            System.out.println(solution.getRow(i));
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> getRow(int rowIndex) {
            if (0 > rowIndex) {
                return Collections.emptyList();
            }
            if (0 == rowIndex) {
                return Collections.singletonList(1);
            }
            Integer[] rowArray = new Integer[rowIndex + 1];
            Integer prev = null;
            for (int row = 1; row <= rowIndex; row++) {
                int midIndex = row >>> 1;
                int midCorrect = row % 2;
                // 如果没有到最后一行则不需要计算后面一半的值
                int loop = row == rowIndex ? rowIndex : midIndex + 1;
                for (int index = 0; index <= loop; index++) {
                    if (0 == index) {
                        rowArray[index] = 1;
                        prev = 1;
                        continue;
                    }
                    if (index > midIndex) {
                        rowArray[index] = rowArray[2 * midIndex - index + midCorrect];
                        continue;
                    }
                    // swap add
                    prev ^= rowArray[index];
                    rowArray[index] ^= prev;
                    prev ^= rowArray[index];
                    rowArray[index] += prev;
                }
            }
            return Arrays.asList(rowArray);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
