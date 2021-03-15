package com.artisan.chaos.loader;

public class ShowLoader {

    public static void main(String[] args) {
        String a = new String("11");
        System.out.println(a.getClass().getClassLoader());
        ShowLoader sl = new ShowLoader();
        System.out.println(sl.getClass().getClassLoader());
    }
}
