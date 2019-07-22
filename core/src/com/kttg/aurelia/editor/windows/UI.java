package com.kttg.aurelia.editor.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.kttg.aurelia.editor.actions.mouse.Mouse;
import com.kttg.aurelia.editor.actions.mouse.PlaceObject;
import com.kttg.aurelia.editor.actions.utils.DigitFilter;
import com.kttg.aurelia.editor.actions.utils.Load;
import com.kttg.aurelia.editor.actions.utils.Save;
import com.kttg.aurelia.game.assets.Setup;
import com.kttg.aurelia.game.units.Friendly.Player;
import com.kttg.aurelia.game.units.Object;
import com.kttg.aurelia.game.units.Ships.PirateScout;
import com.kttg.aurelia.game.units.scenery.Asteroid;
import com.kttg.aurelia.game.units.scenery.Crystal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.kttg.aurelia.editor.actions.utils.Setup.getUITextFieldStyle;
import static java.lang.Math.round;

/*
* Creates the level editor UI*/

public class UI {
    static Stage uiStage;
    static Image selectedImage, gridImage;
    static Group gridGroup = new Group();
    static TextButton remove, select, save, load;
    static Label fileLabel;
    static TextField fileField;
    static ArrayList<TextField> variablesFieldList = new ArrayList<TextField>();
    static ScrollPane variablesScroller, unitsScroller;
    static Container unitScrollerContainer, uiContainer, variablesScrollerContainer;
    static Table variablesTable, unitsTable, uiTable;
    static float w, h;
    static ArrayList<Object> unitVars;
    static Map<Integer, Object> objectMap = new HashMap<Integer, Object>();



    public static void initialize(){
        Setup.initialize();
        uiStage = new Stage();
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

        variablesTable = new Table();
        unitsTable = new Table();

        unitsScroller = new ScrollPane(unitsTable, com.kttg.aurelia.editor.actions.utils.Setup.getUIScrollStyle());
        unitScrollerContainer = new Container(unitsScroller).top();


        for (int i=0;i<100;i++) {
            for (int j = 0; j < 100; j++) {
                gridImage = new Image(com.kttg.aurelia.editor.actions.utils.Setup.getUIButtonSkin().getDrawable("grid"));
                gridImage.setPosition(i * gridImage.getWidth(), j * gridImage.getHeight());
                gridGroup.addActor(gridImage);
            }
        }

        selectedImage = new Image(com.kttg.aurelia.editor.actions.utils.Setup.getUIButtonSkin().getDrawable("selection1"));
        unitVars = new ArrayList<Object>();
        setUnitData();
        fillUnitsScroller();
        variablesScroller = new ScrollPane(variablesTable, com.kttg.aurelia.editor.actions.utils.Setup.getUIScrollStyle());
        variablesScrollerContainer = new Container(variablesScroller).top();


        remove = new TextButton("Remove", com.kttg.aurelia.editor.actions.utils.Setup.getUITextButtonStyle());
        select = new TextButton("Select", com.kttg.aurelia.editor.actions.utils.Setup.getUITextButtonStyle());
        save = new TextButton("Save", com.kttg.aurelia.editor.actions.utils.Setup.getUITextButtonStyle());
        load = new TextButton("Load", com.kttg.aurelia.editor.actions.utils.Setup.getUITextButtonStyle());

        fileLabel = new Label("Save/Load Filename: ", com.kttg.aurelia.editor.actions.utils.Setup.getUILabelStyle());
        fileField = new TextField("", com.kttg.aurelia.editor.actions.utils.Setup.getUITextFieldStyle());

        uiTable = new Table();
        uiTable.setSize(uiStage.getWidth()/5, uiStage.getHeight());
//        uiTable.setDebug(true);
        uiTable.top();
        uiTable.add(select).top().maxWidth(uiTable.getWidth());
        uiTable.row();
        uiTable.add(remove).maxWidth(uiTable.getWidth());
        uiTable.row();
        uiTable.add(unitScrollerContainer).maxHeight(unitsScroller.getHeight()).minHeight(unitsScroller.getHeight());
        uiTable.row();
        uiTable.add(variablesScrollerContainer).maxWidth(uiTable.getWidth()).minWidth(uiTable.getWidth()).minHeight(variablesScroller.getHeight()).fill().expand();
        uiTable.row();
        uiTable.add(fileLabel).bottom();
        uiTable.row();
        uiTable.add(fileField).maxWidth(uiTable.getWidth()).minHeight(uiTable.getHeight()/18).fill();
        fileField.setAlignment(Align.center);
        uiTable.row();
        uiTable.add(save).maxWidth(uiTable.getWidth()).maxHeight(save.getHeight()/1.1f);
        uiTable.row();
        uiTable.add(load).maxWidth(uiTable.getWidth()).maxHeight(load.getHeight()/1.1f);
        uiTable.setBackground(com.kttg.aurelia.editor.actions.utils.Setup.getUIButtonSkin().getDrawable("panel"));
        uiTable.pack();

        uiTable.addListener(new InputListener(){
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                Mouse.setIsOverUI(true);
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                Mouse.setIsOverUI(false);
            }
        });
        select.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                PlaceObject.getPreviewImage().setVisible(false);
                Mouse.setCursor("selectObject");
                return false;
            }
        });

        save.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Save.SaveFile();
                return false;
            }
        });
        load.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Load.LoadFile();
                return false;
            }
        });
