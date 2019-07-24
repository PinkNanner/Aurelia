package com.kttg.aurelia.game.menus;

import com.badlogic.gdx.Game;
import com.kttg.aurelia.editor.actions.utils.GlobalValues;
import com.kttg.aurelia.game.Levels.Level0;
import com.kttg.aurelia.game.Levels.TestLevel;
import com.kttg.aurelia.game.assets.Setup;
import com.kttg.aurelia.game.assets.MenuAnimation;

public class Main extends Game {
    public Game game;
    public static MainMenu mainMenu;
    public void create () {
        game = this;
        Setup.initialize();
        com.kttg.aurelia.editor.actions.utils.Setup.initialize();
        GlobalValues.initialize();
        MenuAnimation.initStar();
		mainMenu = new MainMenu(game);
		game.setScreen(mainMenu);
    }
}
