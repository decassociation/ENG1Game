package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class BurgerPatty extends Meat{
    public BurgerPatty (Integer x, Integer y) {
        cookedImg = new Texture(Gdx.files.internal("patty_cooked.png"));
        uncookedImg = new Texture(Gdx.files.internal("patty_uncooked.png"));
        activeImg = uncookedImg;
        xPos = x;
        yPos = y;

        //Patty Instantiation
        ingredient = new Rectangle();
        ingredient.x = x;
        ingredient.y = y;
        //Until sprites are created, this will be set to 50x50
        ingredient.width = 50;
        ingredient.height = 50;
    }
}
