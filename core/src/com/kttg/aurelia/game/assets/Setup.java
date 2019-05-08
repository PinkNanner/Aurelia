package com.kttg.aurelia.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Setup {
    //Texture Atlas'
    static TextureAtlas menuAtlas = new TextureAtlas("Game/Sprites/menus.pack");
    static TextureAtlas starsAtlas = new TextureAtlas("Game/Sprites/stars.pack");
    static TextureAtlas spriteAtlas = new TextureAtlas("Game/Sprites/sprites.atlas");

    //Skins
    static Skin menuSkin = new Skin();
    static Skin starsSkin = new Skin();
    static Skin spriteSkin = new Skin();

    //Fonts
    static BitmapFont startMenuFont, labelFont;

    //Styles
    static Label.LabelStyle startMenuStyle = new Label.LabelStyle();
    static Label.LabelStyle menuLabelStyle = new Label.LabelStyle();
    static TextButton.TextButtonStyle menuButtonStyle = new TextButton.TextButtonStyle();



    public static void initialize(){
        menuSkin.addRegions(menuAtlas);
        starsSkin.addRegions(starsAtlas);
        spriteSkin.addRegions(spriteAtlas);

        startMenuFont = new BitmapFont(Gdx.files.internal("Game/Fonts/whiteSmall.fnt"));
        labelFont = new BitmapFont(Gdx.files.internal("Game/Fonts/whiteSmall.fnt"));

        startMenuStyle.font = labelFont;
        startMenuStyle.fontColor = Color.WHITE;

        menuLabelStyle.font = labelFont;
        menuLabelStyle.fontColor = Color.FIREBRICK;

        menuButtonStyle.up = menuSkin.getDrawable("menuInactive0");
        menuButtonStyle.over = menuSkin.getDrawable("menuActive0");
        menuButtonStyle.font = startMenuFont;

    }

    public static Skin getMenuSkin() {return menuSkin;}
    public static Skin getStarsSkin() {return starsSkin;}
    public static Skin getSpriteSkin() {return spriteSkin;}
    public static Label.LabelStyle getMenuLabelStyle() {return menuLabelStyle;}
    public static Label.LabelStyle getStartMenuStyle() {return startMenuStyle;}
    public static TextButton.TextButtonStyle getMenuButtonStyle() {return menuButtonStyle;}
}
