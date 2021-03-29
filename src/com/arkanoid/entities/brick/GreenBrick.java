package com.arkanoid.entities.brick;

import com.arkanoid.assets.abilities.AbilitiesEnum;

import java.awt.*;

public class GreenBrick extends Brick {

    public GreenBrick(int y, int x) {
        super(y, x);
        this.ability = AbilitiesEnum.NORMAL;
        this.color = Color.GREEN;
        this.resistance = 3;
    }

}
