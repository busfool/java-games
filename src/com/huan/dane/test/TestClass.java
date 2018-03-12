package com.huan.dane.test;

import com.huan.dane.shoot.ShootGame;

import java.util.Arrays;

public class TestClass {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {6, 6, 6};

        System.arraycopy(arr2, 1, arr1, 2, 2);
        System.out.println(Arrays.toString(arr1));
    }
}
