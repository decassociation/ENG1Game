package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Carrot extends Vegetable{
    public Carrot(Float x, Float y, Integer id){
        uncutImg = new Texture(Gdx.files.internal("carrot.png"));
        cutImg = new Texture(Gdx.files.internal("carrot.png"));
        activeImg = uncutImg;
        xPos = x;
        yPos = y;
        ingID = id;

        //Carrot Instantiation
        ingredient = new Rectangle();
        ingredient.x = x;
        ingredient.y = y;
        //Until sprites are created, this will be set to 50x50
        ingredient.width = 50;
        ingredient.height = 50;
    }
}
