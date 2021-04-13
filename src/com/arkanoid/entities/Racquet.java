package com.arkanoid.entities;

import com.arkanoid.Game;
import com.arkanoid.Main;
import com.arkanoid.assets.abilities.AbilitiesEnum;
import com.arkanoid.config.Configurations;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Racquet extends Thread {

    private static int Y = 330;
    private static int width = 60;
    private static int height = 10;

    Color color;
    private AbilitiesEnum ability;
    int x;
    int xa = 0;
    private Game game;
    private int speed;

    private Thread contador;
    private int contadorSecs;

    public Racquet(Game game) {
        this.game = game;
        width = Main.MAIN_FRAME.getWidth() / Configurations.RACQUET_RELATIVE_WIDTH_SIZE;
        height = Main.MAIN_FRAME.getHeight() / Configurations.RACQUET_RELATIVE_HEIGHT_SIZE;
        Y = Main.MAIN_FRAME.getHeight() + Configurations.RACQUET_Y_SPAWN_POSITION;
        x = (Main.MAIN_FRAME.getWidth() / 2) - (width / 2);
        ability = AbilitiesEnum.NORMAL;
        color = Color.decode(Configurations.RACQUET_BASE_COLOR);
        speed = Configurations.RACQUET_BASE_SPEED;
        contadorSecs = Configurations.GAME_BLUE_ABILITY_TIMER;
        contador = (Thread) this;
    }

    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth() - width)
            x += xa;
    }

    public void paint(Graphics2D g) {
        g.setColor(color);
        g.fillRect(x, Y, width, height);
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
        return new Rectangle(x, Y, width, height);
    }

    public int getTopY() {
        return Y - height;
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
        color = Color.decode(Configurations.RACQUET_SPEED_COLOR);
        speed = speed + Configurations.RACQUET_SPEED_ABILITY;
        width = Main.MAIN_FRAME.getWidth() / Configurations.RACQUET_RELATIVE_WIDTH_SIZE * 2;

        // ¿Ya está el contador activo?
        if (contador.isAlive()) {
            contadorSecs = Configurations.GAME_BLUE_ABILITY_TIMER;
        }
        else {
            contadorSecs = Configurations.GAME_BLUE_ABILITY_TIMER;
            contador.start();
        }
    }

    /** Ability normal que deja la pala en estado normal */
    private void normalAbility() {
        color = Color.decode(Configurations.RACQUET_BASE_COLOR);
        speed = Configurations.RACQUET_BASE_SPEED + game.speed;
        width = Main.MAIN_FRAME.getWidth() / Configurations.RACQUET_RELATIVE_WIDTH_SIZE;
    }

    /* Permite dejar el contador en paralelo para la habiliad azúl */
    @Override
    public void run() {
        while (contadorSecs > 0) {
            contadorSecs--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.setAbility(AbilitiesEnum.NORMAL);
    }
}
