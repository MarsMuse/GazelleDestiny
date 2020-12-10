package com.artisan.leetcode.editor.cn;
//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1: 
//
// 输入: [3,2,3]
//输出: 3 
//
// 示例 2: 
//
// 输入: [2,2,1,1,1,2,2]
//输出: 2
// 
// Related Topics 位运算 数组 分治算法 
// 👍 808 👎 0

import java.util.HashMap;
import java.util.Map;

/**
 * [169]多数元素
 *
 * @author xzman
 * @since 2020-12-01 18:53:24
 */
public class MajorityElement {
    public static void main(String[] args) {
        Solution solution = new MajorityElement().new Solution();
        System.out.println(solution.majorityElement(new int[]{3, 2, 3}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int majorityElement(int[] nums) {
            if (1 == nums.length) {
                return nums[0];
            }
            Map<Integer, Integer> numberMap = new HashMap<>(nums.length / 2);
            for (Integer number : nums) {
                if (!numberMap.containsKey(number)) {
                    numberMap.put(number, 1);
                    continue;
                }
                int count = numberMap.get(number);
                if (++count > nums.length / 2) {
                    return number;
                }
                numberMap.put(number, count);
            }
            return 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}