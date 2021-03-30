package com.arkanoid.entities.brick;

import com.arkanoid.Game;
import com.arkanoid.assets.abilities.AbilitiesEnum;

import java.awt.*;

public class BlueBrick extends Brick {

    public BlueBrick(int y, int x, Game game) {
        super(y, x, game);
        this.ability = AbilitiesEnum.SPEED;
        this.color = Color.BLUE;
        this.resistance = 1;
    }

}
