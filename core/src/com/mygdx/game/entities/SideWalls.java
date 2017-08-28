package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Marcin-PC on 12.08.2017.
 */

public class SideWalls extends Image {

        private final static int WIDHT = Gdx.graphics.getWidth();
        private final static int HEIGHT = Gdx.graphics.getHeight();

        public SideWalls(float yPosition) {
            super(new Texture("sidewall.png"));
            this.setOrigin(WIDHT / 2, HEIGHT / 2);
            this.setSize(WIDHT, HEIGHT);
            this.setPosition(0,yPosition);

        }
        public void reposition(OrthographicCamera camera){
            if (this.getY()>(camera.position.y+camera.viewportHeight/2)){
                this.setY(this.getY()-(camera.viewportHeight)*2);
            }
        }
    }
