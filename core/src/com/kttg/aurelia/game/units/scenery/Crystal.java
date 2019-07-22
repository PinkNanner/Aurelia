package com.kttg.aurelia.game.units.scenery;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kttg.aurelia.game.assets.Setup;
import com.kttg.aurelia.game.units.Object;
import com.kttg.aurelia.game.units.PlayerStats;

import java.util.ArrayList;

public class Crystal extends Object{
    static float x, y, w, h, angle, speedModifier = 0, hp = 50, maxHealth = 50;
    static boolean isMoving = false;
    static Image i;
    static Object objInfo;
    static String name = "Crystal";
    int id;

    public Crystal(String n, Drawable drawable, ArrayList<Float> vars, String[] labels, boolean generateID) { // For creating a new pirate scout
        super(n, drawable, vars, labels, generateID);
        x = vars.get(0); y = vars.get(1);
        w = vars.get(2); h = vars.get(3);
        angle = vars.get(4);
        hp = vars.get(5);
        i = new Image(Setup.getSpriteSkin().getDrawable("enemyBossBlue"));
        if (w != 0) w = w; else w = i.getWidth();
        if (h != 0) h = h; else h = i.getHeight();
        i.setSize(w, h);
        i.setOrigin(w/2, h/2);
        i.setRotation(angle * MathUtils.radiansToDegrees);
        objInfo = new Object(name, getDrawable(), getVariables(), getLabels(), true);
    }
    public Crystal(){ //For filling out the editor window
        i = new Image(Setup.getSpriteSkin().getDrawable("enemyBossBlue"));
        w = i.getWidth();
        h = i.getHeight();
        i.setOrigin(w/2, h/2);
        objInfo = new Object(name, getDrawable(), getVariables(), getLabels(), false);
    }

    public static ArrayList<Float> getVariables(){
        ArrayList<Float> f = new ArrayList<Float>(); //Must add an entry to labels whenever a variable is added here
        f.add(x);
        f.add(y);
        f.add(w);
        f.add(h);
        f.add(angle);
        f.add(maxHealth);
        return f;
    }

    public void update(){
        System.out.println("This is a Crystal");
    }

    public String[] getLabels(){
        return new String[]{"x", "y", "w", "h", "angle", "maxHealth"};
    }
    public Image getImage(){
        return i;
    }
    public static Drawable getDrawable(){
        return i.getDrawable();
    }
    public Object getObjectInfo(){
        return objInfo;
    }
}
