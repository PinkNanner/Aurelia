package com.kttg.aurelia.game.Levels;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kttg.aurelia.game.units.Object;

import java.util.ArrayList;


/*
* Creates a game world in the physics engine,
* loads the test world by pressing "space"
* */

public class TestLevel implements Screen {
    static Game game;
    static Stage stage = new Stage() {
        public boolean keyDown(int keycode) {
            if ((keycode == Input.Keys.BACK) || (keycode == Input.Keys.ESCAPE)) {
                Gdx.app.exit();
            }
            return super.keyDown(keycode);
        }
    };
    float PPM = 100f;
    static Group objectGroup = new Group();
    static ArrayList<Object> objectArrayList = new ArrayList<Object>();
    OrthographicCamera cam;
    static World testWorld;
    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    public TestLevel(Game game){ this.game = game; }

    public void show() {
        testWorld = new World(new Vector2(0, 0), false);
        testWorld.setContactListener(new CollisionListener());
        Load.LoadFile("level0", objectGroup, objectArrayList); //level0 is the default test world
        stage.addActor(objectGroup);
        cam = new OrthographicCamera(stage.getCamera().viewportWidth/PPM, stage.getCamera().viewportHeight/PPM); //Controls camera starting zoom
    }


    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        testWorld.step(1/60f, 6 ,2);
        debugRenderer.render(testWorld, cam.combined);
        stage.act();
        stage.draw();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            Load.LoadFile("level0", objectGroup, objectArrayList);
        }

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
