package com.huan.dane.dota;

public class Hero extends MovingObject implements Skill {

    public Hero(int x, int y) {
        this.image = DotaGame.hero;
        this.width = 30;
        this.height = 30;
        this.x = x;
        this.y = y;
    }

    /**
     * Press E to 吼
     */
    @Override
    public void skillOne() {

    }

    @Override
    public void jump(int x, int y) {

    }
}
