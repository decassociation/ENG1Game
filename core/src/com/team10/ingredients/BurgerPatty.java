package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class BurgerPatty extends Meat{
    public BurgerPatty () {
        cookedImg = new Texture(Gdx.files.internal("temp_food.png"));
        uncookedImg = new Texture(Gdx.files.internal("temp_food.png"));
        activeImg = uncookedImg;
        name = "Burger";

        frying_station = true;
    }
}
