package com.artisan.leetcode.editor.cn;

//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。 
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。 
//
// 问总共有多少条不同的路径？ 
//
// 
//
// 例如，上图是一个7 x 3 的网格。有多少可能的路径？ 
//
// 
//
// 示例 1: 
//
// 输入: m = 3, n = 2
//输出: 3
//解释:
//从左上角开始，总共有 3 条路径可以到达右下角。
//1. 向右 -> 向右 -> 向下
//2. 向右 -> 向下 -> 向右
//3. 向下 -> 向右 -> 向右
// 
//
// 示例 2: 
//
// 输入: m = 7, n = 3
//输出: 28 
//
// 
//
// 提示： 
//
// 
// 1 <= m, n <= 100 
// 题目数据保证答案小于等于 2 * 10 ^ 9 
// 
// Related Topics 数组 动态规划 
// 👍 774 👎 0


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * [62]不同路径
 *
 * @author xzman
 * @since 2020-12-09 06:37:54
 */
public class UniquePaths {
    public static void main(String[] args) {
        Solution solution = new UniquePaths().new Solution();
        Random r = new Random();
        for (int i = 1; i < 200; i++) {
            int m = r.nextInt(20) + 2;
            int n = r.nextInt(20) + 1;
            long start = System.currentTimeMillis();
            System.out.printf("第%d次，m为：%d，n为：%d，结果为%d\n", i, m, n, solution.uniquePaths(m, n));
            System.out.printf("耗时%d\n", System.currentTimeMillis() - start);
        }
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private final List<Integer> pathStorage = new ArrayList<>();

        public int uniquePathsRecursion(int m, int n) {
            if (1 == m || 1 == n) {
                return 1;
            } else {
                return uniquePathsRecursion(m - 1, n) + uniquePathsRecursion(m, n - 1);
            }
        }

        public int uniquePaths(int m, int n) {
            if (1 == m || 1 == n) {
                return 1;
            }
            if (m < n) {
                m ^= n;
                n ^= m;
                m ^= n;
            }
            if (pathStorage.size() < getPathStorageCapacity(m, n)) {
                preInitPath(pathStorage.size(), getPathStorageCapacity(m, n));
            }
            return pathStorage.get(getPathStorageCapacity(m, n) - 1);
        }

        private void preInitPath(int startIndex, int capacity) {
            for (int index = startIndex; index < capacity; index++) {
                int currentM = getMByIndex(index);
                int currentN = getNByIndexAndM(currentM, index);
                if (1 == currentN) {
                    pathStorage.add(1);
                    continue;
                }

                if (2 == currentN) {
                    pathStorage.add(currentM);
                    continue;
                }

                if (currentM == currentN) {
                    pathStorage.add(2 * pathStorage.get(index - 1));
                    continue;
                }
                pathStorage.add(pathStorage.get(getPathStorageCapacity(currentM - 1, currentN) - 1) + pathStorage.get(index - 1));
            }
        }

        private int getMByIndex(int index) {
            int start = 2;
            while (true) {
                if ((index + 1) <= (start * (start + 1) / 2 - 1)) {
                    return start;
                }
                start++;
            }
        }

        private int getNByIndexAndM(int m, int index) {
            if (2 == m) {
                return index + 1;
            }
            return index - m * (m - 1) / 2 + 2;
        }


        private int getPathStorageCapacity(int m, int n) {
            return m * (m - 1) / 2 + n - 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
