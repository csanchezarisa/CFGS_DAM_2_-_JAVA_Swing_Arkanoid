package com.arkanoid.assets.sounds;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

public class Sounds {

    // Sonidos de Mario Bros - https://themushroomkingdom.net/media/smb/wav

    public static AudioClip BALL;
    public static AudioClip GAME_OVER;
    public static AudioClip BACK;
    public static AudioClip POWER_UP;
    public static AudioClip PAUSE;

    static {
        try {
            BALL = Applet.newAudioClip(new URL("https://themushroomkingdom.net/sounds/wav/smb/smb_bump.wav"));
            GAME_OVER = Applet.newAudioClip(new URL("https://themushroomkingdom.net/sounds/wav/smb/smb_mariodie.wav"));
            BACK = Applet.newAudioClip(new URL("http://www.edu4java.com/sound/back.wav"));
            POWER_UP = Applet.newAudioClip(new URL("https://themushroomkingdom.net/sounds/wav/smb/smb_powerup.wav"));
            PAUSE = Applet.newAudioClip(new URL("https://themushroomkingdom.net/sounds/wav/smb/smb_pause.wav"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
