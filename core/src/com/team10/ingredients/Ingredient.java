package com.team10.ingredients;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Ingredient {
    protected Rectangle ingredient;
    protected Texture activeImg;
    protected Float xPos;
    protected Float yPos;
    public Integer ingID; //ID for specific ingredient item

    public Texture getImg() {
        return activeImg;
    }

    public Float getX() {
        return xPos;
    }
    public Float getY() {
        return yPos;
    }
}