//        unitsTable.debug();
        unitsTable.setWidth(unitsScroller.getWidth());

        uiContainer = new Container(uiTable);
//        uiContainer.debug();
        uiContainer.setSize(w/5, h);
        uiContainer.setPosition(w-uiTable.getWidth(), h-uiTable.getHeight());
        uiContainer.pack();
        uiStage.addActor(uiContainer);
        uiStage.addActor(selectedImage);
        unitsTable.add(selectedImage).maxWidth(unitsScroller.getWidth()/5).maxHeight(unitsScroller.getHeight()/5);
        selectedImage.setSize(unitsScroller.getWidth()/5, unitsScroller.getHeight()/5);
//        selectedImage.setPosition(unitVars.get(1).getImage().getX(), unitVars.get(1).getImage().getY());
        selectedImage.setVisible(false);
    }


    public static void fillUnitsScroller(){ //Fills with placeable units created in the game folders

            for (int i = 0; i < unitVars.size(); i++) {
                System.out.println("Drawable = " + unitVars.get(i).getImage().getDrawable());
                unitsTable.add(unitVars.get(i).getImage()).maxSize(unitsScroller.getWidth()/5, unitsScroller.getHeight()/5).minSize(unitsScroller.getWidth()/5, unitsScroller.getHeight()/5);
                selectedImage.setSize(unitsScroller.getWidth()/5, unitsScroller.getHeight()/5);


                unitVars.get(i).getImage().addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        //Set UI stuff
                        selectedImage.setVisible(true);
                        selectedImage.setSize(event.getListenerActor().getWidth(), event.getListenerActor().getHeight());
                        selectedImage.setPosition(event.getListenerActor().getX(), event.getListenerActor().getY());
                        for (int i = 0; i < unitVars.size(); i++) {
                            if (unitVars.get(i).getImageActor() == event.getListenerActor()) {
//                                System.out.println("actors name == " + unitVars.get(i).getName());
                                fillVariablesScroller(unitVars.get(i));

                                //Actual event stuff
                                Mouse.setIsPlacingObject(unitVars.get(i));
//                                PlaceObject.setCurrentObject(unitVars.get(i));
                                break;
                            }
                        }
                        Mouse.setCursor("placeObject");

                        return false;
                    }});

                if ((i+1) % 9f == 0f) unitsTable.row();
