package main.com.arkanoid.entities.brick;

import main.com.arkanoid.Game;
import main.com.arkanoid.assets.abilities.AbilitiesEnum;
import main.com.arkanoid.config.Configurations;

import java.awt.*;

public class BlueBrick extends Brick {

    public BlueBrick(int y, int x, Game game) {
        super(y, x, game);
        this.ability = AbilitiesEnum.SPEED;
        this.color = Color.decode(Configurations.BLUE_BRICK_BASE);
        this.resistance = 1;
    }

    @Override
    public void touched() {
        super.touched();
        switch (resistance){
            case  0 ->   this.color = Color.decode(Configurations.BLUE_BRICK_1HIT);
            default->  this.color = Color.decode(Configurations.BLUE_BRICK_BASE);
        }
    }
}
