package com.mygdx.game.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Marcin-PC on 18.08.2017.
 */

public class SoundService {
    private Sound obstacleSound, enemySound;

    private Music music;

    public SoundService() {
        init();
    }

    private void init() {
        obstacleSound = Gdx.audio.newSound(Gdx.files.internal("sounds/obstacle.wav"));
        enemySound = Gdx.audio.newSound(Gdx.files.internal("sounds/enemy1.ogg"));

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
    }

    public void playObstacleSound() {
        obstacleSound.play();
    }

    public void playEnemySound() {
        enemySound.play();
    }

    public void startPlayingMusic(boolean looped) {
        music.setVolume(0.15f);
        music.play();
        music.setLooping(looped);
    }
}
