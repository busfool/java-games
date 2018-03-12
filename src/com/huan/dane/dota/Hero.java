package com.huan.dane.dota;

public class Hero extends MovingObject {

    int xSpeed = 1;
    int ySpeed = 1;

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
        // test move
        x += xSpeed;
    }

    @Override
    public void move(int x, int y) {
        if (this.x <= x) {
            xSpeed = 1;
        } else {
            xSpeed = -1;
        }

        if (this.y <= y) {
            ySpeed = 1;
        } else {
            ySpeed = -1;
        }
        // 一瞬间移动过去了，用while这里不是自己定义的时间
        if (this.x != x) {
            this.x += xSpeed;
            System.out.println("speed" + xSpeed + "x" + this.x + "tempX" + x);
        }
        if (this.y != y) {
            this.y += ySpeed;
        }
    }
}
