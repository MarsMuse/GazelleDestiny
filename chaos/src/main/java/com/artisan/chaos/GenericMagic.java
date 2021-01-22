package com.artisan.chaos;


import java.util.HashSet;
import java.util.Set;

public class GenericMagic {

    public static <S extends Number> void convertLong(S value) {
        int convert = value.intValue();
        System.out.println(convert / 2);
    }

    public static <S> void showRealClass(S value) {

        System.out.println(value.getClass().getName());
    }

    public static <S extends String> void showRealClass(S value) {

        System.out.println(value.getClass().getName());
    }

    public void init() {

    }

    public String test(String a) {
        return a + "test";
    }

    public <S extends T, T> Set<T> unionSet(Set<S> sSet, Set<T> tSet) {
        if (null == sSet && null == tSet) {
            return new HashSet<>();
        }
        if (null == sSet) {
            return tSet;
        }
        if (null == tSet) {
            return new HashSet<>(sSet);
        }
        Set<T> ct = new HashSet<>(sSet.size() + tSet.size());
        ct.addAll(sSet);
        ct.addAll(tSet);
        return ct;
    }


}
