package com.huan.dane.dota;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class DotaGame extends JPanel {

    public static BufferedImage background;
    public static BufferedImage home;
    public static BufferedImage hero;
    public static BufferedImage camp;
    public static BufferedImage shortWarrior;
    public static BufferedImage longWarrior;

    static {
        try {
            background = ImageIO.read(DotaGame.class.getResource("/dota/background.jpg"));
            home = ImageIO.read(DotaGame.class.getResource("/dota/home.png"));
            hero = ImageIO.read(DotaGame.class.getResource("/dota/hero.png"));
            camp = ImageIO.read(DotaGame.class.getResource("/dota/camp.png"));
            shortWarrior = ImageIO.read(DotaGame.class.getResource("/dota/short_warrior.png"));
            longWarrior = ImageIO.read(DotaGame.class.getResource("/dota/long_warrior.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int mWidth = 800;
    public static int mHeight = 800;

    public Home mHome = Home.newInstance();
    public Hero mHero = new Hero(0, 0);
    public Camp mCamp = new Camp();
    public Warrior[] warriors = new Warrior[]{};

    public DotaGame() {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DotA");
        DotaGame game = new DotaGame();
        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        Insets insets = frame.getInsets();
        frame.setSize(mWidth, mHeight + insets.top);
        game.action();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintBackground(g);
        paintBuildings(g);
        paintHero(g);
        paintWarriors(g);
    }

    private void paintBackground(Graphics g) {
        g.drawImage(DotaGame.background, 0, 0, null);
    }

    private void paintBuildings(Graphics g) {
        g.drawImage(DotaGame.home, (int) mHome.getX(), (int) mHome.getY(), mHome.getWidth(), mHome.getHeight(), null);
        g.drawImage(DotaGame.camp, (int) mCamp.getX(), (int) mCamp.getY(), mCamp.getWidth(), mCamp.getHeight(), null);
    }

    private void paintHero(Graphics g) {
        g.drawImage(DotaGame.hero, (int) mHero.getX(), (int) mHero.getY(), mHero.getWidth(), mHero.getHeight(), null);
    }

    private void paintWarriors(Graphics g) {
        for (int i = 0; i < warriors.length; i++) {
            Warrior w = warriors[i];
            g.drawImage(w.getImage(), (int) w.getX(), (int) w.getY(), w.getWidth(), w.getHeight(), null);
        }
    }

    private static int interval = 10;

    private void action() {
        // 用Timer刷新界面
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                createWarriorsAction();
                moveAction();
                repaint();
            }
        }, interval, interval);

        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                // 有时未调用
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int x = e.getX();
                int y = e.getY();
                // move Hero
                mHero.moveTo(x, y);
                System.out.println("mouse Pressed");
            }
        };
        this.addMouseListener(adapter);
        this.addMouseMotionListener(adapter);
    }

    int moveIndex;

    private void moveAction() {
        moveIndex++;
        if (moveIndex % 2 == 0) {
            mHero.move();
            for (int i = 0; i < warriors.length; i++) {
                warriors[i].move();
            }
        }
    }

    int createWarriorIndex;

    private void createWarriorsAction() {
        createWarriorIndex++;
        if (createWarriorIndex % 100 == 0) {
            // create warriors and add
            Warrior[] newWarriors = mCamp.createWarriors();
            warriors = Arrays.copyOf(warriors, warriors.length + newWarriors.length);//扩容
            System.arraycopy(newWarriors, 0, warriors, warriors.length - newWarriors.length, newWarriors.length);
        }
    }
}
