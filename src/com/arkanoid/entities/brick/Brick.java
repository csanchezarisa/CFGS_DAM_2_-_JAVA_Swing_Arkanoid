package com.arkanoid.entities.brick;

import com.arkanoid.Main;
import com.arkanoid.assets.abilities.AbilitiesEnum;

import java.awt.*;

public abstract class Brick {
    protected int y;
    protected int x;
    protected int resistance;
    protected int width;
    protected int height;
    protected AbilitiesEnum ability;
    protected Color color;

    public Brick(int y, int x) {
        this.y = y;
        this.x = x;
        width = Main.MAIN_FRAME.getWidth() / 20;
        height = Main.MAIN_FRAME.getHeight() / 30;
    }
}
