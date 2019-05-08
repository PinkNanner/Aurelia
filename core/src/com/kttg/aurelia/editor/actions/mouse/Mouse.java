package com.kttg.aurelia.editor.actions.mouse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kttg.aurelia.game.units.Object;

public class Mouse {
    static float x, y;
    static boolean isPlacingObject = false, isOverUI = false, isClicking = false, isSelectingObject = false, isDragging = false;

    public static void update(Stage stage){
        x = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY())).x;
        y = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY())).y;
//        System.out.println("MouseXY: "+x+"/"+y);

        if (isPlacingObject){
            PlaceObject.update();
        }
        if (isSelectingObject()) {
            SelectObject.checkKeys();
        }
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            MouseLClick.update();
        }
        else if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) == false){
            setIsClicking(false);
            SelectObject.stopSelecting();
        }
    }

    public static void setCursor(String s){
        if (s == "selectObject"){
            SelectObject.setXYTemps();
            isDragging = false;
            isPlacingObject = false;
            isSelectingObject = true;
            PlaceObject.getPreviewImage().setVisible(false);
        }
        else if (s == "placeObject"){
            SelectObject.stopSelecting();
            SelectObject.finishSelecting();
            isSelectingObject = false;
            isPlacingObject = true;
            PlaceObject.getPreviewImage().setVisible(true);
        }
    }


    public static float getX(){
        return x;
    }
    public static float getY(){
        return y;
    }
    public static boolean getIsOverUI(){
        return isOverUI;
    }
    public static void setIsOverUI(boolean b){
        isOverUI = b;
    }
    public static void setIsPlacingObject(Object tempObj){
        isPlacingObject = true;
        PlaceObject.setCurrentObject(tempObj);
    }
    public static void setIsClicking(boolean b){ isClicking = b; }
    public static boolean getIsClicking(){ return isClicking; }

    public static boolean isPlacingObject() {
        return isPlacingObject;
    }

    public static void setPlacingObject(boolean placingObject) {
        Mouse.isPlacingObject = placingObject;
    }

    public static boolean isSelectingObject() {
        return isSelectingObject;
    }

    public static void setIsSelectingObject(boolean isSelectingObject) {
        Mouse.isSelectingObject = isSelectingObject;
    }

    public static boolean isDragging() {
        return isDragging;
    }

    public static void setIsDragging(boolean isDragging) {
        Mouse.isDragging = isDragging;
    }
}
