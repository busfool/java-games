package com.huan.dane.dota;

public class ShortWarrior extends Warrior {

    float xSpeed = 1;
    int ySpeed = 1;

    public ShortWarrior(float x, float y) {
        this.image = DotaGame.shortWarrior;
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
    public void moveTo(int x, int y) {

    }

}
