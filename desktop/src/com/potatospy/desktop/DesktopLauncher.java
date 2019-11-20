package com.potatospy.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.potatospy.KanaBucket;


// DesktopLauncher binds application Height Width and x y position

public class DesktopLauncher {
	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		// Application Height Width and x y position
		config.width = KanaBucket.APP_WIDTH;
		config.height = KanaBucket.APP_HEIGHT;

		config.x=-1;config.y=-1; // Centre window on screen


		new LwjglApplication(new KanaBucket(), config);
	}
}
