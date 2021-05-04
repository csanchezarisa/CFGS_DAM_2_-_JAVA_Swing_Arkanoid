package com.arkanoid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class GameTest {

    private Game game;

    @Test
    void testConstructor() throws InterruptedException {
        JFrame frame = new JFrame("Arkanoid test");
        frame.setSize(500, 500);
        game = new Game(frame);
        Assertions.assertNotNull(game);
    }
}
