package com.huan.dane.test;

import com.huan.dane.shoot.ShootGame;

public class TestClass {
    public static void main(String[] args) {
        String path = ShootGame.class.getResource("/bee.png").getPath();
        System.out.print(path);
    }
}
