package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Sauce extends Ingredient {
    public Sauce() {
        activeImg = new Texture(Gdx.files.internal("temp_food.png"));
        name = "Sauce";
    }
}
