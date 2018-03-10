package com.huan.dane.shoot;

public class Airplane extends FlyingObject implements Enemy {
    private int speed = 2;

    public Airplane() {
        this.image = ShootGame.airplane;
        width = image.getWidth();
        height = image.getHeight();
        y = -height;
        x = (int) (Math.random() * (ShootGame.WIDTH - width));
    }

    @Override
    public int getScore() {
        return 5;
    }

    @Override
    public void step() {
        y += speed;
    }

    @Override
    public boolean outOfBounds() {
        return y > ShootGame.HEIGHT;
    }
}
