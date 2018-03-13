package com.huan.dane.dota;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Warrior extends BaseObject implements Move {
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

    Timer mTimer = new Timer();
    Random random = new Random();

    public Warrior() {
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                int x = random.nextInt(DotaGame.mWidth);
                int y = random.nextInt(DotaGame.mHeight);
                setTarget(x, y);
            }
        }, 1000, 1000);
    }

    private void setTarget(int x, int y) {
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
