package com.arkanoid;

import com.arkanoid.assets.gamestate.GameStateEnum;
import com.arkanoid.config.Configurations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

    public static JFrame MAIN_FRAME;
    public static JLabel SCORE_LABEL;
    public static Game game;

    public static int gameSleep = 5;

    /** Estado en el que se encuentra el juego actualmente */
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
        SCORE_LABEL.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));

        // Se inicializa la clase juego
        game = new Game();
        MAIN_FRAME.add(game, "Center");

        // Se crea un listener para escuchar cuando se pulsa cualquier tecla en el frame
        MAIN_FRAME.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    keyEscapePressed();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> game.getRacquet().keyPressed(e);
                    case KeyEvent.VK_ESCAPE -> keyEscapePressed();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> game.getRacquet().keyReleased(e);
                }
            }
        });

        // Bucle principal del juego
        while (!exitGame) {

            // Según el estado en el que está el juego hace una cosa u otra
            switch (gameState) {
                case START -> gameStart();
                case RUNNING -> gameRunning();
                case PAUSED -> gamePaused();
                case FINAL_WIN -> gameFinal(true);
                case FINAL_LOSE -> gameFinal(false);
                case BALL_RESET -> gameBallOut();
            }

            Thread.sleep(gameSleep);
        }
        System.exit(0);
    }

    /** Cuando el juego inicia muestra un Alert con la opción
     * de empezar la partida o de salir
     * Según qué opción se selecciona pasa el estado a RUNNING o
     * cierra la aplicación*/
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
            case JOptionPane.NO_OPTION -> exitGame = true;
        }
    }

    /** Estado principa del juego. Mientras el juego está en estado RUNNING
     * se calculan las nuevas posiciones de los elementos y se pintan
     * por pantalla */
    private static void gameRunning() {
        Main.paintScore();
        game.move();
        game.repaint();
    }

    /** Cuando el juego se encuentra en estado pausado
     * se muestra un alert con la opción de seguir jugando o salir
     * del juego*/
    private static void gamePaused() {
        Main.paintScore();
        game.repaint();
        int result = JOptionPane.showOptionDialog(
                MAIN_FRAME,
                Configurations.GAME_MESSAGE_PAUSED + game.getScore(),
                "PAUSED",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                null
        );

        switch (result) {
            case JOptionPane.YES_OPTION -> gameState = GameStateEnum.RUNNING;
            case JOptionPane.NO_OPTION -> exitGame = true;
        }
    }

    /** Cuando la bola ha tocado el borde se muestra
     * un mensaje para dar tiempo al usuario para prepararse */
    private static void gameBallOut() {
        Main.paintScore();
        game.repaint();
        JOptionPane.showMessageDialog(MAIN_FRAME, Configurations.GAME_MESSAGE_BALL_OUT);
        gameState = GameStateEnum.RUNNING;
    }

    /** Finaliza el juego, muestra un mensaje distinto según si se ha ganado
     * o se ha perdido.
     * Junto con la opción para reiniciar el juego o finalizar la aplicación */
    private static void gameFinal(boolean victory) throws InterruptedException {
        String message;

        if (victory)
            message = Configurations.GAME_MESSAGE_WIN;
        else
            message = Configurations.GAME_MESSAGE_LOSE;

        Main.paintScore();
        game.repaint();

        int result = JOptionPane.showOptionDialog(
                MAIN_FRAME,
                message + Configurations.GAME_MESSAGE_FINAL_BASE + game.getScore(),
                "FINAL",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                null
        );

        switch (result) {
            case JOptionPane.YES_OPTION -> restartGame();
            case JOptionPane.NO_OPTION -> exitGame = true;
        }
    }

    /** Permite reiniciar el juego, creando todos
     * los elementos de nuevo */
    private static void restartGame() throws InterruptedException {
        gameState = GameStateEnum.START;
        Main.main(null);
    }

    /** Cuando la bola ha tocado el borde inferior
     * se cambia el estado del juego según la lógica */
    public static void ballOut(int lives) {
        if (lives > 0)
            gameState = GameStateEnum.BALL_RESET;
        else
            gameState = GameStateEnum.FINAL_LOSE;
    }

    /** Si el juego está en estado RUNNING lo pasa a pausado */
    private static void keyEscapePressed() {
        if (gameState == GameStateEnum.RUNNING) {
            gameState = GameStateEnum.PAUSED;
        }
    }

    private static void paintScore() {
        SCORE_LABEL.setText("Score: " + game.getScore() + "        Lives: " + "♥ ".repeat(game.getLives()));
    }

}
