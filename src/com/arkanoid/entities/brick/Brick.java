package com.arkanoid.entities.brick;

import com.arkanoid.Game;
import com.arkanoid.Main;
import com.arkanoid.assets.abilities.AbilitiesEnum;
import com.arkanoid.config.Configurations;

import java.awt.*;

public abstract class Brick {
    protected int y;
    protected int x;
    protected int resistance;
    protected int width;
    protected int height;
    protected AbilitiesEnum ability;
    protected Color color;
    protected Game game;

    public Brick(int y, int x, Game game) {
        this.y = y;
        this.x = x;
        this.game = game;
        width = Main.MAIN_FRAME.getWidth() / Configurations.BRICK_RELATIVE_WIDTH_SIZE;
        height = Main.MAIN_FRAME.getHeight() / Configurations.BRICK_RELATIVE_HEIGHT_SIZE;
    }
}
