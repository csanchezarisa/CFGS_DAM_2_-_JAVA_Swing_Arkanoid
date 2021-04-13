package com.arkanoid.util;

import com.arkanoid.assets.abilities.AbilitiesEnum;
import com.arkanoid.config.Configurations;
import com.arkanoid.entities.Racquet;

public class Timer extends Thread {

    public int countSecs;
    private Racquet racquet;

    public Timer(Racquet racquet) {
        this.countSecs = Configurations.GAME_BLUE_ABILITY_TIMER;
        this.racquet = racquet;
    }

    @Override
    public void run() {
        while (countSecs > 0) {
            countSecs--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        racquet.setAbility(AbilitiesEnum.NORMAL);
    }
}
