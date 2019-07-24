package com.kttg.aurelia.game.utils;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

import static com.kttg.aurelia.editor.actions.utils.GlobalValues.PPM;

public class InputCore implements InputProcessor {
    OrthographicCamera cam;

    public InputCore(OrthographicCamera cam){
        this.cam = cam;
    }

    public boolean scrolled(int amount) {

        if(amount == 1){
            cam.zoom += .05f;
        }
        else if(amount == -1){
            cam.zoom -= .05f;
        }

        return false;

    }

    public boolean keyDown(int keycode) {
        return false;
    }

    public boolean keyUp(int keycode) {
        return false;
    }

    public boolean keyTyped(char character) {
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
}