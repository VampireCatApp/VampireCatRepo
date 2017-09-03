package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.VampireCat;
import com.mygdx.game.managers.LvlManager;
import com.mygdx.game.managers.StaminaManager;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.screens.SplashScreen;
import com.mygdx.game.service.SoundService;

public class Player extends Image {
    public final static float TURNING_SPEED = 15;
    public static float GRAVITY = -12;

    private final static int WIDHT = Gdx.graphics.getWidth() / 8;
    private final static int HEIGHT = Gdx.graphics.getHeight() / 10;

    private final static float STARTING_X = Gdx.graphics.getWidth() / 2;
    private final static float STARTING_Y = Gdx.graphics.getHeight() * (4 / 5f);

    private Vector3 position;
    private Vector3 velocity;


    private float horizontalSpeed;
    private Rectangle playerBounds;

    public Player() {
        super(new Texture("cat.png"));
        this.setOrigin(WIDHT / 2, HEIGHT / 2);
        this.setSize(WIDHT, HEIGHT);
        position = new Vector3(STARTING_X - this.getWidth() / 2, STARTING_Y, 0);
        velocity = new Vector3(0, 0, 0);
        this.setPosition(position.x, position.y, 0);
        this.playerBounds = new Rectangle(STARTING_X, STARTING_Y, WIDHT, HEIGHT);
    }

    public void move(float dt) {
        velocity.add(this.horizontalSpeed, GRAVITY, 0);
        velocity.scl(dt);
        position.add(velocity);

        velocity.scl(1 / dt);
        this.setX(position.x);
        this.setY(position.y);
        this.playerBounds.setPosition(position.x, position.y);

    }

    public void wallsCollision() {
        if (this.getX() < Gdx.graphics.getWidth() * (1 / 30f)) {
            this.velocity.set(0, this.velocity.y, this.velocity.z);
            position.set(Gdx.graphics.getWidth() * (1 / 30f), position.y, position.z);
            this.setX(position.x);
        }
        if (this.getX() + this.getWidth() > Gdx.graphics.getWidth() * (29 / 30f)) {
            this.velocity.set(0, this.velocity.y, this.velocity.z);
            position.set(Gdx.graphics.getWidth() * (29 / 30f) - this.getWidth(), position.y, position.z);
            this.setX(position.x);
        }
    }

    public void setVelocityY(float yConstSpeed) {
        this.velocity.set(velocity.x, yConstSpeed, 0);
    }

    public float getHorizontalSpeed() {
        return horizontalSpeed;
    }



    public void setHorizontalSpeed(float horizontalSpeed) {
        this.horizontalSpeed = horizontalSpeed;
    }

    public void entitiesCollision(Rectangle enemyBounds, Enemy enemy, Rectangle obstacleBounds, SoundService soundService, StaminaManager staminaManager, LvlManager lvlManager, VampireCat game) {
        if (this.playerBounds.overlaps(enemyBounds)) {
            enemy.setHit(true);
            enemy.clearActions();
            Action parallelAction = Actions.parallel(Actions.moveBy(Gdx.graphics.getBackBufferWidth()/2, Gdx.graphics.getHeight(), 0.3f)
                    , Actions.rotateBy(720, 0.3f));

            enemy.addAction(parallelAction);
            enemyBounds.setPosition(0, Gdx.graphics.getHeight());
            staminaManager.setStamina(staminaManager.getStamina()+15);
            lvlManager.setExp(lvlManager.getExp()+20);
            soundService.playEnemySound();
        }

        if (this.playerBounds.overlaps(obstacleBounds)) {
            Action rotateAction = Actions.rotateBy(-360,0.4f);
            this.addAction(rotateAction);
            obstacleBounds.setPosition(0, Gdx.graphics.getHeight());
            velocity.set(velocity.x,-300,0);
            soundService.playObstacleSound();

            if (staminaManager.getStamina()<staminaManager.THRESHOLD){
                game.setScreen(new SplashScreen(game));
            }
            staminaManager.setStamina(staminaManager.getStamina()-40);


        }
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public Vector3 getPosition() {
        return position;
    }
}