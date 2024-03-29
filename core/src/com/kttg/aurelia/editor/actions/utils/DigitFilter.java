package com.kttg.aurelia.editor.actions.utils;

import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class DigitFilter implements TextField.TextFieldFilter {

    private char[] accepted;

    public DigitFilter() {
        accepted = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'};
    } //Prevents putting letters into numerical values like x/y

    @Override
    public boolean acceptChar(TextField textField, char c) {
        for (char a : accepted)
            if (a == c) return true;
        return false;
    }}