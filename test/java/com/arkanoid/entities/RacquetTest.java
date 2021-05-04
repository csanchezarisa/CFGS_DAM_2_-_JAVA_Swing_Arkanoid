package com.arkanoid.entities;

import com.arkanoid.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class RacquetTest {

    private Racquet racquet;

    @Test
    void testConstructor() {
        JFrame frame = new JFrame("Arkanoid test");
        frame.setSize(500, 500);
        Game game = new Game(frame);
        racquet = new Racquet(game);
        Assertions.assertNotNull(racquet);
    }
}
