package com.huan.dane.shoot;

import java.util.Random;

public class Bee extends FlyingObject implements Award {
    private int xSpeed = 1;
    private int ySpeed = 2;
    private int awardType;

    public Bee() {
        this.image = ShootGame.bee;
        width = image.getWidth();
        height = image.getHeight();

        y = -height;
        Random random = new Random();
        x = random.nextInt(ShootGame.WIDTH - width);
        awardType = random.nextInt(2);
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    void step() {
        x += xSpeed;
        y += ySpeed;

        if (x > ShootGame.WIDTH - width) {
            xSpeed = -1;
        }
        if (x < width) {
            xSpeed = 1;
        }
    }
}
