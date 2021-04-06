package com.arkanoid.entities;

import com.arkanoid.Game;
import com.arkanoid.Main;
import com.arkanoid.config.Configurations;
import com.arkanoid.entities.brick.Brick;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;

public class Ball {

    private static int diameter;

    int x;
    int y;
    int xa = 1;
    int ya = 1;
    private Game game;

    public Ball(Game game) {
        this.game = game;
        x = (int) (Math.random() * (Main.MAIN_FRAME.getWidth()));
        diameter = Main.MAIN_FRAME.getWidth() / Configurations.BALL_RELATIVE_SIZE;
        y = diameter;
    }
    

    public void move() {
        boolean changeDirection = true;

        if (x + xa < 0)
            xa = game.speed;
        else if (x + xa > game.getWidth() - diameter)
            xa = -game.speed;
        else if (y + ya < 0)
            ya = game.speed;
        else if (y + ya > game.getHeight() - diameter) {
            ya = -game.speed;
            //game.gameOver();
        }
        else if (racquetCollision()) {
            ya = -game.speed;
            y = game.getRacquet().getTopY() - diameter;
            game.speed++;
        }
        else if (brickCollision()) {
            ya = -ya;
        }
        else
            changeDirection = false;

        x = x + xa;
        y = y + ya;
    }

    /** Revisa si hay colisión con la raqueta
     * @return true si hay colisión, false si no la hay */
    private boolean racquetCollision() {
        return getBounds().intersects(game.getRacquet().getBounds());
    }

    /** Revisa si hay colisión con alguno de los ladrillos
     * @return true si hay alguna colisión con algún ladrillo,
     * false si no hay ninguna colisión*/
    private boolean brickCollision() {
        Shape ballBounds = getBounds();
        Brick[][] bricks = game.getBricks();

        int touchedBricks = 0;

        for (Brick[] brickRow : bricks) {
            touchedBricks += Arrays.stream(brickRow).parallel().filter(brick -> brick != null && brick.collision(ballBounds)).count();
        }

        return touchedBricks > 0 ? true : false;
    }

    public void paint(Graphics2D g) {
        g.fillOval(x, y, diameter, diameter);
    }

    public Shape getBounds() {
        return new Ellipse2D.Double(x, y, diameter, diameter);
    }
}
