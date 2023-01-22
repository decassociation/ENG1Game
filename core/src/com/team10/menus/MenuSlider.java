package com.team10.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class MenuSlider extends MenuButton{
    private Texture bar_texture;
    protected float sliderValue;

    /**
     * Constructor for MenuSlider
     * 
     * @param xPos the x coordinate
     * @param yPos the y coordinate
     * @param width the width of the button
     * @param height the height of the button
     * @param text the text to display on the button
     * @param batch the SpriteBatch to draw the button to
     * @param camera the camera being used to render the game, needed to get correct
       projections of coordinates for rendering and clicking
     */
    public MenuSlider(int xPos, int yPos, int width, int height, String text, SpriteBatch batch, Camera camera){
        super(xPos, yPos, width, height, text, batch, camera);

        bar_texture = new Texture(Gdx.files.internal("MenuSlider.png"));
        sliderValue = 0;
    }

    /**
     * Convert the value on the slider to a percentage for ease of use
     * 
     * sliderValue is the number of pixels from the start of the slider, which will change depending on the width of the slider,
     * so this function makes things more consistent and easy to change
     */
    public float getValue(){
        return (sliderValue * 100) / width;
    }

    /**
     * Method to simplify the process of drawing the slider, simply call draw() in the render()
     * 
     * Similar to the draw() procedure on MenuButton, draws the slider bar at the coordinates
     * and draws the slider grabber at the slider value
     */
    @Override
    public void draw(){
        BitmapFont font = new BitmapFont();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(bar_texture, xPos, yPos, width, height/10);
        batch.draw(active_texture, xPos + sliderValue, yPos - height/2, width/20, height);
        font.setColor(Color.BLACK);
        font.draw(this.batch, text, xPos, yPos + height + 10);
    }

    /**
     * Check if the slider has been clicked
     * 
     * Should be called every frame in render(), will check if the mouse has been clicked
     * inside of the slider, and then will call the slider's clickFunction() once it is released.
     * Slight changes from the MenuButton onClick(), where it will also update the sliderValue, and the click can
     * be held down even when the mouse is not over the slider, to make things easier for the user
     */
    @Override
    public void onClick(){
        if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);     // need to unproject as touchPos uses a different coordinate system, so need to convert
            
            // check if the touch position is within the rectangle of the slider
			if (touchPos.x >= xPos && touchPos.x <= xPos + width && touchPos.y >= yPos && touchPos.y <= yPos + height){
                clicked = true;
                active_texture = clicked_texture;
            }

            // check if slider has been clicked and if mouse position is between start and end of slider and update sliderValue
            // don't care about y position as this allows mouse to go off slider but still slide it
            if (touchPos.x >= xPos && touchPos.x <= xPos + width && clicked){
                sliderValue = touchPos.x - xPos - width / 40;
            }

            // limit sliderValue to the ends of the slider if the mouse goes beyond them
            else if(touchPos.x < xPos && clicked) sliderValue = 0;
            else if (touchPos.x > xPos + width && clicked) sliderValue = width;

            if(sliderValue < 0) sliderValue = 0;
		}

        else{
            // when the mouse is released, perform the click function
            if(clicked){
                clicked = false;
                clickFunction();
                active_texture = base_texture;
            }
            
        }
    }
}
