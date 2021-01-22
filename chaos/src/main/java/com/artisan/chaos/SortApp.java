package com.artisan.chaos;

import com.artisan.chaos.model.TestSortInfo;
import com.artisan.leetcode.util.ArrayUtils;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


/**
 * 排序测试
 *
 * @author zou yao
 * @since 2020/12/21 17:41
 */
public class SortApp {

    public static void main(String[] args) {
        //sortUser();
        int[] data = ArrayUtils.getExistFixedValueArray(1000, 10001, 20);
        ArrayUtils.printArray(data);
        insertSOrt(data);
        ArrayUtils.printArray(data);

    }


    private static void sortUser() {
        List<TestSortInfo> testList = buildSortList();
        long start = System.currentTimeMillis();
        List<TestSortInfo> dataList = testList.stream()
                .sorted(Comparator.comparing(TestSortInfo::getId)).collect(Collectors.toList());
        //testList.sort(Comparator.comparing(TestSortInfo::getId));
        System.out.printf("总共耗时：%d\n", System.currentTimeMillis() - start);
        System.out.println(testList);
    }

    private static List<TestSortInfo> buildTestSortList() {
        int length = 200000;
        List<TestSortInfo> dataList = new ArrayList<>();
        Random r = new Random();
        while (length-- >= 0) {
            dataList.add(new TestSortInfo((long) r.nextInt(20000)));
        }
        return dataList;
    }

    private static List<TestSortInfo> buildSortList() {
        int length = 3000;
        List<TestSortInfo> dataList = new ArrayList<>();
        while (length-- >= 0) {
            dataList.add(new TestSortInfo(200000L - length));
        }
        return dataList;
    }

    private static int[] insertSOrt(int[] data) {
        if (null == data || 0 == data.length) {
            return data;
        }
        for (int i = 1; i < data.length; i++) {
            for (int j = i; j > 0; j--) {
                if (data[j] >= data[j - 1]) {
                    break;
                }
                data[j] ^= data[j - 1];
                data[j - 1] ^= data[j];
                data[j] ^= data[j - 1];
            }
        }
        return data;
    }
}
