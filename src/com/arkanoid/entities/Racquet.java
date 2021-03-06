package com.arkanoid.entities;

import com.arkanoid.Game;
import com.arkanoid.Main;
import com.arkanoid.assets.abilities.AbilitiesEnum;
import com.arkanoid.assets.sounds.SoundsEnum;
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
        width = game.getMainFrame().getWidth() / Configurations.RACQUET_RELATIVE_WIDTH_SIZE;
        height = game.getMainFrame().getHeight() / Configurations.RACQUET_RELATIVE_HEIGHT_SIZE;
        Y = game.getMainFrame().getHeight() + Configurations.RACQUET_Y_SPAWN_POSITION;
        x = (game.getMainFrame().getWidth() / 2) - (width / 2);
        ability = AbilitiesEnum.NORMAL;
        color = Color.decode(Configurations.RACQUET_BASE_COLOR);
        speed = Configurations.RACQUET_BASE_SPEED;
        timer = new Timer(this);
    }

    public void move() {
        if (x + xa > 0 && (x + xa <= game.getWidth() - width || xa < 0))
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

        Main.playSound(SoundsEnum.POWER_UP);

        this.color = Color.decode(Configurations.RACQUET_SPEED_COLOR);
        speed = speed + Configurations.RACQUET_SPEED_ABILITY;
        int lastWidth = width;
        width = game.getMainFrame().getWidth() / Configurations.RACQUET_RELATIVE_WIDTH_SIZE * 2;
        x += ((lastWidth / 2) - (width / 2));

        // Hay algun cron??metro ya activo?
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
        final int lastWidth = width;
        width = game.getMainFrame().getWidth() / Configurations.RACQUET_RELATIVE_WIDTH_SIZE;
        x += ((lastWidth / 2) - (width / 2));
    }

    public void setColor(String color) {
        this.color = Color.decode(color);
    }
}
