package com.huan.dane.dota;

public class Camp extends Building {

    public static int WARRIOR_SHORT = 0;
    public static int WARRIOR_LONG = 1;

    public Camp() {
        this.image = DotaGame.camp;
        this.width = 50;
        this.height = 50;
        this.x = 70;
        this.y = 345;
    }

    private Warrior createWarrior(int type) {
        float createX = this.x + width;
        float createY = this.y;
        if (type == WARRIOR_SHORT) {
            return new ShortWarrior(createX, createY);
        } else if (type == WARRIOR_LONG) {
            return new LongWarrior(createX - 10, createY + 10);
        }
        return null;
    }

    public Warrior[] createWarriors() {
        Warrior[] warriors = new Warrior[2];
        warriors[0] = this.createWarrior(WARRIOR_SHORT);
        warriors[1] = this.createWarrior(WARRIOR_LONG);
        return warriors;
    }
}
