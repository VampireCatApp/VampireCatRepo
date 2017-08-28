package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
    private Boolean isHit = false;

    private Vector3 newPosition;
    private Vector2 vector1;
    private Vector2 vector2;

    public Enemy() {


        super(new Texture("enemy.png"));

        newPosition = new Vector3(0, 0, 0);
        vector1 = new Vector2(0, 0);
        vector2 = new Vector2(0, 1);

        this.setOrigin(WIDHT / 2, HEIGHT / 2);
        this.setSize(WIDHT, HEIGHT);
        this.setPosition(STARTING_X, STARTING_Y);
        this.enemyBounds = new Rectangle(STARTING_X, STARTING_Y, WIDHT, HEIGHT);
    }

    public void reposition(OrthographicCamera camera) {
        if (this.getY() > (camera.position.y + camera.viewportHeight / 2)) {
            isHit = false;
            this.clearActions();
            Action action = Actions.rotateBy(-this.getRotation());
            vector2.set(0,1);
            this.addAction(action);
            this.setX(MathUtils.random(0, Gdx.graphics.getWidth()));
            this.setY(this.getY() - (camera.viewportHeight) * 1.5f);
            this.enemyBounds.setPosition(this.getX(), this.getY());

        }
    }

    //TODO not only bound but whole position with it


    public Rectangle getEnemyBounds() {
        if (isHit) {
            enemyBounds.setPosition(0, 0);
        } else {
            enemyBounds.setPosition(this.getX(), this.getY());

        }
        return enemyBounds;
    }

    public void move(Stage stage) {
        float cosProp = 0;
        float sinProp = 0;
        float angle = 0;

        if (!this.hasActions() && (!isHit)) {

            newPosition.set(MathUtils.random(1 / 2f * this.getWidth(), Gdx.graphics.getWidth() - this.getWidth() * 1 / 2f), MathUtils.random(this.getY() - 2 * this.getHeight(), this.getY() + 2 * this.getHeight()), 0);
            vector1.set(vector2.x, vector2.y);
            vector2.set(newPosition.x - this.getX(), newPosition.y - this.getY());
            cosProp = (vector1.x*vector2.x) + (vector1.y*vector2.y);
            sinProp = (vector1.x*vector2.y) - (vector1.y*vector2.x);
            angle = (float) (MathUtils.radiansToDegrees * Math.atan2(sinProp,cosProp));


            Action a = Actions.rotateBy(angle, Math.abs(angle * 0.004f));
            Action b = Actions.moveTo(newPosition.x, newPosition.y, 1.5f);



            System.out.println(" angle : " + angle);

            Image reddot = new Image(new Texture("reddot.png"));
            reddot.setPosition(newPosition.x+1/2f*this.getWidth(), newPosition.y+1/2f*this.getHeight());
            reddot.setSize(20, 20);
            stage.addActor(reddot);

            this.addAction(Actions.sequence(a,b));
        }

    }

    public void setHit(Boolean hit) {
        isHit = hit;
    }
}
