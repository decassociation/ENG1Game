package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Tomato extends Vegetable{

    public Tomato(Integer x, Integer y){
        uncutImg = new Texture(Gdx.files.internal("tomato.png"));
        cutImg = new Texture(Gdx.files.internal("tomato_cut.png"));
        activeImg = uncutImg;
        xPos = x;
        yPos = y;

        //Tomato Instantiation
        ingredient = new Rectangle();
        ingredient.x = x;
        ingredient.y = y;
        //Until sprites are created, this will be set to 50x50
        ingredient.width = 50;
        ingredient.height = 50;
    }
}
