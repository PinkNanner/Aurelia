package com.kttg.aurelia.game.units.scenery;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kttg.aurelia.game.assets.Setup;
import com.kttg.aurelia.game.units.Object;

import java.util.ArrayList;

public class Asteroid {
    static float x, y, w, h, angle, speedModifier = 0, hp = 50, maxHealth = 50;
    static boolean isMoving = false;
    static Image i;
    static Object objInfo;
    static String name = "Asteroid";
    int id;

    public Asteroid(float xPos, float yPos, float width, float height, float angle, float health){
        x = xPos;
        y = yPos;
        i = new Image(Setup.getSpriteSkin().getDrawable("asteroid"));
        if (width != 0) w = width; else w = i.getWidth();
        if (height != 0) h = height; else h = i.getHeight();
        this.angle = angle;
        i.setSize(w, h);
        i.setOrigin(w/2, h/2);
        i.setRotation(angle * MathUtils.radiansToDegrees);
        hp = health;
        objInfo = new Object(name, getDrawable(), getVariables(), getLabels(), true);
    }
    public Asteroid(){
        i = new Image(Setup.getSpriteSkin().getDrawable("asteroid"));
        w = i.getWidth();
        h = i.getHeight();
        i.setOrigin(w/2, h/2);
        objInfo = new Object(name, getDrawable(), getVariables(), getLabels(), false);
    }

    public static ArrayList<Float> getVariables(){
        ArrayList<Float> f = new ArrayList<Float>();
        f.add(x);
        f.add(y);
        f.add(w);
        f.add(h);
        f.add(angle);
        f.add(hp);
        f.add(maxHealth);
        f.add(speedModifier);
        return f;
    }
    public static String[] getLabels(){
        return new String[]{"x", "y", "w", "h", "angle", "health", "maxHealth", "speedMod"};
    }
    public static Image getImage(){
        return i;
    }
    public static Drawable getDrawable(){
        return i.getDrawable();
    }
    public Object getObjectInfo(){
        return objInfo;
    }
}