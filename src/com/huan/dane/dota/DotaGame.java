package com.huan.dane.dota;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class DotaGame extends JPanel {

    public static BufferedImage background;
    public static BufferedImage home;
    public static BufferedImage hero;

    static {
        try {
            background = ImageIO.read(DotaGame.class.getResource("/dota/background.jpg"));
            home = ImageIO.read(DotaGame.class.getResource("/dota/home.png"));
            hero = ImageIO.read(DotaGame.class.getResource("/dota/hero.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int mWidth = 500;
    public static int mHeight = 465;

    public Home mHome;
    public Hero mHero = new Hero(0, 0);

    public DotaGame() {
        mHome = Home.newInstance();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DotA");
        DotaGame game = new DotaGame();
        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(mWidth, mHeight + 30);
        frame.setVisible(true);
        game.action();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintBackground(g);
        paintBuildings(g);
        paintHero(g);
    }

    private void paintBackground(Graphics g) {
        g.drawImage(DotaGame.background, 0, 0, null);
    }

    private void paintBuildings(Graphics g) {
        g.drawImage(DotaGame.home, mHome.getX(), mHome.getY(), mHome.getWidth(), mHome.getHeight(), null);
    }

    private void paintHero(Graphics g) {
        g.drawImage(DotaGame.hero, mHero.getX(), mHero.getY(), mHero.getWidth(), mHero.getHeight(), null);
    }

    private static int interval = 10;

    private void action() {
        // 用Timer刷新界面
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                moveAction();
                repaint();
            }
        }, interval, interval);

        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int x = e.getX();
                    int y = e.getY();
                    mHero.setTempX(x);
                    mHero.setTempY(y);
                    System.out.println("x = " + x + " y = " + y);
                }

            }
        };

        this.addMouseListener(adapter);
    }

    int moveIndex;

    private void moveAction() {
        moveIndex++;
        if (moveIndex % 2 == 0) {
            mHero.move(mHero.getTempX(), mHero.getTempY());
        }
    }
}
