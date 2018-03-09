package com.huan.dane.shoot;

import java.awt.image.BufferedImage;

public class Hero extends FlyingObject {
    protected BufferedImage[] images = {};
    protected int index = 0;

    private int doubleFire;
    private int life;

    public Hero() {
        life = 3;
        doubleFire = 0;
        this.image = ShootGame.hero0;
        images = new BufferedImage[]{ShootGame.hero0, ShootGame.hero1};
        width = image.getWidth();
        height = image.getHeight();
        x = 150;
        y = 400;
    }

    @Override
    void step() {
        if (images.length > 0) {
            image = images[index++ / 10 % images.length];
        }
    }

    public Bullet[] shoot() {
        int xStep = width / 4;
        int yStep = 20;
        if (doubleFire > 0) {
            Bullet[] bullets = new Bullet[2];
            bullets[0] = new Bullet(x + xStep, y - yStep);
            bullets[1] = new Bullet(x + 3 * xStep, y - yStep);
            doubleFire -= 2;
            return bullets;
        } else {
            Bullet[] bullets = new Bullet[1];
            bullets[0] = new Bullet(x + 2 * xStep, y - yStep);
            return bullets;
        }
    }

    public void moveTo(int x, int y) {
        this.x = x - width / 2;
        this.y = y - height / 2;
    }
}
