package com.arkanoid.entities;

import com.arkanoid.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class BallTest {

    private Ball ball;

    @Test
    void testConstructor() {
        JFrame frame = new JFrame("Arkanoid test");
        frame.setSize(500, 500);
        Game game = new Game(frame);
        ball = new Ball(game);
        Assertions.assertNotNull(ball);
    }

    @Test
    void testLeftTouch() {
        JFrame frame = new JFrame("Arkanoid test");
        frame.setSize(500, 500);
        Game game = new Game(frame);
        ball = new Ball(game);
        ball.x = -10;
        ball.move();
        Assertions.assertTrue(ball.xa > 0);
    }

    @Test
    void testRightTouch() {
        JFrame frame = new JFrame("Arkanoid test");
        frame.setSize(500, 500);
        Game game = new Game(frame);
        ball = new Ball(game);
        ball.x = frame.getWidth() + 1;
        ball.move();
        Assertions.assertTrue(ball.xa < 0);
    }

    @Test
    void testTopTouch() {
        JFrame frame = new JFrame("Arkanoid test");
        frame.setSize(500, 500);
        Game game = new Game(frame);
        ball = new Ball(game);
        ball.y = 0;
        ball.move();
        Assertions.assertTrue(ball.ya > 0);
    }

    @Test
    void testBottomTouch() {
        JFrame frame = new JFrame("Arkanoid test");
        frame.setSize(500, 500);
        Game game = new Game(frame);
        game.setSize(500, 500);
        ball = new Ball(game);
        ball.y = frame.getHeight() + 1;
        int lives = game.getLives();
        ball.move();
        Assertions.assertFalse(game.getLives() == lives);
    }
}
