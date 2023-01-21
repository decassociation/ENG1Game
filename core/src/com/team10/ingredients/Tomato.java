package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Tomato extends Vegetable{

    public Tomato(Float x, Float y, Integer id){
        uncutImg = new Texture(Gdx.files.internal("tomato_uncut.png"));
        cutImg = new Texture(Gdx.files.internal("tomato_uncut.png"));
        activeImg = uncutImg;
        xPos = x;
        yPos = y;
        ingID = id;

        //Tomato Instantiation
        ingredient = new Rectangle();
        ingredient.x = x;
        ingredient.y = y;
        //Until sprites are created, this will be set to 50x50
        ingredient.width = 16;
        ingredient.height = 16;
    }
}
