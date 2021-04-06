package com.arkanoid;

import com.arkanoid.assets.gamestate.GameStateEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

    public static JFrame MAIN_FRAME;
    public static JLabel SCORE_LABEL;
    public static Game game;
    public static GameStateEnum gameState = GameStateEnum.START;
    public static boolean exitGame = false;

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
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> game.getRacquet().keyPressed(e);
                    case KeyEvent.VK_SPACE -> keySpacePressed();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> game.getRacquet().keyPressed(e);
                    case KeyEvent.VK_SPACE -> keySpacePressed();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> game.getRacquet().keyPressed(e);
                }
            }
        });

        // Bucle principal del juego
        while (!exitGame) {
            switch (gameState) {
                case START -> gameStart();
                case RUNNING -> gameRunning();
            }
        }

    }

    private static void gameStart() {
        Main.paintScore();
        game.repaint();
        int result = JOptionPane.showOptionDialog(
                MAIN_FRAME,
                "Welcome to ARKANOID, do you want to play?",
                "ARKANOID",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                null
        );

        switch (result) {
            case JOptionPane.YES_OPTION -> gameState = GameStateEnum.RUNNING;
            case JOptionPane.NO_OPTION -> gameState = GameStateEnum.FINAL;
        }
    }

    private static void gameRunning() throws InterruptedException {
        Main.paintScore();
        game.move();
        game.repaint();
        Thread.sleep(5);
    }

    private static void keySpacePressed() {
        switch (gameState) {
            case START -> gameState = GameStateEnum.RUNNING;
        }
    }

    private static void paintScore() {
        SCORE_LABEL.setText("Score: " + game.getScore());
    }

}
