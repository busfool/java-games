package com.huan.dane.shoot;

public class Bee extends FlyingObject implements Award {
    private int xSpeed = 1;
    private int ySpeed = 2;
    private int awardType;

    @Override
    public int getType() {
        return 0;
    }
}
