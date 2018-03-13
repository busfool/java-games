package com.huan.dane.dota;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Warrior extends MovingObject {

    Timer mTimer = new Timer();
    Random random = new Random();

    public Warrior() {
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                int x = random.nextInt(DotaGame.mWidth);
                int y = random.nextInt(DotaGame.mHeight);
                Warrior.this.moveTo(x, y);
            }
        }, 1000, 1000);
    }


}
