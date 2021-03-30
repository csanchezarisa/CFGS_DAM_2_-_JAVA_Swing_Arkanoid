package com.arkanoid.entities.brick;

import com.arkanoid.Game;
import com.arkanoid.assets.abilities.AbilitiesEnum;

import java.awt.*;

public class GreenBrick extends Brick {

    public GreenBrick(int y, int x, Game game) {
        super(y, x, game);
        this.ability = AbilitiesEnum.NORMAL;
        this.color = Color.GREEN;
        this.resistance = 3;
    }

}
