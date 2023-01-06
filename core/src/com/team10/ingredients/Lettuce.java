package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Lettuce extends Vegetable{

    public Lettuce(Integer x, Integer y){
        uncutImg = new Texture(Gdx.files.internal("lettuce.png"));
        cutImg = new Texture(Gdx.files.internal("lettuce_cut.png"));
        activeImg = uncutImg;
        xPos = x;
        yPos = y;

        //Lettuce instantiation (can this be put in Ingredient class?)
        ingredient = new Rectangle();
        ingredient.x = x;
        ingredient.y = y;
        //Until sprites are created, this will be set to 50x50
        ingredient.width = 50;
        ingredient.height = 50;
    }
}
