package com.arkanoid.entities;

import com.arkanoid.Game;
import com.arkanoid.Main;
import com.arkanoid.assets.abilities.AbilitiesEnum;
import com.arkanoid.config.Configurations;
import com.arkanoid.util.Timer;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Racquet {

    static int Y = 330;
    public static int width = 60;
    static int height = 10;

    Color color;
    private AbilitiesEnum ability;
    double x;
    double xa = 0;
    private final Game game;
    private double speed;
    private Timer timer;

    public Racquet(Game game) {
        this.game = game;
        width = Main.MAIN_FRAME.getWidth() / Configurations.RACQUET_RELATIVE_WIDTH_SIZE;
        height = Main.MAIN_FRAME.getHeight() / Configurations.RACQUET_RELATIVE_HEIGHT_SIZE;
        Y = Main.MAIN_FRAME.getHeight() + Configurations.RACQUET_Y_SPAWN_POSITION;
        x = (Main.MAIN_FRAME.getWidth() / 2) - (width / 2);
        ability = AbilitiesEnum.NORMAL;
        color = Color.decode(Configurations.RACQUET_BASE_COLOR);
        speed = Configurations.RACQUET_BASE_SPEED;
        timer = new Timer(this);
    }

    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth() - width)
            x += xa;
    }

    public void paint(Graphics2D g) {
        g.setColor(color);
        g.fillRect((int) Math.round(x), Y, width, height);
    }

    public void keyReleased(KeyEvent e) {
        xa = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            xa = -game.speed - speed;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            xa = game.speed + speed;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) Math.round(x), Y, width, height);
    }

    /** Permite setear una ability para la pala
     * @param ability ability que hay que aplicar
     * a la pala */
    public void setAbility(AbilitiesEnum ability) {
        this.ability = ability;

        switch (this.ability) {
            case SPEED -> speedAbility();
            case KILL -> killAbility();
            default -> normalAbility();
        }
    }

    /** Ability kill que finaliza el juego */
    private void killAbility() {
        color = Color.decode(Configurations.RACQUET_KILL_COLOR);
        game.setLives(0);
        Main.ballOut(game.getLives());
    }

    /** Ability speed que aumenta la velocidad de la pala */
    private void speedAbility() {
        speed = speed + Configurations.RACQUET_SPEED_ABILITY;
        width = Main.MAIN_FRAME.getWidth() / Configurations.RACQUET_RELATIVE_WIDTH_SIZE * 2;

        // La pala al crecer aparece fuera del mapa?
        // Se coloca bien
        if (x + width > Main.MAIN_FRAME.getWidth())
            x = Main.MAIN_FRAME.getWidth() - width - 10;

        // Hay algun cronómetro ya activo?
        if (timer.isAlive()) {
            timer.countSecs = Configurations.GAME_BLUE_ABILITY_TIMER;
        }
        else {
            timer = new Timer(this);
            timer.start();
        }
    }

    /** Ability normal que deja la pala en estado normal */
    private void normalAbility() {
        color = Color.decode(Configurations.RACQUET_BASE_COLOR);
        speed = Configurations.RACQUET_BASE_SPEED + game.speed;
        width = Main.MAIN_FRAME.getWidth() / Configurations.RACQUET_RELATIVE_WIDTH_SIZE;
    }

    public void setColor(String color) {
        this.color = Color.decode(color);
    }
}
