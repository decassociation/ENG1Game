package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BurgerPatty extends Meat{
    public BurgerPatty () {
        cookedImg = new Texture(Gdx.files.internal("cookedBurger.png"));
        uncookedImg = new Texture(Gdx.files.internal("rawBurger.png"));
        activeImg = uncookedImg;
        name = "Burger";

        frying_station = true;
    }
}
