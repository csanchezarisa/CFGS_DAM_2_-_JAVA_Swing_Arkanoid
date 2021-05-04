package main.com.arkanoid.entities;

import main.com.arkanoid.Game;
import main.com.arkanoid.Main;
import main.com.arkanoid.assets.sounds.SoundsEnum;
import main.com.arkanoid.config.Configurations;
import main.com.arkanoid.entities.brick.Brick;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;

public class Ball {

    private static int diameter;

    double x;
    double y;
    double xa;
    double ya;
    private final Game game;

    public Ball(Game game) {
        this.game = game;
        x = (int) (Math.random() * (Main.MAIN_FRAME.getWidth()));
        diameter = Main.MAIN_FRAME.getWidth() / Configurations.BALL_RELATIVE_SIZE;
        y = Main.MAIN_FRAME.getHeight() / Configurations.BRICK_RELATIVE_HEIGHT_SIZE * (Configurations.BRICKS_ROWS + 1);
        xa = game.speed;
        ya = game.speed;
    }
    
    /** Hace los cálculos y setea las nuevas posiciones */
    public void move() {
        boolean changeDirection = false;

        if (x + xa < 0) {
            xa = game.speed;
            changeDirection = true;
        }
        else if (x + xa > game.getWidth() - diameter) {
            xa = -game.speed;
            changeDirection = true;
        }
        else if (y + ya < 0) {
            ya = game.speed;
            changeDirection = true;
        }
        else if (y + ya > game.getHeight() - diameter) {
            ya = -game.speed;
            game.onBallOut();
            changeDirection = true;
        }
        else if (racquetCollision()) {
            xa = ((x - game.getRacquet().x - (Racquet.width / 2)) / (Racquet.width / 2)) * game.speed;
            ya = -game.speed;
            game.updateScore();
            changeDirection = true;
        }
        else if (brickCollision()) {
            ya = -ya;
            changeDirection = true;
        }

        if (changeDirection)
            Main.playSound(SoundsEnum.BALL_TOUCH);

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

        return touchedBricks > 0;
    }

    /** Pinta la bola en las posiciones caluladas */
    public void paint(Graphics2D g) {
        g.fillOval((int) Math.round(x), (int) Math.round(y), diameter, diameter);
    }

    /** Devuelve el shape con la posición y la medida de la bola
     * para ejecutar los collision
     * @return  Shape con la posicion  y tamaño de la bola*/
    public Shape getBounds() {
        return new Ellipse2D.Double(x, y, diameter, diameter);
    }
}
