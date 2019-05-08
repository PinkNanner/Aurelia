package com.kttg.aurelia.editor.actions.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kttg.aurelia.editor.actions.mouse.Mouse;
import com.kttg.aurelia.editor.actions.mouse.SelectObject;
import com.kttg.aurelia.editor.windows.MainScreen;
import com.kttg.aurelia.editor.windows.UI;
import com.kttg.aurelia.game.units.Object;

import java.util.ArrayList;

/*
 * For loading a saved level into the editor screen
 * - Checks if targeted file exists
 * - Reads level data and creates all Objects
 * - */

public class Load {

    public static void LoadFile(){
        Setup.packSprites();

        if (!UI.getFileField().getText().isEmpty()){
            FileHandle file = Gdx.files.local("LEVELS/"+UI.getFileField().getText()+".txt");
            if (file.exists()){
                System.out.println("File exists and loading...");
                clearLevel();

                ArrayList<String> itemValues = new ArrayList<String>();
                String input = file.readString();
//                int count = 0;
                String currentVar = "";
                for (int i=0;i<input.length();i++){
                    if (input.charAt(i) == ' '){
                        itemValues.add(currentVar);
//                        System.out.println("currentVar = "+currentVar);
                        currentVar = "";
                    }
                    if (input.charAt(i) != ' ' && input.charAt(i) != ':'){
//                        System.out.println("adding: "+String.valueOf(input.charAt(i)));
                        currentVar += String.valueOf(input.charAt(i));
                    }
                    if (input.charAt(i) == ':'){ //Object data is fully loaded here
                        loadObject(itemValues);
                        itemValues.clear();
//                        System.out.println("input = "+input);
                        input = input.substring(i + 2);
//                        System.out.println("inputSubstring = "+input);
                        i = -1;
                    }
                    if (input.isEmpty()) break;
                }
            } else {
                System.out.println("FILE DOES NOT EXIST IN DIR: "+Gdx.files.local("LEVELS/"));
            }
        }
    }





    private static void loadObject(ArrayList<String> item){ //TODO: angle doesn't load correctly, cannot move objects that are loaded in, but listeners for them still work ??
        int varLength = Integer.parseInt(item.get(2));
//        System.out.println("varLength = "+varLength);
        ArrayList<Float> vars = new ArrayList<Float>();
        for (int i=0;i<varLength;i++){
            vars.add(Float.parseFloat(item.get(i+3)));
        }
        String[] labels = new String[varLength];
        for (int i=0;i<varLength;i++){
            labels[i] = item.get(i+4+varLength);
        }
        Drawable d = com.kttg.aurelia.game.assets.Setup.getSpriteSkin().getDrawable(item.get(1));

        Object tempObj = new Object(item.get(0), d, vars, labels, true);

        MainScreen.getObjectList().add(tempObj);
        MainScreen.getObjectGroup().addActor(tempObj.getImage());

        SelectObject.addImgListener(tempObj.getImage());
//        System.out.println(MainScreen.getObjectList().size());

    }






    private static void clearLevel(){
        Mouse.setCursor("selectObject");
        for (int i=0;i<MainScreen.getObjectList().size();i++){
            MainScreen.getObjectList().get(i).getSelectionImg().setVisible(false);
            MainScreen.getObjectGroup().removeActor(MainScreen.getObjectList().get(i).getImage());
        }
        MainScreen.getSelectImageGroup().clear();
        MainScreen.getObjectGroup().clear();
        MainScreen.getObjectList().clear();
        GlobalValues.globalId = -1;
    }
}
