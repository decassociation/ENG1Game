package com.team10.ingredients;

import com.badlogic.gdx.graphics.Texture;

public class Vegetable extends Ingredient{
    protected Texture uncutImg;
    protected Texture cutImg;

    @Override
    public void process() { //Method for cutting any vegetable
        cut = true;
        activeImg = cutImg;
        name = "Cut " + name;
        super.process();
    }
    //Other veg-related methods go here
}
