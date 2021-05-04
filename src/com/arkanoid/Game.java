package com.arkanoid;

import com.arkanoid.assets.gamestate.GameStateEnum;
import com.arkanoid.assets.sounds.SoundsEnum;
import com.arkanoid.config.Configurations;
import com.arkanoid.entities.Ball;
import com.arkanoid.entities.Racquet;
import com.arkanoid.entities.brick.BlueBrick;
import com.arkanoid.entities.brick.Brick;
import com.arkanoid.entities.brick.GreenBrick;
import com.arkanoid.entities.brick.RedBrick;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {

    private Ball ball;
    private Racquet racquet;
    private Brick[][] bricks;
    private int lives;
    private int score = Configurations.GAME_INITIAL_SCORE;
    public double speed;
    private JFrame mainFrame;

    /** Constructor de la clase Game. Prepara el panel y crea
     * los elementos necesarios para poder jugar */
    public Game(JFrame frame) {
        speed = 1 + (Configurations.GAME_SPEED_INCREMENT * score);
        setBackground(Color.lightGray);
        lives = Configurations.GAME_INITIAL_LIVES;
        mainFrame = frame;

        // De manera paralella se crean los ladrillos, la bola y la raqueta
        Runnable initializeBricks = () -> bricks = initializeBricks();
        initializeBricks.run();
        Runnable initializeBall = () -> ball = new Ball(this);
        initializeBall.run();
        Runnable initializeRacquet = () -> racquet = new Racquet(this);
        initializeRacquet.run();

        // Se reproduce el sonido principal del juego
        Main.stopSounds();
        Main.playSound(SoundsEnum.BACKGROUND);
    }

    /** Crea el array con los ladrillos */
    private Brick[][] initializeBricks() {

        // Se calcula el tamaño de cada ladrillo según las configuraciones especificadas
        int brickWidth = mainFrame.getWidth() / Configurations.BRICK_RELATIVE_WIDTH_SIZE;
        int brickHeight = mainFrame.getHeight() / Configurations.BRICK_RELATIVE_HEIGHT_SIZE;

        // Se calcula el número de columnas y filas que habrá de ladrillos
        int colNumber = mainFrame.getWidth() / brickWidth;
        int rowNumber = Configurations.BRICKS_ROWS;

        // Se crea el array de ladrillos con las medidas calculadas
        Brick[][] bricks = new Brick[rowNumber][colNumber];

        // Se rellena el array con ladrillos aleatorios
        for (int row = bricks.length - 1; row >= 0; row--) {
            for (int col = bricks[row].length - 1; col >= 0; col--) {

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

    /** Método que se ejecuta cuando la bola toca el borde
     * inferior.
     * Resta una vida y reseta las posiciones de los elementos*/
    public void onBallOut() {
        lives--;

        if (lives > 0) {
            resetPositions();
        }

        Main.ballOut(lives);
    }

    /** Reseta las posiciones de la bola y la pala */
    public void resetPositions() {
        ball = new Ball(this);
        racquet = new Racquet(this);
    }

    /** Realiza los cálculos para ver en qué posiciones
     * hay que pintar los objetos por pantalla */
    public void move() {
        // Se crean tres hilos para hacer los cálculos de las posiciones de los objetos
        Runnable moveBricks = this::moveBricks;
        moveBricks.run();
        Runnable moveBall = this::moveBall;
        moveBall.run();
        Runnable moveRacquet = this::moveRacquet;
        moveRacquet.run();
    }

    /** Ejecuta el método move de todos y cada uno de los
     * ladrillos que hay en la lista del juego.
     * Si el ladrillo ha caído y ha salido de la pantalla,
     * se eliminará */
    private void moveBricks() {

        // Se recorren todas las posiciones de la lista
        for (int row = bricks.length - 1; row >= 0; row--) {
            for (int col = bricks[row].length - 1; col >= 0; col--) {

                // ¿Hay un ladrillo en esta posición?
                if (bricks[row][col] != null) {

                    // Se mueve el ladrillo. ¿Está fuera del mapa?
                    if (bricks[row][col].move()) {
                        bricks[row][col] = null;
                    }
                }
            }
        }
    }

    /** Ejecuta el método move de la pelota para calcular
     * su próxima posición */
    private void moveBall() {
        this.ball.move();
    }

    /** Ejecuta el método move de la pala para calcular
     * su proxima posición */
    private void moveRacquet() {
        this.racquet.move();
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
        paintBricks(g2d);
        g2d.setColor(Color.BLACK);
    }

    /** Pinta los ladrillos que hay en el listado del juego */
    private void paintBricks(Graphics2D g2d) {
        for (Brick[] brickRow : bricks) {
            for (Brick brick : brickRow) {
                if (brick != null)
                    brick.paint(g2d);
            }
        }
    }

    /** Aumenta en 1 la score. Si la score es divisible entre 5
     * desminuirà en 1 milisegundo el tiempo del sleep en el render.
     * Si el tiempo de sleep es 5, subirá en 1 la velocidad, hasta que
     * la score llegue al máximo definido en el Configuration.
     * Una vez ha llegado al máximo, cambia el estado del juego en el
     * Main al estasdo FINAL_WIN */
    public void updateScore() {
        score++;
        if (score == Configurations.GAME_MAX_PUNTUATION)
            Main.gameState = GameStateEnum.FINAL_WIN;
        else
            speed = 1 + (Configurations.GAME_SPEED_INCREMENT * score);
    }

    public int getScore() {
        return score;
    }

    public Racquet getRacquet() {
        return racquet;
    }

    public Brick[][] getBricks() {
        return bricks;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
}
