package com.arkanoid;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Flow;

public class Main {

    public static JFrame MAIN_FRAME;
    public static JLabel SCORE_LABEL;
    public static Game game;

    /** Método principal que inicia el juego */
    public static void main(String[] args) throws InterruptedException {

        // Se crea el frame principal para mostrar el juego
        MAIN_FRAME = new JFrame("Arkanoid");
        MAIN_FRAME.setExtendedState(JFrame.MAXIMIZED_BOTH);
        MAIN_FRAME.setLayout(new BorderLayout());
        MAIN_FRAME.setResizable(false);
        MAIN_FRAME.setVisible(true);
        MAIN_FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Se crea el label con la score
        SCORE_LABEL = new JLabel("Score: 0");
        MAIN_FRAME.add(SCORE_LABEL, "North");
        SCORE_LABEL.setBackground(Color.BLACK);
        SCORE_LABEL.setFont(new Font("Verdana", Font.BOLD, 18));

        // Se inicializa la clase juego
        game = new Game();
        MAIN_FRAME.add(game, "Center");

        // Se crea un listener para escuchar cuando se pulsa cualquier tecla en el frame
        MAIN_FRAME.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                game.getRacquet().keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                game.getRacquet().keyReleased(e);
            }
        });

        // Bucle principal del juego
        while (true) {
            Main.paintScore();
            game.move();
            game.repaint();
            Thread.sleep(5);
        }

    }

    private static void paintScore() {
        SCORE_LABEL.setText("Score: " + game.getScore());
    }

}
