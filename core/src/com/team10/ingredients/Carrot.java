package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Carrot extends Vegetable{
    public Carrot(Float x, Float y, Integer id){
        uncutImg = new Texture(Gdx.files.internal("temp_food.png"));
        cutImg = new Texture(Gdx.files.internal("temp_food.png"));
        activeImg = uncutImg;
        xPos = x;
        yPos = y;
        ingID = id;
        
        cutting_station = true;

        //Carrot Instantiation
        ingredient = new Rectangle();
        ingredient.x = x;
        ingredient.y = y;
        ingredient.width = 16;
        ingredient.height = 16;
    }
}
