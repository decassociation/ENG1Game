package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Lettuce extends Vegetable{

    public Lettuce(Float x, Float y, Integer id){
        uncutImg = new Texture(Gdx.files.internal("temp_food.png"));
        cutImg = new Texture(Gdx.files.internal("temp_food.png"));
        activeImg = uncutImg;
        xPos = x;
        yPos = y;
        ingID = id;

        //Lettuce instantiation (can this be put in Ingredient class?)
        ingredient = new Rectangle();
        ingredient.x = x;
        ingredient.y = y;
        ingredient.width = 16;
        ingredient.height = 16;
    }
}
