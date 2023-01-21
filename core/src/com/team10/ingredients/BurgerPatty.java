package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class BurgerPatty extends Meat{
    public BurgerPatty (Float x, Float y, Integer id) {
        cookedImg = new Texture(Gdx.files.internal("temp_food.png"));
        uncookedImg = new Texture(Gdx.files.internal("temp_food.png"));
        activeImg = uncookedImg;
        xPos = x;
        yPos = y;
        ingID =id;

        //Patty Instantiation
        ingredient = new Rectangle();
        ingredient.x = x;
        ingredient.y = y;
        ingredient.width = 16;
        ingredient.height = 16;
    }
}
