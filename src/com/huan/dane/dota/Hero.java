package com.huan.dane.dota;

public class Hero extends MovingObject {

    private float bSpeed = 1;
    float xSpeed = 0;
    float ySpeed = 0;

    public Hero(int x, int y) {
        this.image = DotaGame.hero;
        this.width = 30;
        this.height = 30;
        this.x = x;
        this.y = y;
    }

}
