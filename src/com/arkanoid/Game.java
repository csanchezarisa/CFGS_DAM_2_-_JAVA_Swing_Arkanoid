package com.arkanoid;

import com.arkanoid.entities.Ball;
import com.arkanoid.entities.Racquet;
import com.arkanoid.entities.brick.Brick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel {

    private Ball ball;
    private Racquet racquet;
    private Brick[][] bricks;
    public int speed = 1;

    /** Constructor de la clase Game. Añade un listener para capturar
     * cuando se clica alguna tecla del teclado. Prepara el panel y crea
     * los elementos necesarios para poder jugar */
    public Game() {

        // Se añade el listener al panel para poder capturar el teclado
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                racquet.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                racquet.keyReleased(e);
            }
        });

        ball = new Ball(this);
        racquet = new Racquet(this);
        setFocusable(true);
    }

    /** Realiza los cálculos para ver en qué posiciones
     * hay que pintar los objetos por pantalla */
    public void move() {
        ball.move();
        racquet.move();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        ball.paint(g2d);
        racquet.paint(g2d);
        g2d.setColor(Color.BLACK);
    }

    public Ball getBall() {
        return ball;
    }

    public Racquet getRacquet() {
        return racquet;
    }

    public Brick[][] getBricks() {
        return bricks;
    }
}
