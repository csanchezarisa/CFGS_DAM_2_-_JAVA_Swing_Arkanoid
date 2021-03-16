package com.arkanoid;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Main {

    public static JFrame MAIN_FRAME;

    /** Método principal que inicia el juego */
    public static void main(String[] args) throws InterruptedException {

        // Se crea el frame principal para mostrar el juego
        MAIN_FRAME = new JFrame("Arkanoid");
        MAIN_FRAME.setSize(300, 400);
        MAIN_FRAME.setLayout(new BorderLayout());

        JLabel lable = new JLabel("Hola");

        // Se inicializa la clase juego
        Game game = new Game();
        MAIN_FRAME.add(game, "Center");
        MAIN_FRAME.add(lable, "North");
        MAIN_FRAME.setResizable(false);
        MAIN_FRAME.setVisible(true);
        MAIN_FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Bucle principal del juego
        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(10);
        }

    }

}
