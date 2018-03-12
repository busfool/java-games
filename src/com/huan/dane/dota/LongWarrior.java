package com.huan.dane.dota;

public class LongWarrior extends Warrior {
    int xSpeed = 1;
    int ySpeed = 1;

    public LongWarrior(int x, int y) {
        this.image = DotaGame.longWarrior;
        this.width = 20;
        this.height = 20;
        this.x = x;
        this.y = y;
    }

    @Override
    public void move() {
        x += xSpeed;
        y -= ySpeed;
    }

    @Override
    public void move(int x, int y) {

    }
}
