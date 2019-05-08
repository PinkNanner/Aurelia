package com.kttg.aurelia.game.units.Friendly;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kttg.aurelia.game.assets.Setup;
import com.kttg.aurelia.game.units.Object;

import java.util.ArrayList;

public class Player {
    static float x, y, w, h, angle, speedModifier = 0, hp = 100, maxHealth = 100;
    static boolean isMoving = false;
    static Image i;
    static Object objInfo;
    static String name = "Player";
    int id;
    Body body;

    public Player(float xPos, float yPos, float width, float height, float angle, float health){ // For creating a new player
        x = xPos;
        y = yPos;
        i = new Image(Setup.getSpriteSkin().getDrawable("neutral"));
        if (width != 0) w = width; else w = i.getWidth();
        if (height != 0) h = height; else h = i.getHeight();
        this.angle = angle;
        i.setSize(w, h);
        i.setOrigin(w/2, h/2);
        i.setRotation(angle * MathUtils.radiansToDegrees);
        hp = health;
        objInfo = new Object(name, getDrawable(), getVariables(), getLabels(), true);
    }
    public Player(){ //For filling out the editor window
        i = new Image(Setup.getSpriteSkin().getDrawable("neutral"));
        i.setOrigin(w/2, h/2);
        w = i.getWidth();
        h = i.getHeight();
        objInfo = new Object(name, getDrawable(), getVariables(), getLabels(), false);
    }


    public void createPhysics(World world){
        BodyDef bDef = new BodyDef();
        bDef.fixedRotation = true;
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(x, y);

        PolygonShape polyShape = new PolygonShape();
        polyShape.setAsBox(w/2, h/2); //Shape starts from center and builds outwards, 32 really equals 64

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polyShape;
        fixtureDef.density = 1f;

        body = world.createBody(bDef);
        body.createFixture(fixtureDef).setUserData(this);

        body.setTransform(x, y, angle);
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
        return f;
    }
    public static String[] getLabels(){
        return new String[]{"x", "y", "w", "h", "angle", "health", "maxHealth"};
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
