package com.kttg.aurelia.game.units;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kttg.aurelia.editor.actions.mouse.Mouse;
import com.kttg.aurelia.editor.actions.utils.GlobalValues;
import com.kttg.aurelia.editor.actions.utils.Setup;
import com.kttg.aurelia.editor.windows.MainScreen;
import com.kttg.aurelia.game.Levels.LevelLoader;
import com.kttg.aurelia.game.units.Friendly.Player;
import com.kttg.aurelia.game.units.scenery.Asteroid;
import com.kttg.aurelia.game.units.scenery.Crystal;

import java.util.ArrayList;

/*
* Generic object for being placed in the editor
* - Stores the data for saving and loading any object in the level
* - Unit/Scenery classes read this data to create themselves
* */
public class Object {
    Image image = new Image(Setup.getUIButtonSkin().getDrawable("object")), selectionImg = new Image(Setup.getUIButtonSkin().getDrawable("selection0"));
    Actor imageActor;
    float[] varsArray;
    float tempX, tempY, x, y;
    String[] labels;
    String name, group;
    int id;
    boolean isSetTemps = true;
    Rectangle rectangle = new Rectangle();
    ArrayList<Float> vars = new ArrayList<Float>();

    public Object() {
    }

    public Object(String n, Drawable d, ArrayList<Float> v, String[] s, boolean generateID) {
        name = n;
        group = name;
        image.setDrawable(d);
        vars = v;
        varsArray = new float[vars.size()];
        varsArray = getVarsArray();
//        for (int i=0;i<v.length;i++){vars.add(v[i]);}
//        x = vars.get(0); y = vars.get(1); //TODO: XYWH DO NOT UPDATE WHEN MOVED AFTER PLACING
//        w = vars.get(2); h = vars.get(3);
//        angle = vars.get(4);
        labels = s;
        if (generateID) id = GlobalValues.generateId();
        image.setSize(varsArray[2], varsArray[3]); //"X, Y, W, H, ANGLE, HP, MAXHP"
        setAngle(varsArray[4]);
        image.setPosition(varsArray[0], varsArray[1]);
        imageActor = image;
        selectionImg.setVisible(false);
        selectionImg.setTouchable(Touchable.disabled);
        MainScreen.getSelectImageGroup().addActor(selectionImg);
        setTemps();
    }
    public void moveObject(float mx, float my){
        if (isSetTemps) {
            tempX = Mouse.getX() - varsArray[0];
            tempY = Mouse.getY() - varsArray[1];
            isSetTemps = false;
        }
        varsArray[0] = mx-tempX;
        varsArray[1] = my-tempY;

    }
    public void setTemps(){ isSetTemps = true; }

    public void update(){
        image.setPosition(varsArray[0], varsArray[1]);
        if (selectionImg.isVisible()){
            selectionImg.setDrawable(GlobalValues.getSelectionDrawable());
            selectionImg.setSize(image.getWidth(), image.getHeight());
            selectionImg.setPosition(image.getX(), image.getY());
        }
        rectangle.set(varsArray[0], varsArray[1], varsArray[2], varsArray[3]);
    }
    public void setAngle(float mouseX, float mouseY){
        image.setOrigin(image.getWidth()/2, (image.getHeight()/2));
        selectionImg.setOrigin(image.getOriginX(), image.getOriginY());
        Vector2 mousePos, objPos;
        mousePos = new Vector2(mouseX, mouseY);
        objPos = new Vector2(varsArray[0]+image.getWidth()/2, varsArray[1]+image.getHeight()/2);

        varsArray[4] = (float)Math.atan2(mousePos.y - objPos.y, mousePos.x - objPos.x);

        image.setRotation((varsArray[4]+67.5f) * MathUtils.radiansToDegrees);
        selectionImg.setRotation(image.getRotation());
    }
    public void setAngle(float a){
        image.setOrigin(image.getWidth()/2, (image.getHeight()/2));
        selectionImg.setOrigin(image.getOriginX(), image.getOriginY());
        if (a != 0.0f) {
            image.setRotation((a+67.5f) * MathUtils.radiansToDegrees);
            selectionImg.setRotation(image.getRotation());
        } else {
            image.setRotation((a) * MathUtils.radiansToDegrees);
            selectionImg.setRotation(image.getRotation());
        }
        varsArray[4] = a;
    }

    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public void setImagePosition(float xPos, float yPos){
        image.setPosition(xPos, yPos);
    }
    public Actor getImageActor(){ return imageActor; }
    public void setImageActor(Actor a) { imageActor = a;}
    public ArrayList<Float> getVarsList() {
        vars.clear();
        for (int i=0;i<varsArray.length;i++){
            vars.add(varsArray[i]);
        }//TODO: Make vars the main set of variables manipulated to fix the angle loading issue

        return vars;
    }
    public float[] getVarsArray(){
        float[] f = new float[vars.size()];
        for (int i=0;i<f.length;i++){
            f[i] = vars.get(i);
        }
        return f;
    }
    public void setVars(ArrayList<Float> v) { vars = v; }
    public String[] getLabels() {
        return labels;
    }
    public void setLabels(String[] labels) {
        this.labels = labels;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getID() {
        return id;
    }
    public void setID(int i){
        id = i;
    }
    public void generateId(){ id = GlobalValues.generateId(); }
    public Rectangle getRect(){ return rectangle; }
    public Image getSelectionImg() {
        return selectionImg;
    }

    public void setSelectionImg(Image selectionImg) {
        this.selectionImg = selectionImg;
    }
}
