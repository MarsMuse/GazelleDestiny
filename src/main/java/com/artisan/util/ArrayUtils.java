package com.artisan.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.StringJoiner;

/**
 * 数组工具类
 *
 * @author xz man
 * @since 2020/11/25 16:13
 */
public class ArrayUtils {

    /**
     * 输出数组
     *
     * @author zou yao
     * @since 2020/11/25 16:16
     */
    public static void printArray(int[] data) {
        if (null == data || 0 == data.length) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        StringJoiner joiner = new StringJoiner(",");
        for (int info : data) {
            joiner.add(info + "");
        }
        System.out.print(joiner.toString());
        System.out.print("]\n");
    }

    /**
     * 获取到有序的数组
     *
     * @author zou yao
     * @since 2020/11/25 16:16
     */
    public static int[] getSortAscArray(int length, int duplicatesIndex, int base) {
        Random r1 = new Random();
        int[] data = new int[length];
        for (int i = 0; i < length; i++) {
            if (i % duplicatesIndex == 0) {
                data[i] = base;
                continue;
            }
            base += r1.nextInt(10);
            data[i] = base;
        }
        return data;
    }

    /**
     * 获取到存在固定值的数据
     *
     * @author zou yao
     * @since 2020/11/25 16:19
     */
    public static int[] getExistFixedValueArray(int length, int fixedIndex, int fixedValue) {
        Random r1 = new Random();
        int[] data = new int[length];
        for (int i = 0; i < length; i++) {
            if (i % fixedIndex == 0) {
                data[i] = fixedValue;
                continue;
            }
            data[i] = r1.nextInt(10);
        }
        return data;
    }

    public static int binarySearch(int[] srcArray, int des) {
        //定义初始最小、最大索引
        int start = 0;
        int end = srcArray.length - 1;
        //确保不会出现重复查找，越界
        while (start <= end) {
            //计算出中间索引值
            int middle = (end + start)>>>1 ;//防止溢出
            if (des == srcArray[middle]) {
                return middle;
                //判断下限
            } else if (des < srcArray[middle]) {
                end = middle - 1;
                //判断上限
            } else {
                start = middle + 1;
            }
        }
        //若没有，则返回-1
        return -1;
    }

    public static void main(String[] args) {
        Set<Long> a = new HashSet<>();
        a.add(1L);
        Set<Long> b = new HashSet<>();
        b.add(1L);
        b.add(2L);
        System.out.println(b.retainAll(a));
        char m = 'b' -32;
        System.out.println(m+1);
        System.out.println('0'+1);
    }
}
