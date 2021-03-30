package com.arkanoid;

import com.arkanoid.config.Configurations;
import com.arkanoid.entities.Ball;
import com.arkanoid.entities.Racquet;
import com.arkanoid.entities.brick.BlueBrick;
import com.arkanoid.entities.brick.Brick;
import com.arkanoid.entities.brick.GreenBrick;
import com.arkanoid.entities.brick.RedBrick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel {

    private Ball ball;
    private Racquet racquet;
    private Brick[][] bricks;
    public int speed = Configurations.GAME_INITIAL_SCORE;

    /** Constructor de la clase Game. Prepara el panel y crea
     * los elementos necesarios para poder jugar */
    public Game() {
        ball = new Ball(this);
        racquet = new Racquet(this);
        bricks = initializeBricks();
        setBackground(Color.lightGray);
    }

    /** Crea el array con los ladrillos */
    private Brick[][] initializeBricks() {

        // Se calcula el tamaño de cada ladrillo según las configuraciones especificadas
        int brickWidth = Main.MAIN_FRAME.getWidth() / Configurations.BRICK_RELATIVE_WIDTH_SIZE;
        int brickHeight = Main.MAIN_FRAME.getHeight() / Configurations.BRICK_RELATIVE_HEIGHT_SIZE;

        // Se calcula el número de columnas y filas que habrá de ladrillos
        int colNumber = Main.MAIN_FRAME.getWidth() / brickWidth;
        int rowNumber = 2;

        // Se crea el array de ladrillos con las medidas calculadas
        Brick[][] bricks = new Brick[rowNumber][colNumber];

        // Se rellena el array con ladrillos aleatorios
        for (int row = 0; row < bricks.length; row++) {
            for (int col = 0; col < bricks[row].length; col++) {

                // Se busca un número random entre 1 y 3
                int randomColorBrick = (int) (Math.random() * 3 + 1);

                // Se define la variable ladrillo para añadir a la lista
                Brick brick;

                // Según el número aleatorio se crea el ladrillo de un color o de otro
                // 1 -> Azúl
                // 2 -> Rojo
                // 3 -> Verde
                switch (randomColorBrick) {
                    case 1 -> brick = new BlueBrick((row * brickHeight), (col * brickWidth), this);
                    case 2 -> brick = new RedBrick((row * brickHeight), (col * brickWidth), this);
                    case 3 -> brick = new GreenBrick((row * brickHeight), (col * brickWidth), this);
                    default -> throw new IllegalStateException("Unexpected value: " + randomColorBrick);
                }

                // Se assigna el ladrillo en la posición de la lista
                bricks[row][col] = brick;
            }
        }

        return bricks;
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(
                this,
                "Your score is " + getScore(),
                "Game Over",
                JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
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

    public int getScore() {
        return speed;
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
