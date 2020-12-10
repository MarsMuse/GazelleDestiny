package com.artisan.leetcode.editor.cn;
//给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。 
//
// 如果可以完成上述分割，则返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 输入: [1,2,3,3,4,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 : 
//1, 2, 3
//3, 4, 5
// 
//
// 
//
// 示例 2： 
//
// 输入: [1,2,3,3,4,4,5,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 : 
//1, 2, 3, 4, 5
//3, 4, 5
// 
//
// 
//
// 示例 3： 
//
// 输入: [1,2,3,4,4,5]
//输出: False
// 
//
// 
//
// 提示： 
//
// 
// 输入的数组长度范围为 [1, 10000] 
// 
//
// 
// Related Topics 堆 贪心算法 
// 👍 209 👎 0

import java.util.ArrayList;
import java.util.List;

/**
 * [659]分割数组为连续子序列
 *
 * @author xzman
 * @since 2020-12-04 16:45:37
 */
public class SplitArrayIntoConsecutiveSubsequences {
    public static void main(String[] args) {
        Solution solution = new SplitArrayIntoConsecutiveSubsequences().new Solution();
        System.out.println(solution.isPossible(new int[]{2, 5, 5, 5, 6, 7, 8, 8, 8, 9}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 最小序列长度
        private final int minSequenceLength = 3;

        public boolean isPossible(int[] nums) {
            if (nums.length < minSequenceLength) {
                return false;
            }
            List<SequenceInfo> sequenceList = new ArrayList<>();
            int prevNumberCount = 0;
            int currentNumberCount = 0;
            int checkNumber = nums[0];
            for (int index = 0; index < nums.length; index++) {
                int value = nums[index];
                if (checkNumber == value) {
                    currentNumberCount++;
                } else if (checkNumber + 1 == value) {
                    if (prevNumberCount == currentNumberCount) {
                        sequenceLengthInc(sequenceList, currentNumberCount);
                    } else if (currentNumberCount > prevNumberCount) {
                        sequenceLengthInc(sequenceList, prevNumberCount);
                        addSequence(sequenceList, currentNumberCount - prevNumberCount);
                    } else {
                        if (!checkEndSequence(sequenceList, prevNumberCount, prevNumberCount - currentNumberCount)) {
                            return false;
                        }
                        sequenceLengthInc(sequenceList, currentNumberCount);
                    }
                    prevNumberCount = currentNumberCount;
                    checkNumber = value;
                    currentNumberCount = 1;
                } else {
                    if (currentNumberCount > prevNumberCount) {
                        addSequence(sequenceList, currentNumberCount - prevNumberCount);
                    }
                    sequenceLengthInc(sequenceList, currentNumberCount);
                    if (!checkEndSequence(sequenceList, Math.max(currentNumberCount, prevNumberCount),
                            Math.max(currentNumberCount, prevNumberCount))) {
                        return false;
                    }
                    prevNumberCount = 0;
                    checkNumber = value;
                    currentNumberCount = 1;
                }

                if (index == nums.length - 1) {
                    if (currentNumberCount > prevNumberCount) {
                        return false;
                    }
                    sequenceLengthInc(sequenceList, currentNumberCount);
                    return checkEndSequence(sequenceList, prevNumberCount, prevNumberCount);
                }
            }
            return true;
        }


        private boolean checkEndSequence(List<SequenceInfo> sequenceList, int length, int endLength) {
            while (endLength-- > 0) {
                SequenceInfo si = sequenceList.get(sequenceList.size() - length + endLength);
                if (si.length < minSequenceLength) {
                    return false;
                }
            }
            return true;
        }

        private void sequenceLengthInc(List<SequenceInfo> sequenceList, int length) {
            if (length == 0) {
                return;
            }
            do {
                SequenceInfo si = sequenceList.get(sequenceList.size() - length);
                si.length = 1 + si.length;
            } while (--length > 0);
        }

        private void addSequence(List<SequenceInfo> sequenceList, int length) {
            while (length-- > 0) {
                sequenceList.add(new SequenceInfo(1));
            }
        }

        class SequenceInfo {
            private int length;

            public SequenceInfo(int length) {
                this.length = length;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}