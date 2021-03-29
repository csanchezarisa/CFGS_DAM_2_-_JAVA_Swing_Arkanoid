package com.arkanoid.entities;

import com.arkanoid.Game;
import com.arkanoid.Main;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball {

    private static final int DIAMETER = 30;

    int x;
    int y = DIAMETER;
    int xa = 1;
    int ya = 1;
    private Game game;

    public Ball(Game game) {
        this.game = game;
        x = (int) (Math.random() * (Main.MAIN_FRAME.getWidth()));
    }

    public void move() {
        boolean changeDirection = true;

        if (x + xa < 0)
            xa = game.speed;
        else if (x + xa > game.getWidth() - DIAMETER)
            xa = -game.speed;
        else if (y + ya < 0)
            ya = game.speed;
        else if (y + ya > game.getHeight() - DIAMETER) {
            ya = -game.speed;
            //game.gameOver();
        }
        else if (collision()) {
            ya = -game.speed;
            y = game.getRacquet().getTopY() - DIAMETER;
            game.speed++;
        }
        else
            changeDirection = false;

        x = x + xa;
        y = y + ya;
    }

    private boolean collision() {
        return getBounds().intersects(game.getRacquet().getBounds());
    }

    public void paint(Graphics2D g) {
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    public Shape getBounds() {
        return new Ellipse2D.Double(x, y, DIAMETER, DIAMETER);
    }
}
