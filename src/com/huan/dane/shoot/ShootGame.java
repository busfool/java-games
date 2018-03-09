package com.huan.dane.shoot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ShootGame extends JPanel {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 600;

    public static BufferedImage background;
    public static BufferedImage start;
    public static BufferedImage airplane;
    public static BufferedImage bee;
    public static BufferedImage bullet;
    public static BufferedImage hero0;
    public static BufferedImage hero1;
    public static BufferedImage pause;
    public static BufferedImage gameOver;

    private FlyingObject[] flyings = {};
    private Bullet[] bullets = {};
    private Hero hero = new Hero();

    private Timer timer;
    private int interval = 1000 / 100;

    static {
        try {
            background = ImageIO.read(ShootGame.class.getResource("/background.png"));
            start = ImageIO.read(ShootGame.class.getResource("/start.png"));
            airplane = ImageIO.read(ShootGame.class.getResource("/airplane.png"));
            bee = ImageIO.read(ShootGame.class.getResource("/bee.png"));
            bullet = ImageIO.read(ShootGame.class.getResource("/bullet.png"));
            hero0 = ImageIO.read(ShootGame.class.getResource("/hero0.png"));
            hero1 = ImageIO.read(ShootGame.class.getResource("/hero1.png"));
            pause = ImageIO.read(ShootGame.class.getResource("/pause.png"));
            gameOver = ImageIO.read(ShootGame.class.getResource("/gameOver.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ShootGame() {
        /*
        flyings = new FlyingObject[2];
        flyings[0] = new Bee();
        flyings[1] = new Airplane();
        bullets = new Bullet[1];
        bullets[0] = new Bullet(200, 350);
        */
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, null);
        paintHero(g);
        paintBullets(g);
        paintFlyingObjects(g);
    }

    private void paintHero(Graphics g) {
        g.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);

    }

    private void paintBullets(Graphics g) {
        for (int i = 0; i < bullets.length; i++) {
            Bullet bullet = bullets[i];
            g.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), null);

        }
    }

    private void paintFlyingObjects(Graphics g) {
        for (int i = 0; i < flyings.length; i++) {
            FlyingObject flying = flyings[i];
            g.drawImage(flying.getImage(), flying.getX(), flying.getY(), null);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Fly");
        ShootGame game = new ShootGame();
        frame.add(game);
        frame.setSize(WIDTH, HEIGHT);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.action();
    }

    public static FlyingObject nextOne() {
        Random random = new Random();
        int type = random.nextInt(20);
        if (type == 0 || type == 10) {
            return new Bee();
        } else {
            return new Airplane();
        }
    }

    int flyEnteredIndex = 0;

    public void enterAction() {
        flyEnteredIndex++;
        if (flyEnteredIndex % 40 == 0) {
            FlyingObject object = nextOne();
            flyings = Arrays.copyOf(flyings, flyings.length + 1);
            flyings[flyings.length - 1] = object;
        }
    }

    public void stepAction() {
        for (int i = 0; i < flyings.length; i++) {
            FlyingObject f = flyings[i];
            f.step();
        }

        for (int i = 0; i < bullets.length; i++) {
            Bullet b = bullets[i];
            b.step();
        }
        hero.step();
    }

    public void action() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                enterAction();
                stepAction();
                shootAction();
                repaint();
            }
        }, interval, interval);

        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                hero.moveTo(x, y);
            }
        };
        this.addMouseListener(adapter);
    }

    int shootIndex = 0;

    public void shootAction() {
        shootIndex++;
        if (shootIndex % 30 == 0) {
            Bullet[] bs = hero.shoot();
            bullets = Arrays.copyOf(bullets, bullets.length + bs.length);
            System.arraycopy(bs, 0, bullets, bullets.length - bs.length, bs.length);
        }
    }
}
