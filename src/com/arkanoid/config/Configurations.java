package com.arkanoid.config;

/**
 * Todas las configuraciones y definiciones
 * de tamaños están especificadas en esta clase */
public class Configurations {

    // Messeges
    public static final String GAME_MESSAGE_FINAL_BASE = "\nDo yoy want to retry?\nYour score is ";
    public static final String GAME_MESSAGE_WIN = "YOU WIN!";
    public static final String GAME_MESSAGE_LOSE = "YOU LOSE!";
    public static final String GAME_MESSAGE_PAUSED = "Game paused, do you want to continue?\nYour score is ";
    public static final String GAME_MESSAGE_BALL_OUT = "The ball has touched the bottom border.";

    // Racquet
    public static final int RACQUET_RELATIVE_WIDTH_SIZE = 15;
    public static final int RACQUET_RELATIVE_HEIGHT_SIZE = 50;
    public static final int RACQUET_Y_SPAWN_POSITION = -100;
    public static final int RACQUET_BASE_SPEED = 2;
    public static final int RACQUET_SPEED_ABILITY = 2;

    // Ball sizes
    public static final int BALL_RELATIVE_SIZE = 50;

    // Brick sizes
    public static final int BRICK_RELATIVE_WIDTH_SIZE = 20;
    public static final int BRICK_RELATIVE_HEIGHT_SIZE = 30;
    public static final int BRICKS_ROWS = 4;

    // Game
    public static final int GAME_INITIAL_SCORE = 0;
    public static final int GAME_INITIAL_LIVES = 3;
    public static final int GAME_BLUE_ABILITY_TIMER = 10;
    public static final int GAME_MAX_PUNTUATION = 30;

     
    // Brick colors / Green 3 / Red 2 / Blue 1
    public static final String RED_BRICK_BASE = "#922B21";
    public static final String BLUE_BRICK_BASE = "#081ABC";
    public static final String GREEN_BRICK_BASE = "#006921";
    
    //Bricks hit
    public static final String RED_BRICK_1HIT = "#CF2817";
    public static final String RED_BRICK_2HIT = "#FF6656";

    public static final String GREEN_BRICK_1HIT = "#039631";
    public static final String GREEN_BRICK_2HIT = "#00E013";
    public static final String GREEN_BRICK_3HIT = "#8AFF94";

    public static final String BLUE_BRICK_1HIT = "#3146FF";

    // Racquet colors
    public static final String RACQUET_BASE_COLOR = "#000000";
    public static final String RACQUET_KILL_COLOR = "#922B21";
    public static final String RACQUET_SPEED_COLOR = "#3146FF";
}
