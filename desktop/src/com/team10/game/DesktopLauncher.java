package com.team10.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

	public class DesktopLauncher {
		public static void main (String[] arg) {
			Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
			config.setTitle("Piazza Panic");
			config.setWindowedMode(1820, 880);
			config.useVsync(true);
			config.setForegroundFPS(60);
			new Lwjgl3Application(new Eng1Game(), config);

		}
	}

//TODO: Add a way to change the screen size automatically based on the screen size of the user's computer