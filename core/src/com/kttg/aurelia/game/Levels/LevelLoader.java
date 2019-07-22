package com.kttg.aurelia.game.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kttg.aurelia.editor.actions.utils.Setup;
import com.kttg.aurelia.game.units.Friendly.Player;
import com.kttg.aurelia.game.units.Object;
import com.kttg.aurelia.game.units.Ships.PirateScout;
import com.kttg.aurelia.game.units.scenery.Asteroid;
import com.kttg.aurelia.game.units.scenery.Crystal;

import java.util.ArrayList;

/*
* For loading a saved level into the game screen
* - Checks if targeted file exists
* - Reads level data and creates all Objects
* - */
public class LevelLoader {
    static Group objGroup = new Group();
    static ArrayList<Object> objList = new ArrayList<Object>();

    public static ArrayList<Player> playerArrayList = new ArrayList<Player>();
    public static ArrayList<Asteroid> asteroidArrayList = new ArrayList<Asteroid>();
    public static ArrayList<Crystal> crystalArrayList = new ArrayList<Crystal>();

    public void LoadFile(String fileName, Group g, ArrayList<Object> a){
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
    private void loadObject(ArrayList<String> item){
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
        System.out.println("Building tempObj");
        buildObjects(item.get(0), d, vars, labels, true);

//        objList.add(tempObj);
//        tempObj.buildObject();
        objGroup.addActor(tempObj.getImage());

//        for (int i=0;i<objList.size();i++){
//            objList.get(i).buildObject();
//        }

//        System.out.println(MainScreen.getObjectList().size());

    }

    public void buildObjects(String n, Drawable d, ArrayList<Float> v, String[] s, boolean generateID){
        System.out.println("n == "+n);
        System.out.println("Building tempObj into: ");
        if (n.equals("Player")){
            System.out.println("player");
            Player player = new Player(n, d, v, s, true);
            objList.add(player);
        }
        if (n.equals("PirateScout")){
            System.out.println("pirate scout");
            PirateScout player = new PirateScout(n, d, v, s, true);
            objList.add(player);
        }
        if (n.equals("Crystal")){
            System.out.println("crystal");
           Crystal crystal = new Crystal(n, d, v, s, true);
            objList.add(crystal);
        }
        if (n.equals("Asteroid")){
            System.out.println("asteroid");
            Asteroid asteroid = new Asteroid(n, d, v, s, true);
            objList.add(asteroid);
        }
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
