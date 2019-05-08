package com.kttg.aurelia.editor.actions.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.kttg.aurelia.editor.windows.MainScreen;
import com.kttg.aurelia.editor.windows.UI;
import com.kttg.aurelia.game.units.Object;

import java.util.ArrayList;

/*
* Reads all placed Objects and saves them to .txt file
* - Allows for adding as many custom values with labels as you want, determined by the units type
* */

public class Save {
    public static void SaveFile(){
        UI.unFocusText();
        FileHandle file = Gdx.files.local("LEVELS/"+UI.getFileField().getText()+".txt");
        file.deleteDirectory();
        ArrayList<Object> workingList;
        workingList = MainScreen.getObjectList();
        String[] output = new String[MainScreen.getObjectList().size()];

        for (int i=0;i<workingList.size();i++){
            output[i] = "";
            output[i]+= workingList.get(i).getName()+" "+workingList.get(i).getImage().getDrawable()+" ";

            output[i]+= workingList.get(i).getVarsList().size()+" "; //Saving number of variables in order to load later
            for (int k=0;k<workingList.get(i).getVarsList().size();k++){
                output[i]+= workingList.get(i).getVarsList().get(k)+" ";
            }

            output[i]+= workingList.get(i).getLabels().length+" "; //Saving number of labels in order to load later
            for (int k=0;k<workingList.get(i).getLabels().length;k++){
                output[i]+= workingList.get(i).getLabels()[k]+" ";
            }
            output[i]+= ": ";

            System.out.println("Saving object: "+output[i]);
            file.writeString(output[i], true);
        }

    }
}
