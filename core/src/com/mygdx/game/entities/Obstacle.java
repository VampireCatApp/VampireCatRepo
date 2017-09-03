package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Marcin-PC on 12.08.2017.
 */

public class Obstacle extends Image {
    private final static int WIDHT = Gdx.graphics.getWidth() / 3;
    private final static int HEIGHT = Gdx.graphics.getHeight() / 36;

    private final static float STARTING_X = 0;
    private final static float STARTING_Y = Gdx.graphics.getHeight() / 2;

    private Rectangle obstacleBounds;

    public Obstacle() {
        super(new Texture("obstacle.png"));
        this.setOrigin(WIDHT / 2, HEIGHT / 2);
        this.setSize(WIDHT, HEIGHT);
        this.setPosition(STARTING_X, STARTING_Y);
        this.obstacleBounds = new Rectangle(STARTING_X, STARTING_Y, WIDHT, HEIGHT);
    }

    public void reposition(OrthographicCamera camera) {
        if (this.getY() > (camera.position.y + camera.viewportHeight / 2)) {
            this.setX(MathUtils.random(0, Gdx.graphics.getWidth()));
            this.setY(this.getY() - (camera.viewportHeight));
            this.obstacleBounds.setPosition(this.getX(), this.getY());
        }
    }

    public Rectangle getObstacleBounds() {
        return obstacleBounds;
    }
}