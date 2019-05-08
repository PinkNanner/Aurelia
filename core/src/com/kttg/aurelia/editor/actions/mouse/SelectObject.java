package com.kttg.aurelia.editor.actions.mouse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kttg.aurelia.editor.actions.utils.Setup;
import com.kttg.aurelia.editor.windows.MainScreen;
import com.kttg.aurelia.editor.windows.UI;
import com.kttg.aurelia.game.units.Object;

import java.util.ArrayList;

public class SelectObject {
    static float tempRectX, tempRectY;
    static ArrayList<Object> selectedObjList = new ArrayList<Object>(), dragRectTempList = new ArrayList<Object>();
    static boolean isOverObject = false;
    static Image dragRectImg = new Image(Setup.getUIButtonSkin().getDrawable("dragImage"));
    static Rectangle dragRect = new Rectangle();

    public static void update(){
//        System.out.println("Updating SelectObject");
        UI.unFocusText();
        if (selectedObjList.size() > 0 && isOverObject){ //Clicking an already selected image to move it
            for(int i=0;i<selectedObjList.size();i++){
                selectedObjList.get(i).moveObject(Mouse.getX(), Mouse.getY());
            }
        }
        if (!isOverObject && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) == false && !Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)){ //Clicking in blank space
            clearSelected();
        }
        if (!isOverObject && !Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) { //Control Clicking
            angleObjects();
        }
        if (!isOverObject && Mouse.isDragging()){
            updateDragRect();
        }
        if (!isOverObject && !Mouse.isDragging && !Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)){ //Setting up for dragging
            Mouse.setIsDragging(true);
            SelectObject.setRectTemps();
        }
        if (!isOverObject && !Mouse.isDragging && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) == false && !Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)){ //Dragging a new box without shift
            Mouse.setIsDragging(true);
            SelectObject.setRectTemps();
            dragRectTempList.clear();
//            System.out.println("Clearing dragRectTempList");
        }
    }
    public static void checkKeys(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.FORWARD_DEL) || Gdx.input.isKeyPressed(Input.Keys.DEL)){
//            System.out.println("Pressing delete");
            for (int i=0;i<selectedObjList.size();i++) {
                        if (MainScreen.getObjectList().contains(selectedObjList.get(i))){
                            selectedObjList.get(i).getSelectionImg().setVisible(false);
                            MainScreen.getObjectGroup().removeActor(selectedObjList.get(i).getImage());
                            MainScreen.getObjectList().remove(selectedObjList.get(i));
            }
        }
        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
//            Mouse.setCursor("placeObject");
//        }
    }
    public static void angleObjects(){
        for (int i=0;i<selectedObjList.size();i++){
            selectedObjList.get(i).setAngle(Mouse.getX(), Mouse.getY());
        }
    }
    public static void stopSelecting(){
        SelectObject.setXYTemps();
        Mouse.isDragging = false;
        dragRectImg.setVisible(false);
        dragRectImg.setSize(0, 0);
        dragRectImg.setPosition(0, 0);
        isOverObject = false;
        selectedObjList.addAll(dragRectTempList);
        dragRectTempList.clear();
    }
    public static void finishSelecting(){
        for (int i=0;i<selectedObjList.size();i++){
            selectedObjList.get(i).getSelectionImg().setVisible(false);
        }
        selectedObjList.clear();
    }
    public static void updateDragRect(){
        float tempW, tempH;
        tempW = Math.abs(tempRectX-Mouse.getX());
        tempH = Math.abs(tempRectY-Mouse.getY());

        if (Mouse.getX()<tempRectX) dragRectImg.setX(Mouse.getX());
        else if (Mouse.getX()>tempRectX) dragRectImg.setX(tempRectX);


        if (Mouse.getY()<tempRectY) dragRectImg.setY(Mouse.getY());
        else if (Mouse.getY()>tempRectY) dragRectImg.setY(tempRectY);

        dragRectImg.setSize(tempW, tempH);
        ArrayList<Object> tempList;
        tempList = MainScreen.getObjectList();
        dragRect.set(new Rectangle(dragRectImg.getX(), dragRectImg.getY(), dragRectImg.getWidth(), dragRectImg.getHeight()));
        for (int i=0;i<tempList.size();i++) {

            if (dragRect.overlaps(tempList.get(i).getRect()) && dragRectTempList.contains(tempList.get(i)) == false) {
                dragRectTempList.add(tempList.get(i));
                tempList.get(i).getSelectionImg().setVisible(true);
//                System.out.println("DragRect overlaps objectRect");
            }
            if (dragRect.overlaps(tempList.get(i).getRect()) == false && dragRectTempList.contains(tempList.get(i)) == true && selectedObjList.contains(tempList.get(i)) == false){
                dragRectTempList.remove(tempList.get(i));
                tempList.get(i).getSelectionImg().setVisible(false);
//                System.out.println("DragRect leaving objectRect");
            }
        }
    }
    public static void setRectTemps(){
        tempRectX = Mouse.getX();
        tempRectY = Mouse.getY();
        dragRectImg.setPosition(Mouse.getX(), Mouse.getY());
        dragRectImg.setSize(1, 1);
        dragRectImg.setTouchable(Touchable.disabled);
        dragRectImg.setVisible(true);
    }
    public static void setXYTemps(){
        for (int i=0;i<selectedObjList.size();i++){
            selectedObjList.get(i).setTemps();
        }
        for (int i=0;i<dragRectTempList.size();i++){
            dragRectTempList.get(i).setTemps();
        }
    }
    public static void addToSelected(Object o){
        o.getSelectionImg().setVisible(true);
        selectedObjList.add(o);
    }
    public static void removeSelected(Object o){
//        if (dragRectTempList.contains(o) == false) {
            o.getSelectionImg().setVisible(false);
            selectedObjList.remove(o);
//        }
//        System.out.println("Removing from list");
    }
    public static void clearSelected(){
        for (int i=0;i<selectedObjList.size();i++){
//            if (dragRectTempList.contains(selectedObjList.get(i)) == false)
                selectedObjList.get(i).getSelectionImg().setVisible(false);
        }
        selectedObjList.clear();
    }
    public static ArrayList<Object> getSelected(){
        return selectedObjList;
    }

    public static boolean getIsOverObject() {
        return isOverObject;
    }

    public static Image getDragRectImg() {
        return dragRectImg;
    }

    public static void setDragRectImg(Image dragRectImg) {
        SelectObject.dragRectImg = dragRectImg;
    }

    public static void setIsOverObject(boolean isOverObject) {
        SelectObject.isOverObject = isOverObject;
    }


    public static void addImgListener(Image i){
//        System.out.println("Adding listener to image");
        i.addListener(new InputListener() { //Listener for when an image is clicked on
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Mouse.isSelectingObject()) {
//                    System.out.println("Clicking image with a listener");
                    for (int i = 0; i < MainScreen.getObjectList().size(); i++) {
                        if (MainScreen.getObjectList().get(i).getImageActor() == event.getListenerActor()) {
                            if (getSelected().contains(MainScreen.getObjectList().get(i)) == false && Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) == false) {
                                if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) == false) {
                                    dragRectTempList.clear();
                                    clearSelected();
                                    UI.fillVariablesScroller(MainScreen.getObjectList().get(i));
//                                    System.out.println("Clearing Old List");
                                }
                                addToSelected(MainScreen.getObjectList().get(i));
//                                System.out.println("In selected = false");
                            }
                            else if (getSelected().contains(MainScreen.getObjectList().get(i)) == true && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) == true) {
//                                dragRectTempList.remove(MainScreen.getObjectList().get(i));
                                removeSelected(MainScreen.getObjectList().get(i));
//                                System.out.println("In selected = true");
                            }
                            break;
                        }
                    }
                }
                return false;
            }
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!Mouse.isDragging() && !Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                    SelectObject.setIsOverObject(true);
//                    System.out.println("Over Object");
                }
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) == false && !Mouse.isDragging() && !Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                    SelectObject.setIsOverObject(false);
//                    System.out.println("Not Over Object");
                }
            }
        });

    }
}
