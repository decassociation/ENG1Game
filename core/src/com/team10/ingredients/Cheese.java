package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Cheese extends Ingredient {
    private float xPos;
    private float yPos;
    private int ingID;

    public Cheese(Float x, Float y, Integer id) {
        activeImg = new Texture(Gdx.files.internal("temp_food.png"));
        xPos = x;
        yPos = y;
        ingID = id;

        ingredient = new Rectangle();
        ingredient.x = x;
        ingredient.y = y;
        ingredient.width = 16;
        ingredient.height = 16;
    }
}
