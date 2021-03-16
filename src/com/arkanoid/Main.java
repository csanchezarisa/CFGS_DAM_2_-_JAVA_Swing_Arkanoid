package com.arkanoid;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Main {

    public static JFrame MAIN_FRAME;
    public static JLabel SCORE_LABEL;
    public static Game game;

    /** Método principal que inicia el juego */
    public static void main(String[] args) throws InterruptedException {

        // Se crea el frame principal para mostrar el juego
        MAIN_FRAME = new JFrame("Arkanoid");
        MAIN_FRAME.setSize(300, 400);
        MAIN_FRAME.setLayout(new BorderLayout());
        SCORE_LABEL = new JLabel("Score: 0");
        SCORE_LABEL.setFont(new Font("Verdana", Font.BOLD, 18));

        // Se inicializa la clase juego
        game = new Game();
        MAIN_FRAME.add(game, "Center");
        MAIN_FRAME.add(SCORE_LABEL, "North");
        MAIN_FRAME.setResizable(false);
        MAIN_FRAME.setVisible(true);
        MAIN_FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Bucle principal del juego
        while (true) {
            Main.paintScore();
            game.move();
            game.repaint();
            Thread.sleep(10);
        }

    }

    private static void paintScore() {
        SCORE_LABEL.setText("Score: " + game.getScore());
    }

}
