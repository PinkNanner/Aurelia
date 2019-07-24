package com.kttg.aurelia.game.Levels;


import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kttg.aurelia.game.units.Friendly.Player;
import com.kttg.aurelia.game.units.Object;
import com.kttg.aurelia.game.utils.InputCore;

import java.util.ArrayList;

import static com.kttg.aurelia.editor.actions.utils.GlobalValues.PPM;


/*
* Creates a game world in the physics engine,
* loads the test world by pressing 'space'
* */

public class TestLevel implements Screen {
    Game game;
    Stage stage;
    Group objectGroup = new Group();
    ArrayList<Object> objectArrayList = new ArrayList<Object>();
    LevelLoader levelLoader = new LevelLoader();
    OrthographicCamera cam;
    World testWorld;
    InputCore inputCore;
    InputMultiplexer inputMultiplexer = new InputMultiplexer();
    Box2DDebugRenderer debugRenderer;
    Player player;
    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();

    public TestLevel(Game game){ this.game = game; }

    public void show() {
        testWorld = new World(new Vector2(0, 0), false);
        testWorld.setContactListener(new CollisionListener());
        debugRenderer = new Box2DDebugRenderer();
        stage = new Stage() {
            public boolean keyDown(int keycode) {
                if ((keycode == Input.Keys.BACK) || (keycode == Input.Keys.ESCAPE)) {
                    Gdx.app.exit();
                }
                return super.keyDown(keycode);
            }
        };
//        stage.clear();


        cam = new OrthographicCamera(stage.getCamera().viewportWidth/PPM, stage.getCamera().viewportHeight/PPM); //Controls camera starting zoom
//        cam = new OrthographicCamera(w/PPM, h/PPM);
        stage.getViewport().setCamera(cam);

        inputCore = new InputCore(cam);
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(inputCore);

        Gdx.input.setInputProcessor(inputMultiplexer);

        levelLoader.LoadFile("level0", objectGroup, objectArrayList); //level0 is the default test world
        int playerLoc = 0;
        for (int i=0;i<objectArrayList.size();i++) { //Build physics bodies for all actors, remove player from generic group
            if (objectArrayList.get(i).name.equals("Player")){
                player = (Player) objectArrayList.get(i);
                playerLoc = i;
            }
            else objectArrayList.get(i).createPhysics(testWorld, stage);

        }
        objectArrayList.remove(playerLoc);
        player.createPhysics(testWorld, stage);

        //        stage.addActor(objectGroup);
        System.out.println(stage.getWidth()+" "+stage.getHeight());
        System.out.println(stage.getViewport().getScreenWidth()+" "+stage.getViewport().getScreenHeight());
        System.out.println(stage.getCamera().viewportWidth+" "+stage.getCamera().viewportHeight);
    }


    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        testWorld.step(1/60f, 6 ,2);
        stage.act();
        stage.draw();
        updateCamera();

        debugRenderer.render(testWorld, cam.combined);

    }

    public void update(float delta){
        player.update(stage, delta, cam);

        for (int i=0;i<objectArrayList.size();i++) { //Updates every object in the level
            objectArrayList.get(i).update();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            stage.clear();
            testWorld = new World(new Vector2(0, 0), false);
            testWorld.setContactListener(new CollisionListener());
            levelLoader.LoadFile("level0", objectGroup, objectArrayList);

            int playerLoc = 0;
            for (int i=0;i<objectArrayList.size();i++) { //Build physics bodies for all actors, remove player from generic group
                objectArrayList.get(i).createPhysics(testWorld, stage);

                if (objectArrayList.get(i).name.equals("Player")){
                    player = (Player) objectArrayList.get(i);
                    playerLoc = i;
                }
            }
            objectArrayList.remove(playerLoc);
        }
    }

    public void updateCamera(){
        cam.position.set(player.getX(), player.getY(), 1);
        cam.update();
    }

    public void resize(int width, int height) {
        
    }

    public void pause() {

    }


    public void resume() {

    }


    public void hide() {

    }

    public void dispose() {

    }
}
