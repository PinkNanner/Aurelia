package com.kttg.aurelia.game.menus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.kttg.aurelia.game.Levels.TestLevel;
import com.kttg.aurelia.game.assets.Setup;
import com.kttg.aurelia.game.assets.MenuAnimation;

/*
* Creates the main menu for the game:
* - Pressing space causes the menu items to fade in
* - Pressing play loads the test level
* */

public class MainMenu implements Screen {
    static public Game game;
    static public Label artemis, pressSpace;
    static public TextButton play, stats, options, exit;
    float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
    static public Group starGroup = MenuAnimation.starGroup;
    StretchViewport viewport = new StretchViewport(w, h);

    static public Stage stage = new Stage() {
        public boolean keyDown(int keycode) {
            if ((keycode == Input.Keys.BACK) || (keycode == Input.Keys.ESCAPE)) {
                Gdx.app.exit();
            }
            return super.keyDown(keycode);
        }
    };

    public MainMenu(Game game){
        this.game = game;
    }
    @Override
    public void show() {
//        stage.setViewport(viewport);
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

        pressSpace = new Label("Press Space", Setup.getStartMenuStyle());
        pressSpace.setPosition((w / 2) - (pressSpace.getWidth() / 2), h / 20);

        artemis = new Label("Artemis", Setup.getMenuLabelStyle());
        artemis.setPosition((w / 2) - artemis.getWidth() / 2, h / 1.2f); //Use FreeTypeFontGenerator to make larger fonts from ttf files
        artemis.setColor(1, 1, 1, 0);

        play = new TextButton("Play", Setup.getMenuButtonStyle());
        play.setPosition(w / 2 - play.getWidth() / 2, h / 1.5f);
        play.setColor(1, 1, 1, 0);
        play.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(new TestLevel(game));
                return false;
            }
        });

        stats = new TextButton("Stats", Setup.getMenuButtonStyle());
        stats.setPosition(w / 2 - stats.getWidth() / 2, play.getY()-play.getHeight());
        stats.setColor(1, 1, 1, 0);
        stats.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){

                return false;
            }
        });

        options = new TextButton("Options", Setup.getMenuButtonStyle());
        options.setPosition(w/2-options.getWidth()/2, stats.getY()-stats.getHeight());
        options.setColor(1, 1, 1, 0);
        options.addListener(new InputListener(){
            public boolean touchDown(InputEvent even, float x, float y, int pointer, int button){

                return false;
            }
        });

        exit = new TextButton("Exit", Setup.getMenuButtonStyle());
        exit.setPosition(w/2-exit.getWidth()/2, h/20);
        exit.setColor(1, 1, 1, 0);
        exit.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Gdx.app.exit();
                return false;
            }
        });

        stage.addActor(starGroup);
        stage.addActor(pressSpace);

        stage.addActor(play);
        stage.addActor(stats);
        stage.addActor(options);
        stage.addActor(exit);
        stage.addActor(artemis);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        MenuAnimation.animate(Gdx.graphics.getDeltaTime());
        stage.act();
        stage.draw();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            MenuAnimation.setTransition(true);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
