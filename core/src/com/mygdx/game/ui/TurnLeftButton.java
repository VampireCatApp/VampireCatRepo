package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.screens.GamePlayScreen;


public class TurnLeftButton extends Button {


    public TurnLeftButton(final IClickCallback callback, final ITouchUp touchUpInterface) {
        super(turnLeftButtonStyle());
        init(callback, touchUpInterface);
    }

    private void init(final IClickCallback callback, final ITouchUp touchUp) {
        this.setWidth(Gdx.graphics.getWidth() / 3);
        this.setHeight(Gdx.graphics.getWidth() / 6);
        this.setX(0);
        this.setY(0);

        this.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                callback.onClick();
                return super.touchDown(event, x, y, pointer, button);

            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                touchUp.touchUpMethod();
            }



        });
    }

    private static ButtonStyle turnLeftButtonStyle() {
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture("turnbutton.png")));
        buttonStyle.down = new TextureRegionDrawable(new TextureRegion(new Texture("turnbuttondown.png")));

        return buttonStyle;
    }

    @Override
    public boolean isPressed() {
        return super.isPressed();

    }
    public void updatePosition(OrthographicCamera camera){
        this.setY(camera.position.y-Gdx.graphics.getHeight()/2);
    }

}
