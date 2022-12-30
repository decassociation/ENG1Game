package com.team10.menus;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team10.game.FileManager;

public class FullscreenModeButton extends MenuButton{
    
    public FullscreenModeButton(int xPos, int yPos, int width, int height, String text, SpriteBatch batch, Camera camera){
        super(xPos, yPos, width, height, text, batch, camera);
    }

    @Override
    public void clickFunction(){
        FileManager fileManager = new FileManager();		// create instance of fileManager class to manage the desktop settings file
        Graphics.DisplayMode currentMode = Gdx.graphics.getDisplayMode();

        if(fileManager.read("fullscreen").equals("true")){
            fileManager.write("fullscreen", "false");
            setText("fullscreen: false");
            Gdx.graphics.setWindowedMode(800, 480);
        }

        else{
            fileManager.write("fullscreen", "true");
            setText("fullscreen: true");
            Gdx.graphics.setFullscreenMode(currentMode);
        }
    }
}
