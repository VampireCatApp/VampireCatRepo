package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;



public class FlyButton extends Button {

    public FlyButton(final IClickCallback callback, final ITouchUp touchUp) {
        super(FlyButtonStyle());
        init(callback, touchUp);
    }

    private void init(final IClickCallback callback, final ITouchUp touchUp) {
        this.setWidth(Gdx.graphics.getWidth()/3);
        this.setHeight(Gdx.graphics.getWidth()/6);
        this.setX(Gdx.graphics.getWidth()/3);
        this.setY(0);

        this.addListener(new ClickListener(){
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

    private static ButtonStyle FlyButtonStyle() {
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture("flybutton.png")));
        buttonStyle.down = new TextureRegionDrawable(new TextureRegion(new Texture("flybuttondown.png")));

        return buttonStyle;
    }
    public void updatePosition(OrthographicCamera camera){
        this.setY(camera.position.y-Gdx.graphics.getHeight()/2);
    }
}
