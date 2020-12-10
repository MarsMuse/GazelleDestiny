package com.artisan.leetcode.util;

import java.util.List;

/**
 * 斐波那契数列
 *
 * @author xz man
 * @since 2020/10/15 09:46
 */
public class FibonacciSequence {

    public List<Integer> getInstance(int factor){

        return null;
    }

    private int getValueByCurrent(int index){

        if(1 == index || 2 == index){
            return 1;
        } else {
            return getValueByCurrent( index -1) + getValueByCurrent(index -2);
        }
    }
}
