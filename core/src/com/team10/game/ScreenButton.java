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

        // automatically scale the font of the button to the size of the button
        float fontScale = 1f/60f;
        font.getData().setScale(((float) width) * fontScale, ((float) height) * fontScale);
    }

    /**
     * Draw the button and its text to the screen
     * 
     * Differs to the draw procedure in MenuButton in that it only draws if visible is true and no longer projects to the camera
     * as the game screen renders differently to the menu screens
     */
    @Override
    protected void draw(){
        if(visible){
            batch.draw(active_texture, xPos, yPos, width, height);
            font.setColor(Color.BLACK);
            font.draw(this.batch, text, xPos, yPos+3);
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
