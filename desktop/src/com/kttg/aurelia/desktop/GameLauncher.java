package com.kttg.aurelia.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kttg.aurelia.game.menus.Main;

public class GameLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
//		config.width = (int) (LwjglApplicationConfiguration.getDesktopDisplayMode().width);
//		config.height = (int) (LwjglApplicationConfiguration.getDesktopDisplayMode().height);
//		config.resizable = false;
//		config.fullscreen = false;
		new LwjglApplication(new Main(), config);
	}
}
