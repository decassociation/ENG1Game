package com.team10.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.team10.menus.MenuButton;

// Class for buttons to appear on screen during the game
public class ScreenButton extends MenuButton{
    
    public boolean visible;

    /**
     * Constructor for ScreenButton
     * 
     * @param xPos the x coordinate
     * @param yPos the y coordinate
     * @param width the width of the button
     * @param height the height of the button
     * @param text the text to display on the button
     * @param batch the SpriteBatch to draw the button to
     * @param camera the camera being used to render the game, needed to get correct
       projections of coordinates for clicking but not needed for rendering
     */
    public ScreenButton(int xPos, int yPos, int width, int height, String text, SpriteBatch batch, Camera camera){
        super(xPos, yPos, width, height, text, batch, camera);
        visible = false;
    }

    /**
     * Draw the button and its text to the screen
     * 
     * Only draws if visible
     */
    @Override
    protected void draw(){
        if(visible){
            super.draw();
        }
    }


    /**
     * Check if the button is clicked
     * 
     * Simply check to see if the button is visible and then run the normal MenuButton onClick procedure
     */
    @Override
    protected void onClick(){
        if (visible) super.onClick();
        
    }
}
