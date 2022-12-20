package com.team10.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
		public static void main (String[] arg) {
			Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
			config.setTitle("Piazza Panic");
			config.setWindowedMode(1920, 1080);
			config.useVsync(true);
			config.setForegroundFPS(60);
			new Lwjgl3Application(new Eng1Game(), config);
		}
	}
