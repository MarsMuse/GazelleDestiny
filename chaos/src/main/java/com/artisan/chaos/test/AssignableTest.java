package com.artisan.chaos.test;

import com.artisan.chaos.model.BaseResult;
import com.artisan.chaos.model.NormalResult;

public class AssignableTest {

    public static void main(String[] args) {
        System.out.println(BaseResult.class.isAssignableFrom(NormalResult.class));
        System.out.println(NormalResult.class.isAssignableFrom(BaseResult.class));
    }
}
