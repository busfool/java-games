package com.huan.dane.dota;

public class LongWarrior extends Warrior {
    float xSpeed = 1;
    float ySpeed = 1;

    public LongWarrior(float x, float y) {
        this.image = DotaGame.longWarrior;
        this.width = 20;
        this.height = 20;
        this.x = x;
        this.y = y;
    }

    @Override
    public void moveTo() {
        x += xSpeed;
        y -= ySpeed;
    }

    @Override
    public void moveTo(int x, int y) {

    }
}
