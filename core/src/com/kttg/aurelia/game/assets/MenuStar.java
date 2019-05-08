package com.kttg.aurelia.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.Random;

public 	class MenuStar { //Main menu stars, to be rewritten soonTM
    Image b;
    float PPM = 2f, w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight(), x, y;
    int typ, num0;
    float fade, fallTimer, fadeTimer;
    Random r = new Random();
    boolean up = true, down = false, isFormation = false;
    Skin starSkin = Setup.getStarsSkin();

    public MenuStar(int type){
        typ = type;
        fade = r.nextFloat()+.05f;
        if (typ < 1 || typ > 8){
            if (r.nextInt(1000)<= 910) b = new Image(starSkin.getDrawable("star"+r.nextInt(7)+""+r.nextInt(2)));
            else {
                b = new Image(starSkin.getDrawable("starFormation"+r.nextInt(8)));
                b.setSize(r.nextInt(40)+5, r.nextInt(40)+5);
                isFormation = true;
            }
            b.setPosition((float)r.nextInt((int)w), h+.5f);
        } else if (typ == 1){
            b = new Image(starSkin.getDrawable("starFormation"+r.nextInt(8)));
            b.setPosition((float)r.nextInt((int)w), h+.5f);
            isFormation = true;
        }
        else if (typ == 2){ //For filling a whole screen with standard stars
            b = new Image(starSkin.getDrawable("star0"+r.nextInt(2)));
            x = r.nextInt((int) w);
            y = r.nextInt((int) h);
            b.setPosition(x, y);
        }
        else if (typ == 3){
            //For creating stars above the screen to scroll down
            b = new Image(starSkin.getDrawable("star0"+r.nextInt(2)));
            x = r.nextInt((int) w);
            y = h+.04f;
            b.setPosition(x, y);
        } else if (typ == 4){
            if (r.nextBoolean() == true)num0 = 0; else num0 = 4;
            if (r.nextInt(1000)<= 980) b = new Image(starSkin.getDrawable("star"+num0+""+r.nextInt(2)));
            else {
                b = new Image(starSkin.getDrawable("starFormation"+r.nextInt(8)));
                b.setSize(r.nextInt(40)+5, r.nextInt(40)+5);
                isFormation = true;
            }
            b.setPosition((float)r.nextInt((int)w), h+50);
        } else if (typ == 5){
            if (r.nextBoolean() == true)num0 = 2; else num0 = 4;
            if (r.nextInt(1000)<= 980) b = new Image(starSkin.getDrawable("star"+num0+""+r.nextInt(2)));
            else {
                b = new Image(starSkin.getDrawable("starFormation"+r.nextInt(8)));
                b.setSize(r.nextInt(40)+5, r.nextInt(40)+5);
                isFormation = true;
            }
            b.setPosition((float)r.nextInt((int)w), h+50);
        } else if (typ == 6){
            if (r.nextBoolean() == true)num0 = 2; else num0 = 3;
            if (r.nextInt(1000)<= 980) b = new Image(starSkin.getDrawable("star"+num0+""+r.nextInt(2)));
            else {
                b = new Image(starSkin.getDrawable("starFormation"+r.nextInt(8)));
                b.setSize(r.nextInt(40)+5, r.nextInt(40)+5);
                isFormation = true;
            }
            b.setPosition((float)r.nextInt((int)w), h+50);
        } else if (typ == 7){
            if (r.nextBoolean() == true)num0 = 5; else num0 = 3;
            if (r.nextInt(1000)<= 980) b = new Image(starSkin.getDrawable("star"+num0+""+r.nextInt(2)));
            else {
                b = new Image(starSkin.getDrawable("starFormation"+r.nextInt(8)));
                b.setSize(r.nextInt(40)+5, r.nextInt(40)+5);
                isFormation = true;
            }
            b.setPosition((float)r.nextInt((int)w), h+50);
        }  else if (typ == 8){
            if (r.nextBoolean() == true)num0 = 5; else num0 = 6;
            if (r.nextInt(1000)<= 980) b = new Image(starSkin.getDrawable("star"+num0+""+r.nextInt(2)));
            else {
                b = new Image(starSkin.getDrawable("starFormation"+r.nextInt(8)));
                b.setSize(r.nextInt(40)+5, r.nextInt(40)+5);
                isFormation = true;
            }
            b.setPosition((float)r.nextInt((int)w), h+50);
        }
        b.setColor(1, 1, 1, fade);
        b.setRotation(r.nextInt(361));
        b.setSize(b.getWidth()/PPM, b.getHeight()/PPM);
        fallTimer = r.nextFloat();
        fadeTimer = r.nextFloat()+.35f;
    }

    public void update(float dt){
        fadeTimer -= dt;
        fallTimer -= dt;
        if (fadeTimer <= 0 && isFormation == false){
            if (up == true){
                fade+=.0000001f;
                if (fade >= 1.0f){
                    up = false;
                    down = true;
                }
            }
            else if (down == true){
                fade-=.0000001f;
                if (fade <= 0.0f){
                    down = false;
                    up = true;
                }
            }
//            System.out.println(fade+"/"+up+"/"+down);
            fadeTimer = r.nextFloat()+.35f;
        }
        if (fallTimer <= 0){
            y-=.07f;
            fallTimer = r.nextFloat();
        }
        b.setPosition(x, y);
        b.setColor(1, 1, 1, fade);
    }
    public Image getB(){
        return b;
    }
    public void setB(Image temp){
        b = temp;
    }
}