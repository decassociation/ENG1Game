package com.team10.menus;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

public class MenuButton {
    protected String text;
    protected int xPos, yPos, width, height;
    protected Texture active_texture;
    protected Texture base_texture;
    protected Texture clicked_texture;
    protected SpriteBatch batch;
    protected Camera camera;
    protected Boolean clicked;
    protected Boolean misclicked;
    protected BitmapFont font;

    /**
     * Constructor for MenuButton
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
    public MenuButton(int xPos, int yPos, int width, int height, String text, SpriteBatch batch, Camera camera){
        this.text = text;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.batch = batch;
        this.camera = camera;
        
        clicked = false;
        misclicked = false;

        base_texture = new Texture(Gdx.files.internal("MenuButton.png"));
        clicked_texture = new Texture(Gdx.files.internal("MenuButtonClick.png"));
        active_texture = base_texture;

        font = new BitmapFont();
    }

    public MenuButton(int xPos, int yPos, int width, int height, String text) {
    }

    /**
     * Check if the button has been clicked
     * 
     * Should be called every frame in render(), will check if the mouse has been clicked
     * inside of the button, and then will call the button's clickFunction()
     */
    public void onClick(){
        if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);     // need to unproject as touchPos uses a different coordinate system, so need to convert
            
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

    /**
     * Function to define what happens when the button is clicked
     * 
     * Is blank in this superclass, will need to create a subclass and then override clickFunction
     * with the desired code
     */
    public void clickFunction(){

    }

    /**
     * Method to simplify the process of drawing the button, simply call draw() in the render()
     * 
     * This is why we need to pass the batch and camera as parameters to the constructor, because draw()
     * will call some methods from the batch so needs to know which to use
     */
    public void draw(){
        batch.setProjectionMatrix(camera.combined);     // fixes something to do with the batch coordinates not being the same as the camera coordinates
        batch.draw(active_texture, xPos, yPos, width, height);
        font.setColor(Color.BLACK);
        font.draw(this.batch, text, xPos + 10, yPos + height/2 + 5);
    }


    /**
     * Getters and setters for the attributes
     */
    public Texture getTexture(){
        return active_texture;
    }

    public String getText(){
        return text;
    }

    public int getX(){
        return xPos;
    }

    public int getY(){
        return yPos;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void setX(int value){
        xPos = value;
    }

    public void setY(int value){
        yPos = value;
    }

    public void setWidth(int value){
        width = value;
    }

    public void setHeight(int value){
        height = value;
    }

    public void setText(String value){
        text = value;
    }
}
