package com.team10.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.team10.menus.MenuButton;

public class ScreenButton extends MenuButton{
    
    public boolean visible;

    public ScreenButton(int xPos, int yPos, int width, int height, String text, SpriteBatch batch, Camera camera){
        super(xPos, yPos, width, height, text, batch, camera);
        visible = false;
        font.getData().setScale(0.1f, 0.15f);
    }

    @Override
    public void draw(){
        if(visible){
            batch.draw(active_texture, xPos, yPos, width, height);
            font.setColor(Color.BLACK);
            font.draw(this.batch, text, xPos, yPos+3);
        }
    }

    @Override
    public void onClick(){
        if(Gdx.input.isTouched() && visible){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            
            // check if the touch position is within the rectangle of the button
            //
            // clicked is true when clicked inside the rectangle
            // misclicked is true when clicked outside the rectangle
            //
            // use clicked and misclicked booleans to ensure the button only works when you click inside the rectangle and then
            // let go of click while the cursor is still inside the rectangle
			if (touchPos.x >= xPos && touchPos.x <= xPos + width && touchPos.y >= yPos && touchPos.y <= yPos + height && misclicked == false){
                clicked = true;
            }

            else{
                misclicked = true;
                clicked = false;
            }
		}

        else{
            if (clicked == true){
                clicked = false;
                clickFunction();
            }
            misclicked = false;

        }

        if (clicked) active_texture = clicked_texture;
        else active_texture = base_texture;
        
    }
}
