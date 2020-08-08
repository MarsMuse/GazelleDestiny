package com.artisan.leetcode.editor.cn;
//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表 
// 👍 8834 👎 0

import java.util.*;

/**
 * [1]两数之和
 *
 * @author xzman
 * @since 2020-08-07 15:00:59
 */
public class TwoSum {
    public static void main(String[] args) {
        Random random = new Random();
        int sizeSeed = 2048*10;

        int loop = 10000;
        while (--loop > 0){
            int size = random.nextInt(sizeSeed);
            if(size <= 100){
                size = 1024;
            }

            int target = random.nextInt(2*size -1);
            if(target <= 100){
                target = 1536;
            }
            System.out.println("此次数组大小为："+size);
            test(size, target);
        }
    }

    private static void test(int size, int target){
        int factor = 2;
        Random random = new Random();
        int frontValue = random.nextInt(size);
        int end = target - frontValue;
        Set<Integer> numSet = new HashSet<>(size);
        numSet.add(frontValue);
        numSet.add(end);
        while (true) {
            numSet.add(random.nextInt(factor * size));
            if (numSet.size() == size) {
                break;
            }
        }
        int[] nums = new int[size];
        int index = 0;
        for (int value : numSet) {
            nums[index] = value;
            index++;
        }
        Solution solution = new TwoSum().new Solution();
        long start = System.currentTimeMillis();
        solution.twoSum(nums, target);
        System.out.printf("耗时：%dms。\n", System.currentTimeMillis() - start);

    }

//leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> numberMap = new HashMap<>();
            int front = 0;
            int end = nums.length - 1;

            while (true) {
                numberMap.put(nums[front], front);
                numberMap.put(nums[end], end);

                int[] subFront = getInts(target, numberMap, front, nums[front]);
                if (subFront != null) {
                    return subFront;
                }
                int[] subEnd1 = getInts(target, numberMap, end, nums[end]);
                if (subEnd1 != null) {
                    return subEnd1;
                }
                front++;
                end--;
                if (front > end) {
                    break;
                }
            }

            return null;
        }

        private int[] getInts(int target, Map<Integer, Integer> numberMap, int end, int num) {
            int subEnd = target - num;
            if (numberMap.containsKey(subEnd)) {
                int subIndex = numberMap.get(subEnd);
                if(end != subIndex){
                    return new int[]{end, subIndex};
                }
            }
            return null;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}