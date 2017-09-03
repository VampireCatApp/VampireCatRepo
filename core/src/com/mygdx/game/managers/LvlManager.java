package com.mygdx.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.screens.GamePlayScreen;

/**
 * Created by Marcin-PC on 03.09.2017.
 */

public class LvlManager {
    private int level;
    private float exp;
    private final static float[] nextLvl= {100,150,200,250,300,350,400,450,500,550,600,650,700,750,800,850,900,950,1000};
    private static final float SCALE=2/3f;

    private Image lvlBar;
    private Image expValue;


    public LvlManager(GamePlayScreen game) {
        this.level=1;
        initBar(game);
    }

    private void initBar(GamePlayScreen game) {
        lvlBar = new Image(new Texture(Gdx.files.internal("lvlbar.png")));
        expValue = new Image(new Texture(Gdx.files.internal("lvlvalue.png")));

        lvlBar.setPosition(0, game.getCamera().position.y + GamePlayScreen.HEIGHT*(23/48f));
        lvlBar.setSize(GamePlayScreen.WIDTH*SCALE,GamePlayScreen.HEIGHT*(1/48f));
        game.getStage().addActor(lvlBar);

        expValue.setPosition(game.WIDTH*(88/300f)*SCALE, game.getCamera().position.y + GamePlayScreen.HEIGHT*(23/48f) + GamePlayScreen.HEIGHT*(1/48f)*(3/16f));
        expValue.setSize(scaleExpValue(),GamePlayScreen.HEIGHT*(1/48f)*(10/16f));
        game.getStage().addActor(expValue);

    }

    public void expIncrease(float velocity){
        this.exp+= velocity*(-1/12000f);
        if(exp>nextLvl[level]){
            //TODO surplas add to next lvl progress
            exp=0;
            level++;
        }
    }

    public void updateLvlBarPosition(GamePlayScreen game){

        lvlBar.setPosition(0, game.getCamera().position.y + GamePlayScreen.HEIGHT*(23/48f));
    }

    public void updateExpValue(GamePlayScreen game) {
        expValue.setPosition(game.WIDTH*(88/300f)*SCALE, game.getCamera().position.y + GamePlayScreen.HEIGHT*(23/48f) + GamePlayScreen.HEIGHT*(1/48f)*(3/16f));
        expValue.setSize(scaleExpValue(),GamePlayScreen.HEIGHT*(1/48f)*(10/16f));
    }

    private float scaleExpValue(){
        return exp/nextLvl[level] * 203/300f * GamePlayScreen.WIDTH*SCALE;

    }

    public void setExp(float exp) {
        //TODO surplas add to next lvl progress
        if(exp>nextLvl[level]){
            //TODO surplas add to next lvl progress
            exp=0;
            level++;
        }
        this.exp = exp;
    }

    public float getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }
}

