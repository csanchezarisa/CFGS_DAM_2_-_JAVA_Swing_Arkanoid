package com.arkanoid.entities;

import com.arkanoid.Game;
import com.arkanoid.Main;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Racquet {

    private static int Y = 330;
    private static final int WIDTH = 60;
    private static final int HEIGHT = 10;

    Color color = Color.BLACK;
    int x;
    int xa = 0;
    private Game game;

    public Racquet(Game game) {
        this.game = game;
        Y = Main.MAIN_FRAME.getHeight() - 100;
        x = (Main.MAIN_FRAME.getWidth() / 2) - (WIDTH / 2);
    }

    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth() - WIDTH)
            x += xa;
    }

    public void paint(Graphics2D g) {
        g.setColor(color);
        g.fillRect(x, Y, WIDTH, HEIGHT);
    }

    public void keyReleased(KeyEvent e) {
        xa = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            xa = -game.speed;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            xa = game.speed;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, Y, WIDTH, HEIGHT);
    }

    public int getTopY() {
        return Y - HEIGHT;
    }
}
