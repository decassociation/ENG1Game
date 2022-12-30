package com.team10.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team10.game.FileManager;

public class ControlChangeButton extends MenuButton{

    private String control;
    private FileManager fileManager;
    private boolean waitingForInput;

    public ControlChangeButton(int xPos, int yPos, int width, int height, String text, SpriteBatch batch, Camera camera, String control){
        super(xPos, yPos, width, height, text, batch, camera);

        this.control = control;
        fileManager = new FileManager("ENG1Game/config/desktop_settings.txt");
        waitingForInput = false;
    }

    @Override
    public void clickFunction(){
        waitingForInput = true;
        setText(control + ": [press key]");
    }


    /**
     * Get the next button pressed and set it as the key for the control corresponding to the button
     * 
     * Must be called in render() separately to draw() and onClick()
     */
    public void getInput(){
        if (waitingForInput){
            for (int i = 0; i <= 255; i++) {
                if(Gdx.input.isKeyJustPressed(i)){
                    waitingForInput = false;
                    setText(control + ": " + Input.Keys.toString(i));
                    fileManager.write(control, Input.Keys.toString(i));
                }
            }
        }
    }

    
}
