package com.kttg.aurelia.game.menus;

import com.badlogic.gdx.Game;
import com.kttg.aurelia.game.assets.Setup;
import com.kttg.aurelia.game.assets.MenuAnimation;

public class Main extends Game {
    public Game game;
    public static MainMenu mainMenu;
    public void create () {
        game = this;
        Setup.initialize();
        MenuAnimation.initStar();
		mainMenu = new MainMenu(game);
//      game.setScreen(new Level0(game));
		game.setScreen(mainMenu);
    }
}