//                objectMap.put(unitVars.get(i).getID(), unitVars.get(i));
            }
        unitsTable.pack();
    }
                                                        //Loading saved level should initially load all default data, then fill in with saved variables?
    public static void fillVariablesScroller(Object object){ //Fills a Scroll Pane with all the default values of the placing object
                                                            //Changing these values applies to any new object placed, does not yet work for already placed objects, though it loads their custom data
        if (object != null) {
            //Reset for new variables
            variablesTable.clear();
            variablesFieldList.clear();


            float[] vars = object.getVarsArray();
            String[] labels = object.getLabels();
            Label nameLabel = new Label(object.getName(), com.kttg.aurelia.editor.actions.utils.Setup.getUILabelStyle());
            variablesTable.add(nameLabel).left().padLeft(3f).row();
            Label drawableDescLabel = new Label("Drawable: ", com.kttg.aurelia.editor.actions.utils.Setup.getUILabelStyle());
            variablesTable.add(drawableDescLabel).left().padLeft(3f);
            Label drawableLabel = new Label(object.getImage().getDrawable().toString(), com.kttg.aurelia.editor.actions.utils.Setup.getUILabelStyle());
            variablesTable.add(drawableLabel).right().padRight(6f).row();
            for (int i = 0; i < vars.length; i++) {
                Label tempLabel = new Label(labels[i], com.kttg.aurelia.editor.actions.utils.Setup.getUILabelStyle());
                TextField tempField = new TextField(String.valueOf(vars[i]), getUITextFieldStyle());
                variablesFieldList.add(tempField);
                tempField.setTextFieldFilter(new DigitFilter());
                tempField.setAlignment(Align.right);
                variablesTable.add(tempLabel).expand().left().padLeft(3f).padRight(6f);
                variablesTable.add(tempField).maxWidth(uiTable.getWidth()/2.7f).right().padBottom(2f);
                variablesTable.row();
                tempField.addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        for (int i=0;i<variablesFieldList.size();i++) {
                            if (event.getListenerActor().equals(variablesFieldList.get(i))) {
                                variablesFieldList.get(i).selectAll();
                            }
                    }
                    return false;
                    }});
            }
            Label idLabel = new Label("ID: "+object.getID(), com.kttg.aurelia.editor.actions.utils.Setup.getUILabelStyle());
            variablesTable.add(idLabel).left().padLeft(3f);
            variablesTable.row();
        }
    }

    public static void unFocusText(){
        for (int i=0;i<getFields().size();i++){
            MainScreen.getStage().unfocus(getFields().get(i));
        }
    }

    public static Stage getStage(){
        return uiStage;
    }

    public static void setUnitData(){
        unitVars.add(new Player().getObjectInfo()); //Must manually add a line for every created unit
        unitVars.add(new Asteroid().getObjectInfo());
        unitVars.add(new Crystal().getObjectInfo());
        unitVars.add(new PirateScout().getObjectInfo());
    }
    public static void setXYFields(float x, float y){
        variablesFieldList.get(0).setText(String.valueOf(round(x)));
        variablesFieldList.get(1).setText(String.valueOf(round(y)));
    }
    public static void setWField(){

    }
    public static float getWField(){
        return Float.parseFloat(variablesFieldList.get(2).getText());
    }
    public static void setHField(){

    }
    public static float getHField(){
        return Float.parseFloat(variablesFieldList.get(3).getText());
    }
    public static void setAngleField(float a){
        variablesFieldList.get(4).setText(String.valueOf(a));
    }
    public static float getAngleField(){
        return Float.parseFloat(variablesFieldList.get(4).getText());
    }
    public static void setHealthField(){

    }
    public static float getHealthField(){
        return Float.parseFloat(variablesFieldList.get(5).getText());
    }
    public static void setMaxHealthField(){

    }
    public static float getMaxHealthField(){
        return Float.parseFloat(variablesFieldList.get(6).getText());
    }
    public static ArrayList<TextField> getFields(){
        return variablesFieldList;
    }

    public static TextField getFileField() {
        return fileField;
    }

    public static void setFileField(TextField fileField) {
        UI.fileField = fileField;
    }
}
