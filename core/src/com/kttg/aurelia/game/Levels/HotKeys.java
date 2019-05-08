package com.kttg.aurelia.game.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class HotKeys {
    public boolean spacePressed;

    public static void CheckKeys(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){

        }

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