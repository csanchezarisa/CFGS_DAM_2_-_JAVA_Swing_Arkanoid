package main.com.arkanoid.util;

import main.com.arkanoid.assets.abilities.AbilitiesEnum;
import main.com.arkanoid.config.Configurations;
import main.com.arkanoid.entities.Racquet;

public class Timer extends Thread {

    public int countSecs;
    private final Racquet racquet;

    public Timer(Racquet racquet) {
        this.countSecs = Configurations.GAME_BLUE_ABILITY_TIMER;
        this.racquet = racquet;
    }

    @Override
    public void run() {
        while (countSecs > 0) {
            countSecs--;

            // Cambia el color de la pala según los segundos que quedan
            switch (countSecs) {
                case 3 -> racquet.setColor(Configurations.RACQUET_SPEED_COLOR_3_S);
                case 1 -> racquet.setColor(Configurations.RACQUET_SPEED_COLOR_1_S);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        racquet.setAbility(AbilitiesEnum.NORMAL);
    }
}
