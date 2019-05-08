package com.kttg.aurelia.editor.actions.utils;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class GlobalValues {

    static int globalId = -1;
    static int animTimer;
    static Drawable selectionDrawable = Setup.getUIButtonSkin().getDrawable("selection0");


    public static int generateId(){ //Generate a new set of ID's anytime a map is loaded
        globalId+=1;
//        System.out.println("generating new id: "+globalId);
        return globalId;
    }
    public static void update(){
        animateDrawables();
    }

    public static void animateDrawables(){
        animTimer++;
        if (animTimer == 50) selectionDrawable = Setup.getUIButtonSkin().getDrawable("selection0");
        if (animTimer == 100) {
            animTimer = 0;
            selectionDrawable = Setup.getUIButtonSkin().getDrawable("selection1");
        }
    }
    public static Drawable getSelectionDrawable() {
        return selectionDrawable;
    }
}
