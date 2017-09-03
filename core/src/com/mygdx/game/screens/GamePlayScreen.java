package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.VampireCat;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.Obstacle;
import com.mygdx.game.entities.Player;
import com.mygdx.game.entities.SideWalls;
import com.mygdx.game.managers.LvlManager;
import com.mygdx.game.managers.StaminaManager;
import com.mygdx.game.ui.FlyButton;
import com.mygdx.game.ui.IClickCallback;
import com.mygdx.game.ui.ITouchUp;
import com.mygdx.game.ui.TurnLeftButton;
import com.mygdx.game.ui.TurnRightButton;


public class GamePlayScreen extends AbstractScreen {

    private Skin skin;
    private Label label, label2;
    private Image bgImg;
    private Player player;
    private SideWalls sideWalls1;
    private SideWalls sideWalls2;
    private Obstacle obstacle;
    private Enemy enemy;
    private TurnLeftButton turnLeftBtn;
    private TurnRightButton turnRightBtn;
    private FlyButton flyBtn;
    private StaminaManager staminaManager;
    private LvlManager lvlManager;
    private Boolean isFlyingClicked;
    private Boolean isFlying;
    private int counter;

    public static int WIDTH;
    public static int HEIGHT;



    public GamePlayScreen(VampireCat game) {
        super(game);
    }

    @Override
    protected void init() {


        startTheMusic();
        isFlyingClicked = false;
        isFlying = false;
        counter = 1;
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();

        //order matters
        initBg();
        initSideWalls();
        initObstacle();
        initEnemey();
        initManagers();
        initPlayer();
        initUIButtons();
        //initFont();
    }

    private void initManagers() {
        this.staminaManager = new StaminaManager(this);
        this.lvlManager = new LvlManager(this);

    }

    //TO DELETE (INDICATORS ONLY)
    private void initFont() {
        skin = new Skin(Gdx.files.internal("skin.json"));
        label = new Label("VelocityY: " + MathUtils.round(player.getVelocity().y), skin);
        label.setFontScale(WIDTH * (1 / 6f) / label.getWidth());
        label.setPosition(WIDTH * (2 / 3f), player.getY() + player.getHeight() + HEIGHT * (1 / 20f));
        stage.addActor(label);

    }

    private void startTheMusic() {
        game.getSoundService().startPlayingMusic(true);
    }


    private void initEnemey() {
        enemy = new Enemy();
        stage.addActor(enemy);

    }

    private void initObstacle() {
        obstacle = new Obstacle();
        stage.addActor(obstacle);
    }

    private void initSideWalls() {
        sideWalls1 = new SideWalls(0);
        sideWalls2 = new SideWalls(-camera.viewportHeight);
        stage.addActor(sideWalls1);
        stage.addActor(sideWalls2);
    }


    private void initUIButtons() {
        turnLeftBtn = new TurnLeftButton(new IClickCallback() {
            @Override
            public void onClick() {
                player.setHorizontalSpeed(player.getHorizontalSpeed() - player.TURNING_SPEED);

                Action action = Actions.rotateBy(15);

                player.addAction(action);
            }
        }, new ITouchUp() {
            @Override
            public void touchUpMethod() {
                player.setHorizontalSpeed(player.getHorizontalSpeed() + player.TURNING_SPEED);

                Action action = Actions.rotateBy(-15);

                player.addAction(action);
            }
        });


        turnRightBtn = new TurnRightButton(new IClickCallback() {
            @Override
            public void onClick() {
                player.setHorizontalSpeed(player.getHorizontalSpeed() + player.TURNING_SPEED);

                Action action = Actions.rotateBy(-15);

                player.addAction(action);

            }
        }, new ITouchUp() {
            @Override
            public void touchUpMethod() {
                player.setHorizontalSpeed(player.getHorizontalSpeed() - player.TURNING_SPEED);

                Action action = Actions.rotateBy(15);

                player.addAction(action);
            }
        });
        flyBtn = new FlyButton(new IClickCallback() {
            @Override
            public void onClick() {
                isFlyingClicked = true;
            }
        }, new ITouchUp() {
            @Override
            public void touchUpMethod() {
                notFlying();
                isFlyingClicked = false;
            }
        });

        stage.addActor(turnLeftBtn);
        stage.addActor(turnRightBtn);
        stage.addActor(flyBtn);
    }

    private void flying() {
        player.GRAVITY = 0;
        player.setVelocityY(-200);
        isFlying = true;
    }

    private void notFlying() {
        player.GRAVITY = -5;
        isFlying = false;
    }

    private void initPlayer() {
        player = new Player();
        stage.addActor(player);
    }

    private void initBg() {
        bgImg = new Image(new Texture("background.png"));
        bgImg.setOrigin(WIDTH / 2, HEIGHT / 2);
        bgImg.setSize(WIDTH, HEIGHT);
        stage.addActor(bgImg);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void staminaAndExpUpdate() {
        // MG how often stamina is updating
        if (player.getPosition().y < (HEIGHT * (11 / 15f) - HEIGHT * (1 / 15f) * counter)) {
            if (!isFlying) {
                staminaManager.staminaIncrease(player.getVelocity().y);
                lvlManager.expIncrease(player.getVelocity().y);
            }
            if (isFlyingClicked && isFlying && (staminaManager.getStamina() > 0)) {
                staminaManager.staminaConsumption();
            }
            if (isFlyingClicked && isFlying && (staminaManager.getStamina() <= 0)) {
                notFlying();
            }
            if(isFlyingClicked && (!isFlying) && (staminaManager.getStamina()>staminaManager.THRESHOLD)){
                if(!isFlying){
                    staminaManager.flyingStart();
                }
                flying();
            }
            counter++;
        }
    }

    private void update() {
        uptadePositions();
        staminaAndExpUpdate();

        //TODO set as one method
        staminaManager.updateStaminaBarPosition(this);
        staminaManager.updateStaminaValue(this);
        staminaManager.updateStaminaThresholdPosition(this);

        lvlManager.updateExpValue(this);
        lvlManager.updateLvlBarPosition(this);



        checkForCollision();
        camera.update();
        stage.act();


    }

    private void uptadePositions() {
        //TODO without changing it cant detect collision as it is (with adding actions to player)
        // it is caused by setting fixed values to Rectangle bounds as class field, no update


        //TODO first check if stamina or exp i out of scope than update drawing
        // in onUpgrade exp method


        player.move(Gdx.graphics.getDeltaTime());
        camera.position.set(WIDTH / 2, player.getY() - HEIGHT * (3 / 10f), 0);
        turnLeftBtn.updatePosition(camera);
        turnRightBtn.updatePosition(camera);
        flyBtn.updatePosition(camera);
        bgImg.setPosition(camera.position.x - WIDTH / 2, camera.position.y - HEIGHT / 2);
        enemy.move(stage);
        sideWalls1.reposition(camera);
        sideWalls2.reposition(camera);
        enemy.reposition(camera);
        obstacle.reposition(camera);
    }

    private void checkForCollision() {
        player.wallsCollision();
        player.entitiesCollision(enemy.getEnemyBounds(), enemy, obstacle.getObstacleBounds(), game.getSoundService(),staminaManager, lvlManager, game);
    }


    public OrthographicCamera getCamera() {
        return camera;
    }

    public Stage getStage(){
        return stage;
    }

}
