package com.team10.ingredients;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Ingredient {
    protected Rectangle ingredient;
    protected Texture activeImg;
    public String name; //ID for specific ingredient item

    public Ingredient(){
        ingredient = new Rectangle();
    }

    // booleans for checking ingredient compatibility with cooking stations
    public boolean frying_station = false;
    public boolean cutting_station = false;
    public boolean baking_station = false;

    // booleans for states after being used at cooking station
    public boolean fried = false;
    public boolean baked = false;
    public boolean cut = false;
    public boolean processed = false;

    public Texture getImg() {
        return activeImg;
    }

    public void process(){
        frying_station = false;
        cutting_station = false;
        baking_station = false;
        processed = true;
    }
}
