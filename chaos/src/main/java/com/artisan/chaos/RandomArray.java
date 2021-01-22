package com.artisan.chaos;

public class RandomArray {

    public static void main(String[] args) {
        int[] array = new int[7];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 10 + 1);
            System.out.println(array[i]);
        }
    }
}
