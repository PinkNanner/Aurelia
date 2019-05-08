package com.kttg.aurelia.editor.actions.mouse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kttg.aurelia.editor.actions.utils.Setup;
import com.kttg.aurelia.editor.windows.MainScreen;
import com.kttg.aurelia.editor.windows.UI;
import com.kttg.aurelia.game.units.Object;

import java.util.ArrayList;

public class PlaceObject {
    static Image previewImage = new Image(Setup.getUIButtonSkin().getDrawable("object"));
    static Object currentObject;
    static Group previewGroup = new Group();

    public static void setCurrentObject(Object obj){
        currentObject = new Object(obj.getName(), obj.getImage().getDrawable(), obj.getVarsList(), obj.getLabels(), false);
        setPreviewImage(currentObject.getImage().getDrawable());
    }
    private static void setPreviewImage(Drawable d){
        previewImage.setDrawable(d);
        previewImage.setColor(1, 1, 1, .65f);
        previewGroup.addActor(previewImage);
        MainScreen.getStage().addActor(previewGroup);
    }

    public static void click() {
        ArrayList<Float> fillList = new ArrayList<Float>();
        for (int i=0;i<UI.getFields().size();i++){
            fillList.add(Float.parseFloat(UI.getFields().get(i).getText()));
        }
        Object tempObj = new Object(currentObject.getName(), currentObject.getImage().getDrawable(),
                fillList, currentObject.getLabels(), true); //this needs to use the vars from Player/Asteroid/Crystal, cannot change before placing I guess?
        MainScreen.getObjectList().add(tempObj);
        MainScreen.getObjectGroup().addActor(tempObj.getImage());

        SelectObject.addImgListener(tempObj.getImage());
    }

    public static Image getPreviewImage(){
        return previewImage;
    }


    public static void update(){
        try {
            if (previewImage.getWidth() != UI.getWField()) previewImage.setWidth(UI.getWField());
            if (previewImage.getHeight() != UI.getHField()) previewImage.setHeight(UI.getHField());
            if (previewImage.getRotation() != UI.getAngleField()) previewImage.setHeight(UI.getHField());
        }catch (IllegalArgumentException e){
        e.printStackTrace();
    }
        if (previewImage.isVisible()) previewImage.setPosition(Mouse.getX(), Mouse.getY());
        UI.setXYFields(previewImage.getX(), previewImage.getY());
        if (!Mouse.isOverUI) previewImage.setRotation(UI.getAngleField());
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            Mouse.setCursor("selectObject");
        }
    }
}
