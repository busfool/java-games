package com.huan.dane.shoot;

public class Airplane extends FlyingObject implements Enemy {
    private int speed = 2;

    @Override
    public int getScore() {
        return 0;
    }
}
