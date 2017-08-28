package com.mygdx.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.VampireCat;
import com.mygdx.game.screens.GamePlayScreen;

/**
 * Created by Marcin-PC on 19.08.2017.
 */

public class StaminaManager {
    private float stamina;
    private float maxStamina;
    public static final float THRESHOLD = 40;
    private static final float STARTING_STAMINA = 100;
    private static final float STARTING_MAX_STAMINA = 200;
    private static final float STAMINA_CONSUMPTION = 8;
    private Image staminaBar;
    private Image staminaThreshold;
    private Image staminaValue;


    public StaminaManager(GamePlayScreen game) {
        this.stamina = STARTING_STAMINA;
        this.maxStamina = STARTING_MAX_STAMINA;
        initBar(game);
    }

    private void initBar(GamePlayScreen game) {
        staminaBar = new Image(new Texture(Gdx.files.internal("staminabar.png")));
        staminaThreshold = new Image(new Texture(Gdx.files.internal("staminathreshold.png")));
        staminaValue = new Image(new Texture(Gdx.files.internal("staminavalue.png")));
        staminaBar.setPosition(0, game.getCamera().position.y + GamePlayScreen.HEIGHT*(11/24f));
        staminaBar.setSize(GamePlayScreen.WIDTH,GamePlayScreen.HEIGHT*(1/24f));
        game.getStage().addActor(staminaBar);

        staminaValue.setPosition(game.WIDTH*(88/300f), game.getCamera().position.y + GamePlayScreen.HEIGHT*(11/24f) + GamePlayScreen.HEIGHT*(1/24f)*(3/16f));
        staminaValue.setSize(scaleStaminaValue(),GamePlayScreen.HEIGHT*(1/24f)*(10/16f));
        game.getStage().addActor(staminaValue);

        staminaThreshold.setPosition(game.WIDTH*(88/300f)+game.WIDTH*(THRESHOLD/300f),game.getCamera().position.y + GamePlayScreen.HEIGHT*(11/24f) + GamePlayScreen.HEIGHT*(1/24f)*(3/16f));
        staminaThreshold.setSize(GamePlayScreen.WIDTH*(1/100f),GamePlayScreen.HEIGHT*(1/24f)*(10/16f));
        game.getStage().addActor(staminaThreshold);

    }

    public void staminaIncrease(float velocity){
        this.stamina+= velocity*(-1/2000f);
        if(stamina>maxStamina){
            stamina=maxStamina;
        }

    }

    public void staminaConsumption(){
        this.stamina-= STAMINA_CONSUMPTION;
        if(stamina<0){
            stamina=0;
        }

    }

    public float getStamina() {
        return stamina;
    }

    public void flyingStart(){
        this.stamina-= 20;
    }

    public float getMaxStamina() {
        return maxStamina;
    }

    public void updateStaminaBarPosition(GamePlayScreen game){

        staminaBar.setPosition(0, game.getCamera().position.y + GamePlayScreen.HEIGHT*(11/24f));
    }

    public void updateStaminaValue(GamePlayScreen game) {
        staminaValue.setPosition(game.WIDTH*(88/300f), game.getCamera().position.y + GamePlayScreen.HEIGHT*(11/24f) + GamePlayScreen.HEIGHT*(1/24f)*(3/16f));
        staminaValue.setSize(scaleStaminaValue(),GamePlayScreen.HEIGHT*(1/24f)*(10/16f));
    }

    private float scaleStaminaValue(){
        return stamina/maxStamina * 203/300f * GamePlayScreen.WIDTH;

    }

    public void updateStaminaThresholdPosition(GamePlayScreen game) {
        staminaThreshold.setPosition(game.WIDTH*(88/300f)+game.WIDTH*(THRESHOLD/300f),game.getCamera().position.y + GamePlayScreen.HEIGHT*(11/24f) + GamePlayScreen.HEIGHT*(1/24f)*(3/16f));
    }
}
