package com.artisan.leetcode.editor.cn;
//将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。 
//
// 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下： 
//
// L   C   I   R
//E T O E S I I G
//E   D   H   N
// 
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。 
//
// 请你实现这个将字符串进行指定行数变换的函数： 
//
// string convert(string s, int numRows); 
//
// 示例 1: 
//
// 输入: s = "LEETCODEISHIRING", numRows = 3
//输出: "LCIRETOESIIGEDHN"
// 
//
// 示例 2: 
//
// 输入: s = "LEETCODEISHIRING", numRows = 4
//输出: "LDREOEIIECIHNTSG"
//解释:
//
//L     D     R
//E   O E   I I
//E C   I H   N
//T     S     G 
// Related Topics 字符串 
// 👍 914 👎 0

import java.util.ArrayList;
import java.util.List;

/**
 * [6]Z 字形变换
 *
 * @author xzman
 * @since 2020-11-24 10:00:14
 */ 
public class ZigzagConversion{
    public static void main(String[] args) {
        Solution solution = new ZigzagConversion().new Solution();
        System.out.println(solution.convert("LEETCODEISHIRING", 4));
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String convert(String s, int numRows) {
        if(null == s || 0== s.length() || 1 == numRows){
            return s;
        }
        char[] sourceArray = s.toCharArray();
        List<List<Integer>> result = indexDispatcher(s.length(), numRows);
        StringBuilder builder = new StringBuilder();
        for(List<Integer> indexArray : result){
            for(Integer index:indexArray){
                builder.append(sourceArray[index]);
            }
        }
        return builder.toString();
    }

    private List<List<Integer>> indexDispatcher(int length, int rows) {
        List<List<Integer>> resultList = new ArrayList<>(rows);
        for(int i= 0;i< rows;i++){
            resultList.add(new ArrayList<>());
        }
        for (int i = 0; i < length; i++) {
            if (0 == i / (rows - 1) % 2) {
                int index = i % (rows - 1);
                // 正序
                List<Integer> indexArray = resultList.get(index);
                if(null == indexArray){
                    indexArray = new ArrayList<>();
                    resultList.add(index, indexArray);
                }
                indexArray.add(i);
            }else{
                int index = (rows-1) - i % (rows - 1);
                // 正序
                List<Integer> indexArray = resultList.get(index);
                if(null == indexArray){
                    indexArray = new ArrayList<>();
                    resultList.add(index, indexArray);
                }
                indexArray.add(i);
            }
        }
        return resultList;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}