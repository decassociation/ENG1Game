package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Sauce extends Ingredient {
    public Sauce() {
        activeImg = new Texture(Gdx.files.internal("sauce.png"));
        name = "Sauce";
    }
}
