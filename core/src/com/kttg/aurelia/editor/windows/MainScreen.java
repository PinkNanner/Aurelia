package com.kttg.aurelia.editor.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.kttg.aurelia.editor.actions.mouse.Mouse;
import com.kttg.aurelia.editor.actions.mouse.SelectObject;
import com.kttg.aurelia.editor.actions.utils.Camera;
import com.kttg.aurelia.editor.actions.utils.GlobalValues;
import com.kttg.aurelia.editor.actions.utils.InputCore;
import com.kttg.aurelia.game.units.Object;

import java.util.ArrayList;

public class MainScreen implements Screen {
    static Stage stage, uiStage;
    static Group objectGroup = new Group(), selectImageGroup = new Group();
    static ArrayList<Object> objectList = new ArrayList<Object>();
    InputCore inputCore;
    InputMultiplexer inputMultiplexer;

    public void show() {
        stage = new Stage();
        uiStage = UI.getStage();
        inputCore = new InputCore((OrthographicCamera) stage.getCamera());
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(inputCore);
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(uiStage);
        Gdx.input.setInputProcessor(inputMultiplexer);


        stage.addActor(SelectObject.getDragRectImg());
        SelectObject.getDragRectImg().setVisible(false);
        SelectObject.getDragRectImg().setTouchable(Touchable.disabled);
        stage.addActor(UI.gridGroup);
        stage.addActor(objectGroup);
        stage.addActor(selectImageGroup);

//        objectGroup.addListener(new InputListener() { //To determine if the mouse is hovering over an object
//            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
//                if (!Mouse.isDragging() && !Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
//                    SelectObject.setIsOverObject(true);
//                    System.out.println("Over Object");
//                }
//            }
//
//            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
//                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) == false && !Mouse.isDragging() && !Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
//                    SelectObject.setIsOverObject(false);
//                    System.out.println("Not Over Object");
//                }
//            }
//        });
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0.7f, 0.4f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getCamera().update();
        stage.act();
        stage.draw();
        uiStage.getCamera().update();
        uiStage.act();
        uiStage.draw();
        Camera.update(stage);

        Mouse.update(stage);
        GlobalValues.update();
        for (int i = 0; i< objectList.size(); i++){ objectList.get(i).update(); }
    }
    public static Stage getStage(){
        return stage;
    }
    public static Group getObjectGroup() { return objectGroup; }

    public static void setObjectGroup(Group objectGroup) {
        MainScreen.objectGroup = objectGroup;
    }

    public static Group getSelectImageGroup() {
        return selectImageGroup;
    }

    public static void setSelectImageGroup(Group selectImageGroup) {
        MainScreen.selectImageGroup = selectImageGroup;
    }

    public static ArrayList<Object> getObjectList() {
        return objectList;
    }

    public static void setObjectList(ArrayList<Object> objectList) {
        MainScreen.objectList = objectList;
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
