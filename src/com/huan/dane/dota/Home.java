package com.huan.dane.dota;

public class Home extends Building {

    private static Home mInstance;

    private Home() {
        this.image = DotaGame.home;
        this.width = 50;
        this.height = 50;
        this.x = 70;
        this.y = 670;
    }

    public static Home newInstance() {
        if (mInstance == null) {
            mInstance = new Home();
        }
        return mInstance;
    }
}
