package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Marcin-PC on 12.08.2017.
 */

public class Enemy extends Image {
    private final static int WIDHT = Gdx.graphics.getWidth() / 6;
    private final static int HEIGHT = Gdx.graphics.getHeight() / 9;

    private final static float STARTING_X = Gdx.graphics.getWidth() * (3 / 5f);
    private final static float STARTING_Y = Gdx.graphics.getHeight() * (1 / 3f);

    private Rectangle enemyBounds;

    public Enemy() {


        super(new Texture("enemy.png"));
// TO DELETE
        this.setDebug(true);
        this.setOrigin(WIDHT / 2, HEIGHT / 2);
        this.setSize(WIDHT, HEIGHT);
        this.setPosition(STARTING_X, STARTING_Y);
        this.enemyBounds= new Rectangle(STARTING_X, STARTING_Y, WIDHT, HEIGHT);
    }

    public void reposition(OrthographicCamera camera) {
        if (this.getY() > (camera.position.y + camera.viewportHeight / 2)) {
            //TO DELETE
           // this.clearActions();
           // this.setX(MathUtils.random(0,Gdx.graphics.getWidth()));
           // this.setY(this.getY() - (camera.viewportHeight) * 1.5f);
          //  this.enemyBounds.setPosition(this.getX(),this.getY());
            

        }
    }
            //TODO not only bound but whole position with it
    public Rectangle getEnemyBounds() {
        return enemyBounds;
    }
}
