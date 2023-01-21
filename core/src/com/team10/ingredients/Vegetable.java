package com.team10.ingredients;

import com.badlogic.gdx.graphics.Texture;

public class Vegetable extends Ingredient{
    protected Texture uncutImg;
    protected Texture cutImg;
    private Boolean isCut = false;

    public void cut() { //Method for cutting any vegetable
        isCut = true;
        activeImg = cutImg;
    }
    //Other veg-related methods go here
}
