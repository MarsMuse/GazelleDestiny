package com.artisan.chaos;

/**
 * 获取到最小差距
 *
 * @author zou yao
 * @since 2021/4/7 14:33
 */
public class SearchMinGap {

    public static void main(String[] args) {
        SearchMinGap sm = new SearchMinGap();
        int[] array = {10, 7, 20, 56, 50, 99, 200, 102};
        System.out.println(sm.minGap(array));
    }

    private int minGap(int[] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j + 1];
                    data[j + 1] = data[j];
                    data[j] = temp;
                }
            }
        }
        int result = data[1] - data[0];
        for (int i = 1; i < data.length - 1; i++) {
            if (result > data[i + 1] - data[i]) {
                result = data[i + 1] - data[i];
            }
        }
        return result;
    }
}
