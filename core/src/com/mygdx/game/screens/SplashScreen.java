package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.VampireCat;


public class SplashScreen extends AbstractScreen {

    private Texture splashImg;

    public SplashScreen(final VampireCat game) {
        super(game);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(new GamePlayScreen(game));
            }
        }, 2);
    }


    @Override
    protected void init() {
        splashImg = new Texture("splashscreen.png");
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(splashImg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.end();
    }


}
