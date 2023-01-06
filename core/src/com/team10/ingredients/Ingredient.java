package com.team10.ingredients;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Ingredient {
    protected Rectangle ingredient;
    protected Texture activeImg;
    public Integer xPos;
    public Integer yPos;

    public Texture getImg() {
        return activeImg;
    }

    public Integer getX() {
        return xPos;
    }
    public Integer getY() {
        return yPos;
    }

    //Methods for things like moving ingredients around will go here
}
