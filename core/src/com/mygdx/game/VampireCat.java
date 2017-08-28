package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.mygdx.game.screens.SplashScreen;
import com.mygdx.game.service.SoundService;

public class VampireCat extends Game {
    public static final String GAME_NAME = "VampireCat";

    private boolean paused;
    private SoundService soundService;

    @Override
    public void create() {
        init();
        this.setScreen(new SplashScreen(this));
    }

    private void init() {
        initSoundsService();

    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    private void initSoundsService() {
        soundService = new SoundService();
    }

    public SoundService getSoundService() {
        return soundService;
    }
}
