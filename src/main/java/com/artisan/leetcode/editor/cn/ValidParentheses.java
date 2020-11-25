package com.artisan.leetcode.editor.cn;
//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1: 
//
// 输入: "()"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "()[]{}"
//输出: true
// 
//
// 示例 3: 
//
// 输入: "(]"
//输出: false
// 
//
// 示例 4: 
//
// 输入: "([)]"
//输出: false
// 
//
// 示例 5: 
//
// 输入: "{[]}"
//输出: true 
// Related Topics 栈 字符串 
// 👍 2005 👎 0

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * [20]有效的括号
 *
 * @author xzman
 * @since 2020-11-25 13:37:35
 */
public class ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new ValidParentheses().new Solution();
        System.out.println(solution.isValid("([)]"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private final Map<Character, Character> symbolMap = new HashMap<>();

        {
            symbolMap.put(')', '(');
            symbolMap.put(']', '[');
            symbolMap.put('}', '{');
        }

        public boolean isValid(String s) {
            if (null == s) {
                return false;
            }
            if (0 == s.length()) {
                return true;
            }
            if (1 == s.length() % 2) {
                return false;
            }
            Deque<Character> stack = new LinkedBlockingDeque<>(s.length() / 2);
            char[] dataArray = s.toCharArray();
            for (Character info : dataArray) {
                if (symbolMap.containsKey(info)) {
                    Character sc = stack.pollLast();
                    if (!symbolMap.get(info).equals(sc)) {
                        return false;
                    }
                    continue;
                }
                if (!stack.offer(info)) {
                    return false;
                }
            }
            if (!stack.isEmpty()) {
                return false;
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}