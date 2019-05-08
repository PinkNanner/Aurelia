package com.kttg.aurelia.editor.actions.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Camera {
    static Vector2 dragNew, dragOld;

    public static void update(Stage stage) {
        if (Gdx.input.justTouched()) {
            dragNew = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            dragOld = dragNew;
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) { //Drags the camera around using right click
            dragNew = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            if (!dragNew.equals(dragOld)) {
                stage.getCamera().translate(dragOld.x - dragNew.x, dragNew.y - dragOld.y, 0); //Translate by subtracting the vectors
                stage.getCamera().update();
                dragOld = dragNew; //Drag old becomes drag new.
            }
        }
    }
}
