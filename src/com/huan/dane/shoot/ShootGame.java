package com.huan.dane.shoot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

    private int score = 0;

    private int state;
    public static final int START = 0;
    public static final int RUNNING = 1;
    public static final int PAUSE = 2;
    public static final int GAME_OVER = 3;

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
        paintScore(g);
        paintState(g);
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

    private void paintScore(Graphics g) {
        int x = 10;
        int y = 25;
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        g.setColor(new Color(0x3A3B3B));
        g.setFont(font);
        g.drawString("Score: " + score, x, y);
        y += 20;
        g.drawString("Life: " + hero.getLife(), x, y);
    }

    private void paintState(Graphics g) {
        switch (state) {
            case START:
                g.drawImage(start, 0, 0, null);
                break;
            case PAUSE:
                g.drawImage(pause, 0, 0, null);
                break;
            case GAME_OVER:
                g.drawImage(gameOver, 0, 0, null);
                break;

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
                bangAction();
                outOfBoundsAction();
                checkGameOverAction();
                repaint();
            }
        }, interval, interval);

        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (state == RUNNING) {
                    int x = e.getX();
                    int y = e.getY();
                    hero.moveTo(x, y);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (state == PAUSE) {
                    state = RUNNING;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (state != GAME_OVER) {
                    state = PAUSE;
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                switch (state) {
                    case START:
                        state = RUNNING;
                        break;
                    case GAME_OVER:
                        flyings = new FlyingObject[0];
                        bullets = new Bullet[0];
                        hero = new Hero();
                        score = 0;
                        state = START;
                        break;
                }
            }
        };
        this.addMouseListener(adapter);
        this.addMouseMotionListener(adapter);
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

    public void bangAction() {
        for (int i = 0; i < bullets.length; i++) {
            Bullet b = bullets[i];
            bang(b);
        }
    }

    private void bang(Bullet bullet) {
        int index = -1;
        for (int i = 0; i < flyings.length; i++) {
            FlyingObject object = flyings[i];
            if (object.shootBy(bullet)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            FlyingObject one = flyings[index];
            FlyingObject temp = flyings[index];
            flyings[index] = flyings[flyings.length - 1];
            flyings[flyings.length - 1] = temp;
            flyings = Arrays.copyOf(flyings, flyings.length - 1);
            if (one instanceof Enemy) {
                Enemy enemy = (Enemy) one;
                score += enemy.getScore();
            }
            if (one instanceof Award) {
                Award award = (Award) one;
                int type = award.getType();
                if (type == Award.DOUBLE_FIRE) {
                    hero.addDoubleFire();
                } else if (type == Award.LIFE) {
                    hero.addLife();
                }
            }
        }
    }

    public void outOfBoundsAction() {
        int index = 0;
        FlyingObject[] flyingLives = new FlyingObject[flyings.length];
        for (int i = 0; i < flyings.length; i++) {
            if (!flyings[i].outOfBounds()) {
                flyingLives[index] = flyings[i];
                index = index + 1;
            }
        }
        flyings = Arrays.copyOf(flyingLives, index);
        index = 0;
        Bullet[] bulletLives = new Bullet[bullets.length];
        for (int i = 0; i < bullets.length; i++) {
            if (!bullets[i].outOfBounds()) {
                bulletLives[index] = bullets[i];
                index = index + 1;
            }
        }
    }

    public boolean isGameOver() {
        for (int i = 0; i < flyings.length; i++) {
            int index = -1;
            FlyingObject object = flyings[i];
            if (hero.hit(object)) {
                hero.subtractLife();
                hero.setDoubleFire(0);
                index = i;
            }
            if (index != -1) {
                FlyingObject temp = flyings[index];
                flyings[index] = flyings[flyings.length - 1];
                flyings[flyings.length - 1] = temp;
                flyings = Arrays.copyOf(flyings, flyings.length - 1);
            }
        }

        return hero.getLife() <= 0;
    }

    public void checkGameOverAction() {
        if (isGameOver()) {
            state = GAME_OVER;
        }
    }
}
