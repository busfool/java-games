package com.huan.dane.shoot;

public class Bullet extends FlyingObject {
    private int speed = 3;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
        this.image = ShootGame.bullet;
    }

    @Override
    public void step() {
        y -= speed;
    }

    @Override
    public boolean outOfBounds() {
        return y < -this.height;
    }
}
