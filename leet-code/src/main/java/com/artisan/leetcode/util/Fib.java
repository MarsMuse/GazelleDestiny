package com.artisan.leetcode.util;

/**
 * 斐波那契数列
 *
 * @author xzman
 * @since 2020/11/23 下午9:35
 */
public class Fib {

    public static long get(int length) {
        long temp;
        if (length <= 1) {
            temp = 1;
        } else {
            temp = get(length - 1) + get(length - 2);
        }
        System.out.println(temp);
        return temp;
    }

    public static long getByAdd(int length) {
        if (length <= 1) {
            return 1;
        }
        long front = 1;
        long end = 1;
        long answer = 1;
        long startTime = System.currentTimeMillis();
        for (int i = 2; i <= length; i++) {
            answer = front + end;
            front = end;
            end = answer;
        }

        System.out.printf("总共耗时%d\n", System.currentTimeMillis() - startTime);
        return answer;
    }

    public static long getByAddSwap(int length) {
        if (length <= 1) {
            return 1;
        }
        long front = 1;
        long end = 1;

        long startTime = System.currentTimeMillis();
        for (int i = 2; i <= length; i++) {
            front += end;
            front = front ^ end;
            end = end ^ front;
            front = front ^ end;
        }
        System.out.printf("SWAP总共耗时%d\n", System.currentTimeMillis() - startTime);
        return end;
    }

    public static void main(String[] args) {
        System.out.println(getByAdd(1000));
        System.out.println(getByAddSwap(1000));
    }
}
