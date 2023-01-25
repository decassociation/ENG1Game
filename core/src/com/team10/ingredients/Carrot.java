package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Carrot extends Vegetable{
    public Carrot(){
        uncutImg = new Texture(Gdx.files.internal("temp_food.png"));
        cutImg = new Texture(Gdx.files.internal("temp_food.png"));
        activeImg = uncutImg;
        name = "Carrot";
        
        cutting_station = true;
    }
}
