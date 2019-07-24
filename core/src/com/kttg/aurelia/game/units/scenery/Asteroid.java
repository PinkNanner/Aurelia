package com.kttg.aurelia.game.units.scenery;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kttg.aurelia.game.assets.Setup;
import com.kttg.aurelia.game.units.Object;
import com.kttg.aurelia.game.units.PlayerStats;

import java.util.ArrayList;

import static com.kttg.aurelia.editor.actions.utils.GlobalValues.PPM;

public class Asteroid extends Object{
    float x, y, w, h, angle, speedModifier = 0, hp = 50, maxHealth = 50;
    boolean isMoving = false;
    Image i;
    Object objInfo;
    String name = "Asteroid";
    int id;
    Body body;

    public Asteroid(String n, Drawable drawable, ArrayList<Float> vars, String[] labels, boolean generateID) { // For creating a new pirate scout
        super(n, drawable, vars, labels, generateID);
        x = vars.get(0)/PPM; y = vars.get(1)/PPM;
        w = vars.get(2)/PPM; h = vars.get(3)/PPM;
        angle = vars.get(4);
        hp = vars.get(5);
        i = new Image(Setup.getSpriteSkin().getDrawable("asteroid"));
        if (w != 0) w = w; else w = i.getWidth();
        if (h != 0) h = h; else h = i.getHeight();
        i.setSize(w, h);
        i.setOrigin(w/2, h/2);
        i.setRotation(angle * MathUtils.radiansToDegrees);
        i.setPosition(x, y);
        objInfo = new Object(name, getDrawable(), getVariables(), getLabels(), true);
    }
    public Asteroid(){ //For filling out the editor window
        i = new Image(Setup.getSpriteSkin().getDrawable("asteroid"));
        w = i.getWidth();
        h = i.getHeight();
        i.setOrigin(w/2, h/2);
        objInfo = new Object(name, getDrawable(), getVariables(), getLabels(), false);
    }

    public void createPhysics(World world, Stage stage){
        stage.addActor(i);
        BodyDef bDef = new BodyDef();
        bDef.fixedRotation = false;
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(x+(i.getImageWidth()/2), y+(i.getImageHeight()/2));

        PolygonShape polyShape = new PolygonShape();
        polyShape.setAsBox(w/2, h/2);
//        polyShape.setAsBox((i.getWidth()/2)/2/PPM, (i.getHeight()/2)/2/PPM); //Shape starts from center and builds outwards, 32 really equals 64

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polyShape;
        fixtureDef.density = 1f;

        body = world.createBody(bDef);
        body.createFixture(fixtureDef).setUserData(this);

        body.setTransform(x+(i.getImageWidth()/2), y+(i.getImageHeight()/2), angle+67.5f);
    }

    public ArrayList<Float> getVariables(){
        ArrayList<Float> f = new ArrayList<Float>(); //Must add an entry to labels whenever a variable is added here
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

    public void update(){
        i.setPosition(body.getPosition().x-i.getWidth()/2, body.getPosition().y-i.getHeight()/2);
        x = i.getX();
        y = i.getY();
        x = body.getPosition().x; y = body.getPosition().y;
    }

    public String[] getLabels(){
        return new String[]{"x", "y", "w", "h", "angle", "health", "maxHealth", "speedMod"};
    }
    public Image getImage(){
        return i;
    }
    public Drawable getDrawable(){
        return i.getDrawable();
    }
    public Object getObjectInfo(){
        return objInfo;
    }
}
