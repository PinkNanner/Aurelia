package com.kttg.aurelia.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kttg.aurelia.editor.windows.Positioner;

public class EditorLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		config.width = (int) (LwjglApplicationConfiguration.getDesktopDisplayMode().width/1.4f);
		config.height = (int) (LwjglApplicationConfiguration.getDesktopDisplayMode().height/1.4f);
//		config.resizable = false;
		//config.fullscreen = true;
		new LwjglApplication(new Positioner(), config);
	}
}
