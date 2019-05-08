package com.kttg.aurelia.editor.actions.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class Setup {
    static TextureAtlas uiAtlas, spritesAtlas;
    static TextButton.TextButtonStyle uiButtonStyle, uiPanelStyle;
    static Label.LabelStyle uiLabelStyle;
    static TextField.TextFieldStyle uiTextFieldStyle;
    static Skin uiButtonSkin = new Skin();
    static List.ListStyle uiListStyle;
    static ScrollPane.ScrollPaneStyle uiScrollStyle;
    static BitmapFont uiFont;
    Preferences prefs = Gdx.app.getPreferences("com-kttg-aureliaeditor-savedData");


    public static void initialize(){
        packSprites();
        uiAtlas = new TextureAtlas(Gdx.files.internal("Editor/UI/UI.atlas"));
        spritesAtlas = new TextureAtlas(Gdx.files.internal("Editor/Sprites/sprites.atlas"));
        uiFont = new BitmapFont(Gdx.files.internal("Editor/Font/black.fnt"));

        uiPanelStyle = new TextButton.TextButtonStyle();
        uiPanelStyle.font = uiFont;
        uiPanelStyle.up = uiButtonSkin.getDrawable("panel");

        uiButtonStyle = new TextButton.TextButtonStyle();
        uiButtonStyle.font = uiFont;
        uiButtonStyle.up = uiButtonSkin.getDrawable("buttonUp");
        uiButtonStyle.down = uiButtonSkin.getDrawable("buttonDown");
        uiButtonStyle.over = uiButtonSkin.getDrawable("buttonOver");


        uiTextFieldStyle = new TextField.TextFieldStyle();
        uiTextFieldStyle.background = uiButtonSkin.getDrawable("txFieldBackground");
        uiTextFieldStyle.cursor = uiButtonSkin.getDrawable("txFieldCursor");
        uiTextFieldStyle.selection = uiButtonSkin.getDrawable("txFieldSelection");
        uiTextFieldStyle.font = uiFont;
        uiTextFieldStyle.fontColor = Color.BLUE; // MUST Have or else crashes

        uiLabelStyle = new Label.LabelStyle();
        uiLabelStyle.font = uiFont;

        uiScrollStyle = new ScrollPane.ScrollPaneStyle();
        uiScrollStyle.hScroll = uiButtonSkin.getDrawable("scrollerHScroll");
        uiScrollStyle.hScrollKnob = uiButtonSkin.getDrawable("scrollerHScrollKnob");
        uiScrollStyle.vScroll = uiButtonSkin.getDrawable("scrollerVScroll");
        uiScrollStyle.vScrollKnob = uiButtonSkin.getDrawable("scrollerVScrollKnob");
        uiScrollStyle.background = uiButtonSkin.getDrawable("scrollerBackground");

        uiListStyle = new List.ListStyle();
        uiListStyle.selection = uiButtonSkin.getDrawable("selection1");
        uiListStyle.font = uiFont;


    }
    public static TextureAtlas getSpritesAtlas(){
        return spritesAtlas;
    }
    public static void packSprites(){
        TexturePacker.process(Gdx.files.internal("Editor\\Sprites\\").toString(), Gdx.files.internal("Editor\\Sprites\\").toString(), "sprites");
        System.out.println("New Sprite Pack Created");
        spritesAtlas = new TextureAtlas(Gdx.files.internal("Editor\\Sprites\\sprites.atlas"));
        uiButtonSkin.addRegions(spritesAtlas);

        TexturePacker.process(Gdx.files.internal("Editor\\UI\\").toString(), Gdx.files.internal("Editor\\UI\\").toString(), "UI");
        System.out.println("New UI Pack Created");
        uiAtlas = new TextureAtlas(Gdx.files.internal("Editor\\UI\\UI.atlas"));
        uiButtonSkin.addRegions(uiAtlas);
    }

    public static Label.LabelStyle getUILabelStyle() {
        return uiLabelStyle;
    }

    public static TextField.TextFieldStyle getUITextFieldStyle() {
        return uiTextFieldStyle;
    }

    public static TextButton.TextButtonStyle getUITextButtonStyle() {
        return uiButtonStyle;
    }

    public static Skin getUIButtonSkin() {
        return uiButtonSkin;
    }
    public static ScrollPane.ScrollPaneStyle getUIScrollStyle() {
        return uiScrollStyle;
    }
    public static List.ListStyle getUIListStyle(){
        return uiListStyle;
    }
}
