package com.huan.dane.dota;

public abstract class MovingObject extends BaseObject {

    float bSpeed = 1;
    float xSpeed = 0;
    float ySpeed = 0;

    int targetX;
    int targetY;

    public int getTargetX() {
        return targetX;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    public void move() {
        int x = this.getTargetX();
        int y = this.getTargetY();
        if (Math.abs(this.x - x) > 1) {
            this.x = this.x + xSpeed;
        }
        if (Math.abs(this.y - y) > 1) {
            this.y = this.y + ySpeed;
        }
    }

    public void moveTo(int x, int y) {
        this.setTargetX(x);
        this.setTargetY(y);
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
    }

    public boolean isMoving() {
        if (Math.abs(this.x - this.getTargetX()) <= 1 && Math.abs(this.y - this.getTargetY()) <= 1) {
            return false;
        } else {
            return true;
        }
    }

    public void stopMoving() {
        if (isMoving()) {
            this.setTargetX((int) this.x);
            this.setTargetY((int) this.y);
        }
    }
}
