package com.kttg.aurelia.game.units.Ships;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kttg.aurelia.game.assets.Setup;
import com.kttg.aurelia.game.units.Object;
import com.kttg.aurelia.game.units.PlayerStats;

import java.util.ArrayList;

import static com.kttg.aurelia.game.units.Friendly.Player.getDrawable;

public class PirateScout extends Object {
    static float x, y, w, h, angle, hp, maxHealth, armor;
    static String weapon, name = "PirateScout";
    static boolean isMoving = false;
    static Image i;
    static Object objInfo;
    Body body;
    public PirateScout(String n, Drawable drawable, ArrayList<Float> vars, String[] labels, boolean generateID) { // For creating a new pirate scout
        super(n, drawable, vars, labels, generateID);
//                for (int i=0;i<v.length;i++){vars.add(v[i]);}
//        name = n;
        x = vars.get(0); y = vars.get(1);
        w = vars.get(2); h = vars.get(3);
        angle = vars.get(4);
        hp = vars.get(5);
    }


    public PirateScout(){ //For filling out the editor window
        i = new Image(Setup.getSpriteSkin().getDrawable("enemy"));
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
        ArrayList<Float> f = new ArrayList<Float>(); //Must add an entry to labels whenever a variable is added here
        f.add(x);
        f.add(y);
        f.add(w);
        f.add(h);
        f.add(angle);
        f.add(hp);
        f.add(maxHealth);
        return f;
    }

    public void update(){
        System.out.println("This is a PirateScout");
    }


    public String[] getLabels(){
        return new String[]{"x", "y", "w", "h", "angle", "health", "maxHealth"};
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
