package com.potatospy.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.potatospy.KanaBucket;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = KanaBucket.APP_WIDTH;
		config.height = KanaBucket.APP_HEIGHT;
		new LwjglApplication(new KanaBucket(), config);
	}
}
