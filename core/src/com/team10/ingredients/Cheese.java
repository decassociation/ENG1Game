package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Cheese extends Ingredient {

    public Cheese() {
        activeImg = new Texture(Gdx.files.internal("temp_food.png"));
        name = "Cheese";
    }
}
