package com.kttg.aurelia.game.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kttg.aurelia.editor.actions.utils.Setup;
import com.kttg.aurelia.game.units.Object;

import java.util.ArrayList;

public class Load {
    static Group objGroup = new Group();
    static ArrayList<Object> objList = new ArrayList<Object>();

    public static void LoadFile(String fileName, Group g, ArrayList<Object> a){
        Setup.packSprites();
        objGroup = g;
        objList = a;

            FileHandle file = Gdx.files.local("LEVELS/"+fileName+".txt");
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
                createPhysicalWorld();
            } else {
                System.out.println("FILE DOES NOT EXIST IN DIR: "+Gdx.files.local("LEVELS/"));
            }
        }
    private static void loadObject(ArrayList<String> item){
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

        objList.add(tempObj);
        objGroup.addActor(tempObj.getImage());

//        System.out.println(MainScreen.getObjectList().size());

    }

    private static void clearLevel(){
        for (int i=0;i<objList.size();i++){
            objGroup.removeActor(objList.get(i).getImage());
        }
        objGroup.clear();
        objList.clear();
    }


    public static void createPhysicalWorld(){

    }
}
