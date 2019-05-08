package com.kttg.aurelia.editor.actions.utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class InputCore implements InputProcessor {
    OrthographicCamera cam;
    public boolean deletePressed;

    public InputCore(OrthographicCamera cam){
        this.cam = cam;
    }

    public boolean scrolled(int amount) {

        if(amount == 1){ //Controls scroll wheel zooming
            cam.zoom += .02f;
        }
        else if(amount == -1){
            cam.zoom -= .02f;
        }

        return false;

    }
    public boolean keyDown(int keycode){
        if (keycode == Input.Keys.DEL){
            deletePressed = true;
            System.out.println("PRESSING DEL");
        }
        return false;
    }
    public boolean keyUp(int keycode){
        if (keycode == Input.Keys.DEL){
            deletePressed = false;
        }
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