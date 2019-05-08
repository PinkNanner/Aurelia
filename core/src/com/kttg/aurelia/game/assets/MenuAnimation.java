package com.kttg.aurelia.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.kttg.aurelia.game.menus.MainMenu;

import java.util.ArrayList;
import java.util.Random;

import static com.kttg.aurelia.game.menus.MainMenu.pressSpace;

public class MenuAnimation {
    static float animTime = 0, titleAlpha = 1, spaceTimer = 0, w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight(), fadeTimer, fadeAlpha = 0;
    static boolean pressSpaceBoo = false, starGroupBoo = false, transition = false, fadeIn = false, animatePressSpace = true;
    public static Group starGroup = new Group();
    static MenuStar tempMenuStar;
    static ArrayList<MenuStar> menuStarList = new ArrayList<MenuStar>();
    static Random r = new Random();


    static public void animate(float dt) {
        animateMenuButtons();
        updateStar(dt);
        if (animatePressSpace) animatePressSpaceLabel();
        if (transition == true) transitionMainMenu();

        if (fadeIn == true) fadeIn();
    }


    static public void animateMenuButtons() {
        animTime++;
        if (animTime == 25) {
            Setup.menuButtonStyle.over = Setup.menuSkin.getDrawable("menuActive1");

        } else if (animTime >= 50) {
            animTime = 0;
            Setup.menuButtonStyle.over = Setup.menuSkin.getDrawable("menuActive0");
        }
    }

    static public void animatePressSpaceLabel() {
        if (pressSpaceBoo == false) spaceTimer++;
        else spaceTimer--;
        if (spaceTimer == 200) {
            pressSpaceBoo = true;
        } else if (spaceTimer == 0) {
            pressSpaceBoo = false;
        }
        titleAlpha = spaceTimer / 200;
        pressSpace.setColor(1, 1, 1, titleAlpha);
//        System.out.println("titleAlpha = " + titleAlpha);
    }

    static public void transitionMainMenu() {
        spaceTimer -= 2;
        titleAlpha = spaceTimer / 200;
        pressSpace.setColor(1, 1, 1, titleAlpha);
        if (spaceTimer <= 0){
            transition = false;
            animatePressSpace = false;
            setFadeIn(true);
        }
    }

    static public void fadeIn(){
        fadeTimer++;
        fadeAlpha = fadeTimer/200;
        MainMenu.artemis.setColor(1, 1, 1, fadeAlpha);
        MainMenu.exit.setColor(1, 1, 1, fadeAlpha);
        MainMenu.options.setColor(1, 1, 1, fadeAlpha);
        MainMenu.play.setColor(1, 1, 1, fadeAlpha);
        MainMenu.stats.setColor(1, 1, 1, fadeAlpha);
//        System.out.println(fadeAlpha);

        if (fadeAlpha >= 1) fadeIn = false;
    }


    static public void initStar() {
        print("Creating Stars");
        for (int i = 0; i < (r.nextInt(300)+700); i++) {
            tempMenuStar = createStar(2);
            starGroup.addActor(tempMenuStar.getB());
            menuStarList.add(tempMenuStar);
            tempMenuStar = null;
        }
    }

    static MenuStar createStar(int i) {
        MenuStar k = new MenuStar(i);
        return k;
    }
    static void makeStar(int type, Group sGroup) {
        tempMenuStar = createStar(type);//(r.nextInt(2));
        sGroup.addActor(tempMenuStar.getB());
        menuStarList.add(tempMenuStar);
        tempMenuStar = null;
    }

    static void updateStar(float dt) {
//        print("Starlist Size = "+String.valueOf(menuStarList.size()));
        for (int i = 0; i < menuStarList.size(); i++) {
            menuStarList.get(i).update(dt);
            if (menuStarList.get(i).getB().getY() < h - h - .04f) {
//              menuStarList.get(i).getB().setPosition(-10000, -10000);
//				stage.removeActor(menuStarList.get(i).b);
                menuStarList.get(i).setB(null);
                menuStarList.remove(i);
                menuStarList.trimToSize();
                makeStar(3, starGroup);
//                print("Removing stars");
            }
        }
    }

    public static boolean isTransition() {
        return transition;
    }

    public static void setTransition(boolean transition) {
        MenuAnimation.transition = transition;
    }

    public static boolean isFadeIn() {
        return fadeIn;
    }

    public static void setFadeIn(boolean fadeIn) {
        MenuAnimation.fadeIn = fadeIn;
    }

    private static void print(String s) {
        System.out.println(s);
    }
    public static Group getStarGroup(){
        return starGroup;
    }
}