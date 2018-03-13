package com.huan.dane.dota;

public class Hero extends MovingObject {

    private float bSpeed = 1;
    float xSpeed = 0;
    float ySpeed = 0;

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
        // 不用while
        if (Math.abs(this.x - x) > 1) {
            this.x = this.x + xSpeed;
        }
        if (Math.abs(this.y - y) > 1) {
            this.y = this.y + ySpeed;
        }
    }

    public void setTarget(int x, int y) {
        this.setTempX(x);
        this.setTempY(y);
        //计算速度，保留位数
        float ratio = (float) Math.abs(this.x - x) / (float) Math.abs(this.y - y);
        if (ratio > 1) {
            xSpeed = bSpeed;
            ySpeed = (float) (Math.round((bSpeed / ratio) * 100)) / 100;
        } else if (ratio < 1) {
            xSpeed = (float) (Math.round((bSpeed * ratio) * 100)) / 100;
            ySpeed = bSpeed;
        } else {
            xSpeed = bSpeed;
            ySpeed = bSpeed;
        }

        if (this.x > x) {
            xSpeed = -xSpeed;
        }

        if (this.y > y) {
            ySpeed = -ySpeed;
        }

        System.out.println("xSpeed =" + xSpeed + " ySpeed =" + ySpeed);
    }
}
