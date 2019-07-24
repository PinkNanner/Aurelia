package com.kttg.aurelia.game.units.Friendly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kttg.aurelia.game.assets.Setup;
import com.kttg.aurelia.game.units.Object;
import com.kttg.aurelia.game.units.PlayerStats;
import org.lwjgl.Sys;

import java.util.ArrayList;

import static com.kttg.aurelia.editor.actions.utils.GlobalValues.PPM;


public class Player extends Object {
    float x, y, w, h, angle, hp, maxHealth, armor, speedTimer, speedModifier = 0;
    boolean isMoving = false;
    String weapon, name = "Player";
    Image i;
    Object objInfo;
    Body body;
    public Player(String n, Drawable drawable, ArrayList<Float> vars, String[] labels, boolean generateID) { // For creating a new player
        super(n, drawable, vars, labels, generateID);
//                for (int i=0;i<v.length;i++){vars.add(v[i]);}
//        name = n;
        x = vars.get(0)/PPM; y = vars.get(1)/PPM;
        w = vars.get(2)/PPM; h = vars.get(3)/PPM;
        angle = vars.get(4);
        hp = vars.get(5);
        maxHealth = PlayerStats.getMaxHealth();
        armor = PlayerStats.getArmor();
        weapon = PlayerStats.getWeapon();
        i = new Image(Setup.getSpriteSkin().getDrawable("neutral"));
        i.setSize(w, h);
        i.setOrigin(w/2, h/2);
        i.setPosition(x, y);
        i.setRotation(angle * MathUtils.radiansToDegrees);
    }


    public Player(){ //For filling out the editor window
        i = new Image(Setup.getSpriteSkin().getDrawable("neutral"));
        i.setOrigin(w/2, h/2);
        w = i.getWidth();
        h = i.getHeight();
        objInfo = new Object(name, getDrawable(), getVariables(), getLabels(), false);
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
        return f;
    }
    public String[] getLabels(){
        return new String[]{"x", "y", "w", "h", "angle", "health", "maxHealth"};
    }

    public void createPhysics(World world, Stage stage){
        stage.addActor(i);
        BodyDef bDef = new BodyDef();
        bDef.fixedRotation = true;
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(i.getImageX()+(i.getImageWidth()), i.getImageY()+(i.getImageHeight()));

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(w/2, h/2);  // shape starts from center and builds outwards in both directions, 32 really equals 64

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        this.body = world.createBody(bDef);
        this.body.createFixture(fixtureDef).setUserData(this);

        body.setTransform(x+(i.getImageWidth()), y+(i.getImageHeight()), angle+67.5f);



    }

    public void update(Stage stage, Float delta, Camera cam){
//        System.out.println("This is a Player");
        i.setPosition(body.getPosition().x-i.getWidth()/2, body.getPosition().y-i.getHeight()/2);
        x = i.getX();
        y = i.getY();
        rotatePlayer(stage);
        inputUpdate(delta);
        updateCamera(cam, delta);
//        x = body.getPosition().x; y = body.getPosition().y;

        System.out.println("XY: "+x+" "+y);
        System.out.println("BODY XY: "+body.getPosition().x+" "+body.getPosition().y);
//        body.setTransform(x, y, angle);
        if (hp <= 0){
//            Main.reset();
        }
        if (isMoving){
            speedTimer++;
            if(speedTimer == 10) {
                if(speedModifier<2.5f) speedModifier += .5f;
                speedTimer = 0;
            }
        } else {
            speedTimer-=1;
            if (speedTimer == -5){
                if(speedModifier>.5f) speedModifier-=.5f;
                speedTimer=0;
            }
        }
//        System.out.println("speedModifier = "+speedModifier);

    }
    public void inputUpdate(float delta){
        int horizontalForce = 0;
        int verticalForce = 0;
        isMoving = false;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            horizontalForce-=1;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            horizontalForce+=1;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            verticalForce+=1;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            verticalForce-=1;
            isMoving = true;
        }

        body.setLinearVelocity(horizontalForce * speedModifier, verticalForce * speedModifier);
    }
    public void updateCamera(Camera cam, float delta){
        cam.position.set(x, y, 1);
        cam.update();
    }

    public void hit() {
        hp-=5;
        System.out.println(id+": I've been hit! Health = "+hp);
    }

    public void rotatePlayer(Stage stage){
//        Vector2 mousePos, playerPos;
//        float newAngle;
//        playerPos = new Vector2(body.getPosition());
//        mousePos = new Vector2();
//        mousePos.x = Gdx.input.getX();
//        mousePos.y = Gdx.input.getY();
//        Vector3 worldCoordinates = new Vector3(mousePos.x, mousePos.y, 0);
//        stage.getCamera().unproject(worldCoordinates);
//        mousePos.set(worldCoordinates.x/PPM, worldCoordinates.y/PPM);
//        newAngle = (float)Math.atan2(mousePos.y - playerPos.y, mousePos.x - playerPos.x);
//
//        body.setTransform(body.getPosition(), newAngle);
//        angle = (float) Math.toDegrees(body.getAngle());
//        i.setRotation(angle);

        Vector2 mousePos, playerPos;
        float newAngle;
        playerPos = new Vector2(body.getPosition());
        mousePos = new Vector2();
        mousePos.x = Gdx.input.getX();
        mousePos.y = Gdx.input.getY();
        Vector3 worldCoordinates = new Vector3(mousePos.x, mousePos.y, 0);
        stage.getCamera().unproject(worldCoordinates);
        mousePos.set(worldCoordinates.x, worldCoordinates.y);
        newAngle = (float)Math.atan2(mousePos.y - playerPos.y, mousePos.x - playerPos.x);

        body.setTransform(body.getPosition(), newAngle+67.5f);
        angle = (body.getAngle() * MathUtils.radiansToDegrees);
        i.setRotation(angle);
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
