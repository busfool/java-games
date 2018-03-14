package com.huan.dane.dota;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Hero extends MovingObject implements Skill {

    private boolean performOne = false;
    private boolean performTwo = false;

    public boolean isPerformOne() {
        return performOne;
    }

    public void setPerformOne(boolean performOne) {
        this.performOne = performOne;
    }

    public boolean isPerformTwo() {
        return performTwo;
    }

    public void setPerformTwo(boolean performTwo) {
        this.performTwo = performTwo;
    }

    public Hero(int x, int y) {
        this.image = DotaGame.hero;
        this.width = 30;
        this.height = 30;
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval((int) x + 10, (int) y + 10, width, height);
    }

    /**
     * Press E to 吼
     */
    @Override
    public void skillOne(JPanel panel) {
        stopMoving();
        setPerformOne(true);
    }

    /**
     * Press F to 转
     */
    @Override
    public void skillTwo() {

    }
}
