package com.huan.dane.dota;

public class Hero extends MovingObject {

    private float bSpeed = 1;
    float xSpeed = 1;
    float ySpeed = 1;

    int tempX;
    int tempY;

    public int getTempX() {
        return tempX;
    }

    public void setTempX(int tempX) {
        this.tempX = tempX;
    }

    public int getTempY() {
        return tempY;
    }

    public void setTempY(int tempY) {
        this.tempY = tempY;
    }

    public Hero(int x, int y) {
        this.image = DotaGame.hero;
        this.width = 30;
        this.height = 30;
        this.x = x;
        this.y = y;
    }

    @Override
    public void move() {
    }

    @Override
    public void move(int x, int y) {
        int x1 = x - width / 2;
        int y1 = y - height / 2;
        if (this.x <= x1) {
            xSpeed = bSpeed;
        } else {
            xSpeed = -bSpeed;
        }

        if (this.y <= y1) {
            ySpeed = bSpeed;
        } else {
            ySpeed = -bSpeed;
        }
        // 一瞬间移动过去了，用while这里不是自己定义的时间
        if (this.x != x1) {
            this.x += xSpeed;
        }
        if (this.y != y1) {
            this.y += ySpeed;
        }
    }
}
