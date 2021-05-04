package com.arkanoid.entities.brick;

import com.arkanoid.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class BrickTest {

    private BlueBrick blueBrick;
    private RedBrick redBrick;
    private GreenBrick greenBrick;

    @Test
    void testBlueBrickConstructor() {
        JFrame frame = new JFrame("Arkanoid test");
        frame.setSize(500, 500);
        Game game = new Game(frame);
        blueBrick = new BlueBrick(0, 0, game);
        Assertions.assertNotNull(blueBrick);
    }

    @Test
    void testRedBrickConstructor() {
        JFrame frame = new JFrame("Arkanoid test");
        frame.setSize(500, 500);
        Game game = new Game(frame);
        redBrick = new RedBrick(0, 0, game);
        Assertions.assertNotNull(redBrick);
    }

    @Test
    void testGreenBrickConstructor() {
        JFrame frame = new JFrame("Arkanoid test");
        frame.setSize(500, 500);
        Game game = new Game(frame);
        greenBrick = new GreenBrick(0, 0, game);
        Assertions.assertNotNull(greenBrick);
    }
}
