package com.team10.menus;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

public class MenuButton {
    private String text;
    private int xPos, yPos, width, height;
    private Rectangle buttonRect;
    private Texture texture;

    public MenuButton(int xPos, int yPos, int width, int height, String text){
        this.text = text;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        buttonRect = new Rectangle();
        texture = new Texture(Gdx.files.internal("MenuButton.png"));
    }

    public void onClick(Camera camera){
        if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
            System.out.println(touchPos.x + "   " + touchPos.y);
			if (touchPos.x >= xPos && touchPos.x <= xPos + width && touchPos.y >= yPos && touchPos.y <= yPos + height){
                clickFunction();
            }
		}
        
    }

    public void clickFunction(){

    }

    public void draw(SpriteBatch batch){
        BitmapFont font = new BitmapFont();
        int x = (int) (xPos*2.2727272727);
        int w = (int) (width*2.2727272727);
        int y = (int) (yPos*1.6666666666);
        int h = (int) (height*1.6666666666);
        batch.draw(texture, x, y, w, h);
        font.setColor(Color.BLACK);
        font.draw(batch, text, x + 10, y + h/2 + 5);
    }

    public Texture getTexture(){
        return texture;
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
}
