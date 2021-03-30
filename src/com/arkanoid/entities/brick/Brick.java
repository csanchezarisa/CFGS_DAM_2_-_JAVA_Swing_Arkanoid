package com.arkanoid.entities.brick;

import com.arkanoid.Game;
import com.arkanoid.Main;
import com.arkanoid.assets.abilities.AbilitiesEnum;
import com.arkanoid.config.Configurations;

import java.awt.*;

public abstract class Brick {
    protected int y;
    protected int x;
    protected int resistance;
    protected int width;
    protected int height;
    protected AbilitiesEnum ability;
    protected Color color;
    protected Game game;

    /** Constructor de ladrillo abstrácto */
    public Brick(int y, int x, Game game) {
        this.y = y;
        this.x = x;
        this.game = game;
        width = Main.MAIN_FRAME.getWidth() / Configurations.BRICK_RELATIVE_WIDTH_SIZE;
        height = Main.MAIN_FRAME.getHeight() / Configurations.BRICK_RELATIVE_HEIGHT_SIZE;
    }

    /** Calcula la posición Y del ladrillo.
     * Si no tiene más resistencia, irá cayendo.
     * Si la Y se encuentra fuera del frame principal
     * se devolverá un true
     * @return True si se encuentra fuera del frame
     *  false si se encuentra dentro*/
    public boolean move() {
        boolean outOfBounds = false;

        if (resistance <= 0) {
            y++;

            if (y > Main.MAIN_FRAME.getHeight())
                outOfBounds = true;

        }

        return outOfBounds;
    }

    /** Pinta el ladrillo en la posición en la que se encuentra */
    public void paint(Graphics2D g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    /** Devuelve un boolean si la bola ha
     * colisionado con el propio ladrillo.
     * Si el ladrillo está cayendo devuelve
     * false
     * @return True si la bola colisiona
     * con el ladrillo, false si no lo hace*/
    public boolean collision(Shape ball) {

        boolean collision = false;

        if (resistance > 0) {
            collision = ball.intersects(getBounds());

            if (collision)
                this.touched();
        }


        return collision;
    }

    /** Devuelve un rectangula con la posición y el
     * tamaño del ladrillo */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    /** Cuando hay una colisión con el ladrillo se ejecuta
     * el método para bajarle la resisténcia, cambiarle el tono
     * del color al ladrillo y hacer que caiga */
    public void touched() {
        resistance--;
    }
}
