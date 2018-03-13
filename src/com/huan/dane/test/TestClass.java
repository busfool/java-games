package com.huan.dane.test;

import com.huan.dane.shoot.ShootGame;

import java.util.Arrays;

public class TestClass {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {6, 6, 6};

        System.arraycopy(arr2, 1, arr1, 2, 2);
        float ratio = 0.00f;
        ratio = 60f / 35f;

        float delta = 0.3f;
        int x = 2;
        x += delta;
        System.out.println(x);

        int a=1;
        int b=2;
        a = a + b;
    }
}
