package com.team10.game;


import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
		public static void main (String[] arg){
			System.out.println(System.getProperty("os.name"));		// print the os name for debugging cross-platform support
			FileManager fileManager = new FileManager();		// create instance of fileManager class to manage the desktop settings file

			Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
			config.setTitle("Piazza Panic");

			// if the fullscreen variable of the settings file is true, load in fullscreen mode
			if(fileManager.read("fullscreen").equals("true")){
				config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
			}

			config.useVsync(true);
			config.setForegroundFPS(60);
			new Lwjgl3Application(new Eng1Game(), config);
		}
	}
