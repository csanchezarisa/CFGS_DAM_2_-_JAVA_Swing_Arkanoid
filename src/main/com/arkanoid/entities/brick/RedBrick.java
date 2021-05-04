package main.com.arkanoid.entities.brick;

import main.com.arkanoid.Game;
import main.com.arkanoid.assets.abilities.AbilitiesEnum;
import main.com.arkanoid.config.Configurations;

import java.awt.*;

public class RedBrick extends Brick {

    public RedBrick(int y, int x, Game game) {
        super(y, x, game);
        this.ability = AbilitiesEnum.KILL;
        this.color = Color.decode(Configurations.RED_BRICK_BASE);
        this.resistance = 2;
    }

    @Override
    public void touched() {
        super.touched();
        switch (resistance){

            case 1 -> this.color = Color.decode(Configurations.RED_BRICK_1HIT);
            case 0 -> this.color = Color.decode(Configurations.RED_BRICK_2HIT);
            default -> this.color = Color.decode(Configurations.RED_BRICK_BASE);
        }
    }
}
