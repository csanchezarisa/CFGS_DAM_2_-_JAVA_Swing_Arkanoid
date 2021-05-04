package main.com.arkanoid.entities.brick;

import main.com.arkanoid.Game;
import main.com.arkanoid.assets.abilities.AbilitiesEnum;
import main.com.arkanoid.config.Configurations;

import java.awt.*;

public class GreenBrick extends Brick {

    public GreenBrick(int y, int x, Game game) {
        super(y, x, game);
        this.ability = AbilitiesEnum.NORMAL;
        this.color = Color.decode(Configurations.GREEN_BRICK_BASE);
        this.resistance = 3;
    }

    @Override
    public void touched() {
        super.touched();
        switch (resistance){
            case 2 -> this.color = Color.decode(Configurations.GREEN_BRICK_1HIT);
            case 1 -> this.color = Color.decode(Configurations.GREEN_BRICK_2HIT);
            case 0 -> this.color = Color.decode(Configurations.GREEN_BRICK_3HIT);
            default->this.color = Color.decode(Configurations.GREEN_BRICK_BASE);
        }
    }
}
