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
        if (isPerformOne()) {
            g.setColor(Color.WHITE);
            g.drawArc((int) (x - maxRange / 2), (int) (y - maxRange / 2), maxRange, maxRange, 0, 360);
        }
    }

    public void handle(DotaGame game) {
        if (isPerformOne()) {
            Warrior[] warriors = game.warriors;
            for (int i = 0; i < warriors.length; i++) {
                Warrior w = warriors[i];
                if (inRange(w.getX(), w.getY())) {
                    w.cancelTimer();
                    w.moveTo((int) this.x, (int) this.y);
                } else {
                }
            }
        }
    }

    private int range;
    private int minRange = 20;
    private int maxRange = 100;

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    private boolean inRange(float x, float y) {
        float a = this.x - x;
        float b = this.y - y;
        double len = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        if (len < maxRange / 2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Press E to 吼
     */
    @Override
    public void skillOne(JPanel panel) {
        stopMoving();
        setPerformOne(true);
        // set skill data
    }

    /**
     * Press F to 转
     */
    @Override
    public void skillTwo() {

    }
}
