package com.team10.menus;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team10.game.FileManager;
import com.team10.game.SoundManager;

public class VolumeSlider extends MenuSlider{
    private FileManager fileManager;

    public VolumeSlider(int xPos, int yPos, int width, int height, String text, SpriteBatch batch, Camera camera){
        super(xPos, yPos, width, height, text, batch, camera);
        fileManager = new FileManager();
        sliderValue = Float.valueOf(fileManager.read("volume")) * width / 100;
    }

    @Override
    public void clickFunction(){
        fileManager.write("volume", Float.toString(getValue()));
        setText("volume: " + Integer.toString(Math.round(Float.valueOf(fileManager.read("volume")))));
        SoundManager.setVolume(getValue());
    }
    
}
