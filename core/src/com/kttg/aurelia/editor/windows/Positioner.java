package com.kttg.aurelia.editor.windows;

import com.badlogic.gdx.Game;
import com.kttg.aurelia.editor.actions.utils.Setup;

public class Positioner extends Game {
    Game game;

    public void create() {
        game = this;
        Setup.initialize();
        UI.initialize();
        System.out.println("Initializing UIStage");
        setScreen(new MainScreen());

    }
}

