package com.arkanoid.entities.brick;

import com.arkanoid.assets.abilities.AbilitiesEnum;

import java.awt.*;

public class RedBrick extends Brick {

    public RedBrick(int y, int x) {
        super(y, x);
        this.ability = AbilitiesEnum.KILL;
        this.color = Color.RED;
        this.resistance = 2;
    }

}
